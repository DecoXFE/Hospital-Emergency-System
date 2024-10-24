/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.opcionesOperador;

import interfaz.GestorInterfazUsuario;

/**
 *
 * @author diego
 */
public class CtrlVistaOpcionesOperador {

    private final VistaOpcionesOperador vista;

    public CtrlVistaOpcionesOperador (VistaOpcionesOperador vista) throws NullPointerException {
        this.vista = vista;
    }

    public void procesaAtenderLlamada(){
        GestorInterfazUsuario.getInstance().mostrarVistaAtenderLlamada();
    }

}
