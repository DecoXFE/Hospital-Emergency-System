/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.controladoresCU;

import serviciosComunes.exceptions.PolizaNoEncontradaException;
import serviciosComunes.exceptions.RegistrarLlamadaException;
import serviciosComunes.exceptions.TurnoDeOperadorNoEncontradoException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelos.Session;
import negocio.modelos.llamada.Consejo;
import negocio.modelos.llamada.strategy.LlamadaCriticaStrategy;
import negocio.modelos.llamada.strategy.LlamadaInfundadaStrategy;
import negocio.modelos.llamada.strategy.LlamadaNoCriticaStrategy;
import negocio.modelos.llamada.strategy.LlamadaStrategy;
import negocio.modelos.persona.Asegurado;
import negocio.modelos.persona.Empleado;
import persistencia.daos.operador.DAOTurnoDeOperador;
import negocio.modelos.turnoOperador.TipoDeTurnoOperador;
import negocio.modelos.turnoOperador.TurnoDeOperador;
import persistencia.daos.persona.DAOAsegurado;
import persistencia.daos.persona.DAOPersona;
import persistencia.daos.persona.DAOPoliza;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class ControladorCUAtenderLlamada {

    TurnoDeOperador turnoActual;
    LocalDate fechaInicioLlamada;
    LocalTime horaInicioLlamada;
    String telefOrigen;
    String comunicante;
    String descripcionEmergencia;
    Asegurado asegurado;
    ArrayList<Consejo> consejosOtorgados = new ArrayList<>();

    public void comprobarOperadorEnTurno() throws TurnoDeOperadorNoEncontradoException {
        TipoDeTurnoOperador tipoActual;
        LocalDate fechaActual = LocalDate.now();
        int horaActual = LocalTime.now().getHour();

        if (horaActual > 0 && horaActual < 7) {
            tipoActual = TipoDeTurnoOperador.DeNoche23;
        } else if (horaActual >= 7 && horaActual < 15) {
            tipoActual = TipoDeTurnoOperador.DeMañana7;
        } else if (horaActual >= 15 && horaActual < 23) {
            tipoActual = TipoDeTurnoOperador.DeTarde15;
        } else {
            tipoActual = TipoDeTurnoOperador.DeNoche23;
        }

        Empleado operador = Session.getInstance().getEmpleado();

        try {
            String turnoActualString = DAOTurnoDeOperador.consultaTurnoDeOperadorPorFechaYTipo(fechaActual, tipoActual.toString());
            if (turnoActualString.equals("")) {
                throw new TurnoDeOperadorNoEncontradoException("Usted no se encuentra en turno actualmente");
            }
            turnoActual = new TurnoDeOperador(turnoActualString);
            if (!turnoActual.getOperadores().contains(operador)) {
                throw new TurnoDeOperadorNoEncontradoException("Usted no se encuentra en turno actualmente");
            }
        } catch (DBConnectionException dbe) {
            Logger.getLogger(ControladorCUAtenderLlamada.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOTurnoDeOperador.", dbe);
            throw new TurnoDeOperadorNoEncontradoException("No se ha encontrado un turno de operador por error en DBConnection.", dbe);
        }

    }

    public void comprobarPoliza(String poliza, String nombrePaciente, String apellidosPaciente, LocalDate fechaNacimiento) throws PolizaNoEncontradaException,RegistrarLlamadaException {
        try {
            String nif = DAOPersona.consultaNifPorNombreYFecha(nombrePaciente, apellidosPaciente, fechaNacimiento);
            String polizaJSONString = DAOPoliza.consultaPolizaPorAseguradoYNumero(nif, poliza);
            if (polizaJSONString.equals("")) {
                LlamadaStrategy llamadaInfundadaStrat = new LlamadaInfundadaStrategy();
                registrarLlamada(llamadaInfundadaStrat);
                throw new PolizaNoEncontradaException("No se ha encontrado poliza para ese asegurado");
            }
            asegurado = new Asegurado(DAOAsegurado.consultaAseguradoPorNif(nif));
        } catch (DBConnectionException dbe) {
            Logger.getLogger(ControladorCUAtenderLlamada.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOPersona or DAOPoliza.", dbe);
            throw new PolizaNoEncontradaException("No se ha encontrado poliza por error en conexión a base de datos", dbe);
        }
    }

    public void registrarInicioLlamada() {
        fechaInicioLlamada = LocalDate.now();
        horaInicioLlamada = LocalTime.now();
    }

    public void registrarLlamada(LlamadaStrategy strat) throws RegistrarLlamadaException{
        LocalDate fechaFinLlamada = LocalDate.now();
        LocalTime horaFinLlamada = LocalTime.now();
        String nifOperador = Session.getInstance().getEmpleado().getNif();

        try {
            strat.registrarLlamada(telefOrigen, fechaInicioLlamada, horaInicioLlamada, fechaFinLlamada, horaFinLlamada, comunicante, nifOperador);
        } catch (DBConnectionException dbe) {
            Logger.getLogger(ControladorCUAtenderLlamada.class.getName()).log(Level.SEVERE, "Error on DBConnection when inserting", dbe);
            throw new RegistrarLlamadaException("No se pudo registrar llamada por error en DBConnection",dbe);
        }

    }

    public void setTelefOrigen(String telefOrigen) {
        this.telefOrigen = telefOrigen;
    }

    public void setComunicante(String comunicante) {
        this.comunicante = comunicante;
    }

    public boolean comprobarLlamadaCritica(String descripcion, boolean critica) throws RegistrarLlamadaException {
        String nifAsegurado = asegurado.getNif();
        descripcionEmergencia = descripcion;
        if (critica) {
            LlamadaStrategy strat = new LlamadaCriticaStrategy(nifAsegurado, descripcion);
            registrarLlamada(strat);
            return true;
        }
        return false;
    }

    //devuelve true si soluciona la emergencia
    public boolean addConsejo(String descripcion, String resultado, boolean soluciona, boolean requiereOperativo) throws RegistrarLlamadaException {
        Consejo c = new Consejo(descripcion, soluciona, resultado);
        consejosOtorgados.add(c);
        if (soluciona || requiereOperativo) {
            LlamadaStrategy strat = new LlamadaNoCriticaStrategy(consejosOtorgados, asegurado.getNif(), descripcionEmergencia, !requiereOperativo);
            registrarLlamada(strat);
            return true;
        }
        return false;
    }
}
