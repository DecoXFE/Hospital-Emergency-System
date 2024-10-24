/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.operativo.filtroStrategy;

import java.util.ArrayList;
import negocio.modelos.operativo.Activacion;

/**
 *
 * @author diego
 */
public class FiltroContext {
    private FiltroActivacion filtro;
    
    public FiltroContext(FiltroActivacion filtro){
        this.filtro = filtro;
    }

    public void setFiltro(FiltroActivacion filtro) {
        this.filtro = filtro;
    }

    public ArrayList<String> filtrarActivaciones(ArrayList<Activacion> activaciones) {
        ArrayList<String> datosActivaciones = new ArrayList<>();
        for (Activacion a : activaciones) {
            if (filtro.filtrar(a)) {
                String cadenaActivacion = a.getIdActivacion() + " || " + a.getFechaCierre().toString() + " || " + a.getHoraActivacion();
                datosActivaciones.add(cadenaActivacion);
            }
        }
        return datosActivaciones;
    }
}
