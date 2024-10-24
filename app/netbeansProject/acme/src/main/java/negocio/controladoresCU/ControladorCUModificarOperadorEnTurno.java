/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.controladoresCU;

import serviciosComunes.exceptions.CambiarOperadorEnTurnoException;
import serviciosComunes.exceptions.NoExistenOperadoresDisponiblesException;
import serviciosComunes.exceptions.TurnoDeOperadorNoEncontradoException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;
import negocio.modelos.persona.Empleado;
import negocio.modelos.turnoOperador.TipoDeTurnoOperador;
import negocio.modelos.persona.TipoDisponibilidad;
import negocio.modelos.turnoOperador.TurnoDeOperador;
import persistencia.daos.persona.DAODisponibilidad;
import persistencia.daos.persona.DAOEmpleado;
import persistencia.daos.operador.DAOTurnoDeOperador;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class ControladorCUModificarOperadorEnTurno {

    TurnoDeOperador turnoSeleccionado = null;
    ArrayList<Empleado> operadoresACambiar = null;

    public void identificarTurnoDeOperadorPorFecha(LocalDate fecha, String tipoDeTurno) throws TurnoDeOperadorNoEncontradoException{
        try{
            String jsonTurnoDeOperador = DAOTurnoDeOperador.consultaTurnoDeOperadorPorFechaYTipo(fecha, tipoDeTurno);
        if (!jsonTurnoDeOperador.equals("")) {
            turnoSeleccionado = new TurnoDeOperador(jsonTurnoDeOperador);
        } else {
            throw new TurnoDeOperadorNoEncontradoException("No se ha encontrado Turno de Operador en dicha fecha");
        }
        }catch(DBConnectionException e){
            Logger.getLogger(ControladorCUModificarOperadorEnTurno.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOTurnoDeOperador.", e);
            throw new TurnoDeOperadorNoEncontradoException("No se ha encontrado Turno de Operador por error en conexión a base de datos",e);
        }
    }


    public ArrayList<String> getDatosOperadores() {
        return consultaDatosOperadores(turnoSeleccionado.getOperadores());
    }
    
    public ArrayList<String> getDatosOperadoresACambiar() {
        return consultaDatosOperadores(operadoresACambiar);
    }
    
    private ArrayList<String> consultaDatosOperadores(ArrayList<Empleado> operadores){
        ArrayList<String> datosOperadores = new ArrayList<>();
        String cadenaOperador;
        for (Empleado e : operadores) {
            cadenaOperador = e.getNif() + " || " + e.getNombre() + " " + e.getApellidos() + " || " + e.getTelefono();
            datosOperadores.add(cadenaOperador);
        }

        return datosOperadores;
    }

    public void comprobarOperadoresACambiar() throws NoExistenOperadoresDisponiblesException{
        operadoresACambiar = new ArrayList<>();
        try{
        String operadoresString = DAOEmpleado.consultaOperadores();

        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(operadoresString));
        JsonArray operadoresJSON = reader.readArray();

        for (JsonValue v : operadoresJSON) {
            Empleado e = new Empleado(v.toString());
            TipoDisponibilidad disponibilidadEnFechaTurno = TipoDisponibilidad.valueOf(DAODisponibilidad.consultaDisponibilidadPorNifYFecha(e.getNif(), turnoSeleccionado.getFechaTurno()));
            if (e.getDisponibilidadActual() != TipoDisponibilidad.DeBaja
                    && disponibilidadEnFechaTurno != TipoDisponibilidad.DeVacaciones
                    && !turnoSeleccionado.getOperadores().contains(e)
                    && comprobarHorasDesdeTurnoAnterior(e)) {
                operadoresACambiar.add(e);
            }
        }
        if(operadoresACambiar.isEmpty()){
            throw new NoExistenOperadoresDisponiblesException("No hay operadores disponibles.");
        }
        }catch (DBConnectionException dbe){
            Logger.getLogger(ControladorCUModificarOperadorEnTurno.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOEmpleado, DAODisponibilidad or DAOTurnoDeOperador", dbe);
            throw new NoExistenOperadoresDisponiblesException("No hay operadores disponibles debido a error en DBConnection.",dbe);
        }
    }

    // Devuelve si han pasado más de 12 horas desde el turno anterior
    public boolean comprobarHorasDesdeTurnoAnterior(Empleado e) {
        LocalDate fechaTurno = turnoSeleccionado.getFechaTurno();
        TipoDeTurnoOperador tipoTurno = turnoSeleccionado.getTipoTurno();
        
        try{
            String turnoAnteriorString = DAOTurnoDeOperador.consultaTipoDelTurnoAnteriorPorFechaYNif(fechaTurno, e.getNif());
        // Si no tiene turnos anteriores
        if (turnoAnteriorString.equals("")) {
            return true;
        }
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(turnoAnteriorString));
        JsonObject turnoAnteriorJSON = reader.readObject();

        LocalDate fechaTurnoAnterior = LocalDate.parse(turnoAnteriorJSON.getString("FechaTurnoAnterior"));
        long diferenciaDias = fechaTurno.toEpochDay() - fechaTurnoAnterior.toEpochDay();
        
        //Si ha pasado mas de un dia desde el último turno siempre va a poder
        if(diferenciaDias > 1){
            return true;
        }
        //Si ha pasado exactamente un dia hay que comprobar las horas
        else {
            TipoDeTurnoOperador tipoTurnoAnterior = TipoDeTurnoOperador.valueOf(turnoAnteriorJSON.getString("TipoTurnoAnterior"));
            switch(tipoTurno){
                case DeMañana7:
                    switch(tipoTurnoAnterior){
                        case DeMañana7:
                        case DeNoche23:
                            return true;
                        case DeTarde15:
                            return false;
                    }
                case DeTarde15:
                    return true;
                case DeNoche23://empieza a las 23 del 9
                    switch(tipoTurnoAnterior){
                        case DeMañana7:
                        case DeTarde15:
                            return false;
                        case DeNoche23:
                            return true;
                    }
            }
        }
        }catch(DBConnectionException dbe){
            Logger.getLogger(ControladorCUModificarOperadorEnTurno.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOTurnoDeOperador.", dbe); 
        }
        
        return false;
    }

    public void cambiarOperador(String operadorAntiguo, String operadorACambiar) throws CambiarOperadorEnTurnoException{
        String nifAntiguo = operadorAntiguo.split(" \\|\\| ")[0];
        String nifNuevo = operadorACambiar.split(" \\|\\| ")[0];
        try{
            DAOTurnoDeOperador.cambiarOperadorEnTurno(turnoSeleccionado.getFechaTurno(), turnoSeleccionado.getTipoTurno().toString(),nifAntiguo,nifNuevo);
        }catch(DBConnectionException dbe){
            Logger.getLogger(ControladorCUModificarOperadorEnTurno.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOTurnoDeOperador.", dbe);
            throw new CambiarOperadorEnTurnoException("Error en DBConnection al cambiar el operador en turno.",dbe);
        }
        
    }
}
