/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.llamada.strategy;

import java.time.LocalDate;
import java.time.LocalTime;
import persistencia.daos.llamada.DAOLlamadaCritica;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class LlamadaCriticaStrategy implements LlamadaStrategy{
    private final String paciente;
    private final String descripcionEmergencia;
    
    public LlamadaCriticaStrategy(String paciente, String descripcionEmergencia){
        this.paciente = paciente;
        this.descripcionEmergencia = descripcionEmergencia;
    }
    
    @Override
    public void registrarLlamada(String telefOrigen, LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, String comunicante, String nifOperador)throws DBConnectionException {
       DAOLlamadaCritica.registrarLlamada(telefOrigen, comunicante, fechaInicio, horaInicio, fechaFin, horaFin, nifOperador,paciente,descripcionEmergencia);
    }
}
