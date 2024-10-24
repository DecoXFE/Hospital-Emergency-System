/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.operativo;

import negocio.modelos.persona.Empleado;
import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

import java.io.StringReader;
import negocio.modelos.general.Direccion;

/**
 *
 * @author diego
 */
public class Operativo {

    int id;
    Empleado conductor;
    Empleado medico;
    LocalDate fechaCreacion;
    EstadoDeOperativo estado;
    Direccion consultorio;
    Vehiculo vehiculo;
    TurnoDeOperativo turnoDeOperativo;

    public Operativo(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject operativoJSON = reader.readObject();

        reader = factory.createReader(new StringReader(operativoJSON.getString("Consultorio")));
        JsonObject consultorioJSON = reader.readObject();

        id = operativoJSON.getInt("IdOperativo");
        conductor = new Empleado(operativoJSON.getString("Conductor"));
        medico = new Empleado(operativoJSON.getString("Medico"));
        fechaCreacion = LocalDate.parse(operativoJSON.getString("FechaCreacion"));
        estado = EstadoDeOperativo.valueOf(operativoJSON.getString("Estado"));
        consultorio = new Direccion(consultorioJSON.getString("Direccion"));
        vehiculo = new Vehiculo(operativoJSON.getString("Vehiculo"));
        turnoDeOperativo = new TurnoDeOperativo(operativoJSON.getString("TurnoDeOperativo"));

    }

    public Empleado getConductor() {
        return conductor;
    }

    public void setConductor(Empleado conductor) {
        this.conductor = conductor;
    }

    public Empleado getMedico() {
        return medico;
    }

    public void setMedico(Empleado medico) {
        this.medico = medico;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public EstadoDeOperativo getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeOperativo estado) {
        this.estado = estado;
    }

    public Direccion getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Direccion consultorio) {
        this.consultorio = consultorio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public TurnoDeOperativo getTurnoDeOperativo() {
        return turnoDeOperativo;
    }

    public void setTurnoDeOperativo(TurnoDeOperativo turnoDeOperativo) {
        this.turnoDeOperativo = turnoDeOperativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

}
