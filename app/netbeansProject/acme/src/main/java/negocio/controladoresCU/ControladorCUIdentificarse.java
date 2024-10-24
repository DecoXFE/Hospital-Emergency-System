/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.controladoresCU;

import serviciosComunes.exceptions.EmpleadoNoActivoException;
import serviciosComunes.exceptions.EmpleadoNoEncontradoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.modelos.persona.Empleado;
import negocio.modelos.Session;
import persistencia.daos.persona.DAOEmpleado;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class ControladorCUIdentificarse {

    public String identificarEmpleado(String d, String p) throws EmpleadoNoEncontradoException, EmpleadoNoActivoException{
        boolean enActivo;
        try{
        String jsonEmpleado = DAOEmpleado.consultaEmpleadoPorLoginYPassword(d,p);
        if (jsonEmpleado.equals("")){
            throw new EmpleadoNoEncontradoException("No se ha encontrado un empleado con dichas credenciales.");
        }else{
            Empleado e = new Empleado(jsonEmpleado);
            enActivo = e.estaActivo();
            if(enActivo){
                Session.getInstance().setEmpleado(e);
                return e.getRolActual().toString();
            }
            else{
                throw new EmpleadoNoActivoException("El empleado no se encuentra en activo.");
            }
        }
        }catch(DBConnectionException e){
            Logger.getLogger(ControladorCUIdentificarse.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOEmpleado.", e);
            throw new EmpleadoNoEncontradoException("No se ha encontrado un empleado por error en DBConnection.",e);
        }
        
    }
}
