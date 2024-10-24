/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.modificarOperadorEnTurno;

import serviciosComunes.exceptions.CambiarOperadorEnTurnoException;
import serviciosComunes.exceptions.NoExistenOperadoresDisponiblesException;
import serviciosComunes.exceptions.TurnoDeOperadorNoEncontradoException;
import interfaz.GestorInterfazUsuario;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import negocio.controladoresCU.ControladorCUModificarOperadorEnTurno;

/**
 *
 * @author diego
 */
public class CtrlVistaModificarOperadorEnTurno {

    private final VistaModificarOperadorEnTurno vista;
    private final ControladorCUModificarOperadorEnTurno control;

    public CtrlVistaModificarOperadorEnTurno(VistaModificarOperadorEnTurno vista) throws NullPointerException {
        control = new ControladorCUModificarOperadorEnTurno();
        this.vista = vista;
    }

    public void finalizarCU() {
        GestorInterfazUsuario.getInstance().mostrarVistaOpcionesGerente();
    }

    public void procesaSeleccionarFecha() {
        String f = vista.getFecha();
        try {
            LocalDate fecha = LocalDate.parse(f); // Esto lanza la excepción
            String tipoDeTurno = vista.getFiltroTipoDeTurno();
            control.identificarTurnoDeOperadorPorFecha(fecha, tipoDeTurno);
            vista.muestraMensajeErrorSinResetearVista("");
            vista.fijarFecha();
            vista.fijarFiltro();
            vista.desabilitarBotonMostrarTurnos();
            vista.habilitarSeleccionOperador();
            mostrarOperadores();
        } catch (DateTimeParseException e) {
            vista.muestraMensajeError("Formato de fecha incorrecta: 'YYYY-MM-DD'");
        } catch (TurnoDeOperadorNoEncontradoException e) {
            vista.muestraMensajeError(e.getMessage());
        }
    }

    public void mostrarOperadores() {
        ArrayList<String> datos = control.getDatosOperadores();
        for (String operador : datos) {
            vista.agregarOperadorEnTurnoALista(operador);
        }

    }

    public void mostrarOperadoresACambiar() {
        ArrayList<String> datos = control.getDatosOperadoresACambiar();
        for (String operador : datos) {
            vista.agregarOperadorACambiarALista(operador);
        }
    }

    public void procesaSeleccionOperador() {
        String operadorSeleccionado = vista.getOperadorSeleccionado();
        if (operadorSeleccionado == null) {
            vista.muestraMensajeErrorSinResetearVista("No ha seleccionado un operador");
        } else {
            vista.muestraMensajeErrorSinResetearVista("");
            try {
                control.comprobarOperadoresACambiar();
                vista.deshabilitarSeleccion();
                mostrarOperadoresACambiar();
            } catch(NoExistenOperadoresDisponiblesException e) {
                vista.muestraMensajeErrorSinResetearVista(e.getMessage());
                vista.deshabilitarSeleccion();
            }
        }
    }

    public void procesaConfirmarCambio() {
        String operadorACambiar = vista.getOperadorACambiar();
        String operadorAntiguo = vista.getOperadorSeleccionado();
        if (operadorACambiar.isEmpty()) {
            vista.muestraMensajeErrorSinResetearVista("No se ha seleccionado un operador a cambiar");
        } else {
            try{
                control.cambiarOperador(operadorAntiguo, operadorACambiar);
                finalizarCU();
            }catch(CambiarOperadorEnTurnoException e){
                vista.muestraMensajeErrorSinResetearVista(e.getMessage());
            }

        }
    }

}
