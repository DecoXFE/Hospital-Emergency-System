/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.opcionesPersonalDeOperativo;

import interfaz.GestorInterfazUsuario;

/**
 *
 * @author diego
 */
public class CtrlVistaOpcionesPersonalDeOperativo {

    private final VistaOpcionesPersonalDeOperativo vista;

    public CtrlVistaOpcionesPersonalDeOperativo (VistaOpcionesPersonalDeOperativo vista) throws NullPointerException {
        this.vista = vista;
    }
    
    public void consultarEmergencias(){
        GestorInterfazUsuario.getInstance().mostrarVistaSeleccionConsultaEmergencias();
    }
}
