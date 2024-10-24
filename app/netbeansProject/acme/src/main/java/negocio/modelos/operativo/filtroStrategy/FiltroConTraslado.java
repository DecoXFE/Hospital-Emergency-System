/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.operativo.filtroStrategy;

import negocio.modelos.operativo.Activacion;

/**
 *
 * @author diego
 */
public class FiltroConTraslado implements FiltroActivacion {
    @Override
    public boolean filtrar(Activacion activacion) {
        return activacion.getTrasladoHospital() != null;
    }
}
