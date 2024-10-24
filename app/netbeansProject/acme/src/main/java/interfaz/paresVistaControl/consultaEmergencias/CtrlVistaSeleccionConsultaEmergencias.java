/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.consultaEmergencias;

import serviciosComunes.exceptions.OperativoNoEncontradoException;
import interfaz.GestorInterfazUsuario;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import negocio.controladoresCU.ControladorCUConsultarEmergencias;

/**
 *
 * @author diego
 */
public class CtrlVistaSeleccionConsultaEmergencias {

    private final VistaSeleccionConsultaEmergencias vista;
    private final ControladorCUConsultarEmergencias control;

    public CtrlVistaSeleccionConsultaEmergencias(VistaSeleccionConsultaEmergencias vista) throws NullPointerException {
        control = new ControladorCUConsultarEmergencias();
        this.vista = vista;
    }

    public void procesaSeleccionarFecha() {
        String f = vista.getFecha();
        try {
            LocalDate fecha = LocalDate.parse(f); // Esto lanza la excepción
            control.identificarMiOperativoPorFechaTurno(fecha);
            vista.muestraMensajeErrorSinResetearVista("");
            vista.fijarFecha();
            mostrarActivaciones();
        } catch (DateTimeParseException e) {
            vista.muestraMensajeError("Formato de fecha incorrecta: 'YYYY-MM-DD'");
        } catch (OperativoNoEncontradoException e) {
            vista.muestraMensajeError(e.getMessage());
        }
    }

    public void cancelarOperacion() {
        GestorInterfazUsuario.getInstance().mostrarVistaOpcionesPersonalDeOperativo();
    }

    public void mostrarActivaciones() {
        vista.resetearActivaciones();
        String filtro = vista.getFiltroActivacion();

        ArrayList<String> datos = control.getDatosActivaciones(filtro);
        for (String activacion : datos) {
            vista.agregarActivacionALista(activacion);
        }
    }

    public void procesaMostrarDetalles() {
        String activacionSeleccionada = vista.getActivacionSeleccionada();
        if (activacionSeleccionada == null) {
            vista.muestraMensajeErrorSinResetearVista("No ha seleccionado una activacion");
        } else {
            String[] datosActivacionSeleccionada = activacionSeleccionada.split(" \\|\\| ");
            String[] datosCompletos = control.conseguirDatosCompletosActivacion(datosActivacionSeleccionada);
            vista.mostrarDatosActivacion(datosCompletos);
            vista.muestraMensajeErrorSinResetearVista("");
        }
    }
}
