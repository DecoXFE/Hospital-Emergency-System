/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.llamada.strategy;

import java.time.LocalDate;
import java.time.LocalTime;
import persistencia.daos.llamada.DAOLlamadaInfundada;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class LlamadaInfundadaStrategy implements LlamadaStrategy {
    @Override
    public void registrarLlamada(String telefOrigen, LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, String comunicante, String nifOperador) throws DBConnectionException {
       DAOLlamadaInfundada.registrarLlamada(telefOrigen, comunicante, fechaInicio, horaInicio, fechaFin, horaFin, nifOperador);
    }
}
