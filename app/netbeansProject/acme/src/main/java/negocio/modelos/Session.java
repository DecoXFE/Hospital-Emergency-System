/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos;

import negocio.modelos.persona.Empleado;

/**
 *
 * @author diego
 */
public class Session {
    private static Session sesion;
    private Empleado empleado;
    
    private Session(){
        
    }

    public static Session getInstance() {
        if (sesion == null) {
            sesion = new Session();
        }
        return sesion;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }
}
