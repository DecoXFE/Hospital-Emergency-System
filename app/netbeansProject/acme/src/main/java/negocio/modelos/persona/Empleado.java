/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.persona;

import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

import java.io.StringReader;

/**
 *
 * @author diego
 */
public class Empleado extends Persona {

    private LocalDate fechaInicioEnEmpresa;
    private TipoDeRol rolActual;
    private TipoDisponibilidad disponibilidadActual;
    private TipoVinculacion vinculacionActual;
    private LocalDate fechaInicioEnPuesto;
    private LocalDate fechaPermisoConduccion = null;
    private String numeroColegiadoMedico;
    //private ArrayList<TurnoDeOperativo> turnosDeOperativo;

    public Empleado(String json) {
        super(json);
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject empleadoJSON = reader.readObject();

        reader = factory.createReader(new StringReader(empleadoJSON.getString("RolActual")));
        JsonObject rolActualJSON = reader.readObject();

        reader = factory.createReader(new StringReader(empleadoJSON.getString("DisponibilidadActual")));
        JsonObject disponibilidadActualJSON = reader.readObject();
        
        reader = factory.createReader(new StringReader(empleadoJSON.getString("VinculacionActual")));
        JsonObject vinculacionActualJSON = reader.readObject();

        fechaInicioEnEmpresa = LocalDate.parse(empleadoJSON.getString("FechaInicioEnEmpresa"));
        vinculacionActual = TipoVinculacion.valueOf(vinculacionActualJSON.getString("NombreTipo"));
        rolActual = TipoDeRol.valueOf(rolActualJSON.getString("NombreTipo"));
        disponibilidadActual = TipoDisponibilidad.valueOf(disponibilidadActualJSON.getString("NombreTipo"));
        String aux = rolActualJSON.getString("FechaPermisoConduccion");
        if (!aux.equals("")) {
            fechaPermisoConduccion = LocalDate.parse(aux);
        }
        numeroColegiadoMedico = rolActualJSON.getString("NumeroColegiadoMedico");

        /*JsonArray turnosDeOperativo = empleadoJSON.getJsonArray("TurnosDeOperativo");
        TurnoDeOperativo turn;
        for (JsonValue j : turnosDeOperativo) {
            turn = new TurnoDeOperativo(j.toString());
            this.turnosDeOperativo.add(turn);
        }*/
    }

    public LocalDate getFechaInicioEnEmpresa() {
        return fechaInicioEnEmpresa;
    }

    public final void setFechaInicioEnEmpresa(LocalDate fechaInicioEnEmpresa) {
        this.fechaInicioEnEmpresa = fechaInicioEnEmpresa;
    }

    public TipoDeRol getRolActual() {
        return rolActual;
    }

    public void setRolActual(TipoDeRol rolActual) {
        this.rolActual = rolActual;
    }

    public LocalDate getFechaInicioEnPuesto() {
        return fechaInicioEnPuesto;
    }

    public void setFechaInicioEnPuesto(LocalDate fechaInicioEnPuesto) {
        this.fechaInicioEnPuesto = fechaInicioEnPuesto;
    }

    public TipoDisponibilidad getDisponibilidadActual() {
        return disponibilidadActual;
    }

    public void setDisponibilidadActual(TipoDisponibilidad disponibilidadActual) {
        this.disponibilidadActual = disponibilidadActual;
    }

    public TipoVinculacion getVinculacionActual() {
        return vinculacionActual;
    }

    public void setVinculacionActual(TipoVinculacion vinculacionActual) {
        this.vinculacionActual = vinculacionActual;
    }

    public LocalDate getFechaPermisoConduccion() {
        return fechaPermisoConduccion;
    }

    public void setFechaPermisoConduccion(LocalDate fechaPermisoConduccion) {
        this.fechaPermisoConduccion = fechaPermisoConduccion;
    }

    public String getNumeroColegiadoMedico() {
        return numeroColegiadoMedico;
    }

    public void setNumeroColegiadoMedico(String numeroColegiadoMedico) {
        this.numeroColegiadoMedico = numeroColegiadoMedico;
    }

    public boolean estaActivo() {
        return vinculacionActual == TipoVinculacion.ConNormalidad && disponibilidadActual == TipoDisponibilidad.Disponible;
    }
    

}
