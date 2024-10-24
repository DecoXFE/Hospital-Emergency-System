/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.llamada.strategy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import negocio.modelos.llamada.Consejo;
import persistencia.daos.llamada.DAOConsejo;
import persistencia.daos.llamada.DAOLlamadaNoCritica;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class LlamadaNoCriticaStrategy implements LlamadaStrategy {
    private final ArrayList<Consejo> consejos;
    private final String paciente;
    private final String descripcionEmergencia;
    private final boolean esLeve;
    
    public LlamadaNoCriticaStrategy(ArrayList<Consejo> consejos, String paciente, String descripcionEmergencia,boolean esLeve){
        this.paciente = paciente;
        this.descripcionEmergencia = descripcionEmergencia;
        this.consejos = consejos;
        this.esLeve = esLeve;
    }
    
    @Override
    public void registrarLlamada(String telefOrigen, LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, String comunicante, String nifOperador) throws DBConnectionException {
        int id = DAOLlamadaNoCritica.registrarLlamadaYDevolverID(telefOrigen, comunicante, fechaInicio, horaInicio, fechaFin, horaFin, nifOperador,paciente,descripcionEmergencia,esLeve);
        
        for(Consejo c : consejos){
            DAOConsejo.registrarConsejo(id, c.getDescripcion(),c.getResultado(),c.soluciona());
        }
    }

}
