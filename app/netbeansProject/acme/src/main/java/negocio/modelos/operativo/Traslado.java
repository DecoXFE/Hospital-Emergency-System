/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.operativo;

import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

import java.io.StringReader;
import java.time.LocalTime;
import negocio.modelos.general.Direccion;

/**
 *
 * @author elena
 */
public class Traslado {

    LocalDate fechaInicioTraslado;
    LocalTime horaInicioTraslado;
    String nombreHospital;
    Direccion direccionHospital;

    public Traslado(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject trasladoJSON = reader.readObject();

        fechaInicioTraslado = LocalDate.parse(trasladoJSON.getString("FechaInicioTraslado"));
        horaInicioTraslado = LocalTime.parse(trasladoJSON.getString("HoraInicioTraslado"));
        nombreHospital = trasladoJSON.getString("NombreHospital");
        direccionHospital = new Direccion(trasladoJSON.getString("Direccion"));

    }

    public LocalDate getFechaInicioTraslado() {
        return fechaInicioTraslado;
    }

    public void setFechaInicioTraslado(LocalDate fechaInicioTraslado) {
        this.fechaInicioTraslado = fechaInicioTraslado;
    }

    public LocalTime getHoraInicioTraslado() {
        return horaInicioTraslado;
    }

    public void setHoraInicioTraslado(LocalTime horaInicioTraslado) {
        this.horaInicioTraslado = horaInicioTraslado;
    }

    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public Direccion getDireccion() {
        return direccionHospital;
    }

    public void setDireccion(Direccion direccion) {
        this.direccionHospital = direccion;
    }

    

}
