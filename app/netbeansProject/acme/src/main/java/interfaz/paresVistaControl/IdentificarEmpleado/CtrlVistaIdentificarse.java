/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.IdentificarEmpleado;

import serviciosComunes.exceptions.EmpleadoNoActivoException;
import serviciosComunes.exceptions.EmpleadoNoEncontradoException;
import interfaz.GestorInterfazUsuario;
import negocio.controladoresCU.ControladorCUIdentificarse;

/**
 *
 * @author elena
 */
public class CtrlVistaIdentificarse {

    private final VistaIdentificarse vista;
    private final ControladorCUIdentificarse controlador;

    public CtrlVistaIdentificarse(VistaIdentificarse vista) throws NullPointerException {
        controlador = new ControladorCUIdentificarse();
        this.vista = vista;
    }

    public void procesaEventoIdentificarse() {
        String d = vista.getDni();
        String p = vista.getPassword();
        Boolean ok = compruebaCharArrayNoVacio(d);
        if (!ok) {
            vista.muestraMensajeError("No ha introducido datos de dni");
            return;
        }
        ok = compruebaCharArrayNoVacio(p);
        if (!ok) {
            vista.muestraMensajeError("No ha introducido datos de contraseña");
            return;
        }
        try {
            String resultado = controlador.identificarEmpleado(d, p);
            GestorInterfazUsuario.getInstance().mostrarVistaSegunRol(resultado);
        } catch (EmpleadoNoEncontradoException | EmpleadoNoActivoException e) {
            vista.muestraMensajeError(e.getMessage());
        }


}

private boolean compruebaCharArrayNoVacio(String s) {
        return !s.isEmpty();

    }

}
