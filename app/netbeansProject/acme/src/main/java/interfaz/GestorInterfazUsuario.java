/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import interfaz.paresVistaControl.opcionesOperador.VistaOpcionesOperador;
import javax.swing.JFrame;
import interfaz.paresVistaControl.IdentificarEmpleado.VistaIdentificarse;
import interfaz.paresVistaControl.atenderLlamada.VistaAtenderLlamada;
import interfaz.paresVistaControl.consultaEmergencias.VistaSeleccionConsultaEmergencias;
import interfaz.paresVistaControl.modificarOperadorEnTurno.VistaModificarOperadorEnTurno;
import interfaz.paresVistaControl.opcionesGerente.VistaOpcionesGerente;
import interfaz.paresVistaControl.opcionesPersonalDeOperativo.VistaOpcionesPersonalDeOperativo;

/**
 *
 * @author diego
 */
public class GestorInterfazUsuario {

    private static JFrame ventana;
    private static GestorInterfazUsuario gestor;

    private GestorInterfazUsuario() {
    }

    public static GestorInterfazUsuario getInstance() {
        if (gestor == null) {
            gestor = new GestorInterfazUsuario();
        }
        return gestor;
    }

    public void mostrarVistaIdentificarse() {
        resetearVista();
        ventana = new VistaIdentificarse();
        ventana.setVisible(true);
    }

    public void mostrarVistaOpcionesPersonalDeOperativo() {
        resetearVista();
        ventana = new VistaOpcionesPersonalDeOperativo();
        ventana.setVisible(true);
    }
    
    public void mostrarVistaOpcionesGerente() {
        resetearVista();
        ventana = new VistaOpcionesGerente();
        ventana.setVisible(true);
    }

    public void mostrarVistaSeleccionConsultaEmergencias() {
        resetearVista();
        ventana = new VistaSeleccionConsultaEmergencias();
        ventana.setVisible(true);
    }
    
    public void mostrarVistaModificarOperadorEnTurno() {
        resetearVista();
        ventana = new VistaModificarOperadorEnTurno();
        ventana.setVisible(true);
    }
    
    public void mostrarVistaAtenderLlamada(){
        resetearVista();
        ventana = new VistaAtenderLlamada();
        ventana.setVisible(true);
    }
    
    public void mostrarVistaOpcionesOperador(){
        resetearVista();
        ventana = new VistaOpcionesOperador();
        ventana.setVisible(true);
    }

    public void resetearVista() {
        if (ventana != null) {
            ventana.setVisible(false);
        }
    }
    
    
    public void mostrarVistaSegunRol(String rol){
        switch(rol) {
            case "Conductor":
            case "Médico":
                mostrarVistaOpcionesPersonalDeOperativo();
                break;
            case "Gerente":
                mostrarVistaOpcionesGerente();
                break;
            case "Operador":
                mostrarVistaOpcionesOperador();
        
    }
    }


}
