/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.atenderLlamada;

import serviciosComunes.exceptions.PolizaNoEncontradaException;
import serviciosComunes.exceptions.RegistrarLlamadaException;
import serviciosComunes.exceptions.TurnoDeOperadorNoEncontradoException;
import interfaz.GestorInterfazUsuario;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import negocio.controladoresCU.ControladorCUAtenderLlamada;

/**
 *
 * @author elena
 */
public class CtrlVistaAtenderLlamada {

    private final VistaAtenderLlamada vista;
    private final ControladorCUAtenderLlamada control;

    public CtrlVistaAtenderLlamada(VistaAtenderLlamada vista) throws NullPointerException {
        control = new ControladorCUAtenderLlamada();
        this.vista = vista;
    }

    public void procesaRegistrarLlamadaEntrante() {
        try {
            control.comprobarOperadorEnTurno();
            vista.habilitarConfirmarDatos();
            control.registrarInicioLlamada();
        } catch (TurnoDeOperadorNoEncontradoException e) {
            vista.muestraMensajeError(e.getMessage());
        }
    }

    public void procesaConfirmarDatos() {
        String telefOrigen = vista.getTelefOrigen();
        String comunicante = vista.getComunicante();
        String poliza = vista.getPoliza();
        String nombrePaciente = vista.getNombrePaciente();
        String apellidosPaciente = vista.getApellidosPaciente();
        String sexo = vista.getSexo();

        control.setComunicante(comunicante);
        control.setTelefOrigen(telefOrigen);

        if (telefOrigen.isEmpty() || comunicante.isEmpty() || poliza.isEmpty() || nombrePaciente.isEmpty() || apellidosPaciente.isEmpty()) {
            vista.muestraMensajeError("No has introducido los datos solicitados");
        } else {
            try {
                LocalDate fechaNacimiento = LocalDate.parse(vista.getFechaNacimiento());
                control.comprobarPoliza(poliza, nombrePaciente, apellidosPaciente, fechaNacimiento);
                vista.muestraMensajeError("");
                vista.habilitarDescripcionEmergencia();
                vista.fijarDatosInicioLlamada();
            } catch (DateTimeParseException e) {
                vista.muestraMensajeError("Formato de fecha de nacimiento incorrecto: YYYY-MM-DD");
            } catch (PolizaNoEncontradaException e) {
                vista.redirijirLlamada();
                GestorInterfazUsuario.getInstance().mostrarVistaOpcionesOperador();
            } catch (RegistrarLlamadaException rle) {
                vista.muestraMensajeError(rle.getMessage());
            }
        }
    }

    public void procesaIntroducirDescripcion() {
        String descripcion = vista.getDescripcionEmergencia();
        boolean critica = vista.esCritica();
        if (descripcion.equals("")) {
            vista.muestraMensajeError("No has introducido la descripción de la emergencia");
        } else {
            vista.muestraMensajeError("");
            try {
                if (control.comprobarLlamadaCritica(descripcion, critica)) {
                    vista.redirijirLlamada();
                    GestorInterfazUsuario.getInstance().mostrarVistaOpcionesOperador();
                } else {
                    vista.habilitarConsejos();
                    vista.fijarDescripcionEmergencia();
                }
            } catch (RegistrarLlamadaException rle) {
                vista.muestraMensajeError(rle.getMessage());
            }
        }
    }

    public void procesaGuardarConsejo() {
        String descripcion = vista.getDescripcionConsejo();
        String resultado = vista.getResultadoConsejo();
        boolean soluciona = vista.getSolucionaConsejo();
        boolean requiereOperativo = vista.getRequiereOperativo();

        if (descripcion.equals("") || resultado.equals("")) {
            vista.setMensajeConsejos("No has introducido los datos del consejo");
        } else {
            vista.setMensajeConsejos("Consejo añadido a la llamada");
            try {
                if (control.addConsejo(descripcion, resultado, soluciona, requiereOperativo)) {
                    if (!requiereOperativo) {
                        vista.llamadaFinalizadaConExito();
                    } else {
                        vista.activacionOperativo();
                    }
                    GestorInterfazUsuario.getInstance().mostrarVistaOpcionesOperador();
                }
            } catch (RegistrarLlamadaException rle) {
                vista.muestraMensajeError(rle.getMessage());
            }
            vista.resetearConsejo();
        }
    }

}
