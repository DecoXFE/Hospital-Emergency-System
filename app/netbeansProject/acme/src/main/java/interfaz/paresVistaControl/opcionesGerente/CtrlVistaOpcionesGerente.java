/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.paresVistaControl.opcionesGerente;

import interfaz.GestorInterfazUsuario;

/**
 *
 * @author diego
 */
public class CtrlVistaOpcionesGerente {

    private final VistaOpcionesGerente vista;

    public CtrlVistaOpcionesGerente (VistaOpcionesGerente vista) throws NullPointerException {
        this.vista = vista;
    }
    
    public void modificarOperadorEnTurno(){
        GestorInterfazUsuario.getInstance().mostrarVistaModificarOperadorEnTurno();
    }
    

}
