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
import negocio.modelos.llamada.LlamadaNoCritica;

/**
 *
 * @author diego
 */
public class Activacion {

    int idActivacion;
    Direccion direccionDondeAcudir;
    LocalDate fechaActivacion;
    LocalTime horaActivacion;
    LocalDate fechaSeHaceCargoMedico = null;
    LocalTime horaSeHaceCargoMedico = null;
    LocalDate fechaCierre = null;
    LocalTime horaCierre = null;
    Traslado trasladoHospital = null;
    LlamadaNoCritica llamadaNoCritica;

    public Activacion(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject activacionJSON = reader.readObject();

        direccionDondeAcudir = new Direccion(activacionJSON.getString("DireccionDondeAcudir"));
        fechaActivacion = LocalDate.parse(activacionJSON.getString("FechaActivacion"));
        horaActivacion = LocalTime.parse(activacionJSON.getString("HoraActivacion"));
        idActivacion = activacionJSON.getInt("IdActivacion");

        llamadaNoCritica = new LlamadaNoCritica(activacionJSON.getString("LlamadaNoCritica"));

        String aux = activacionJSON.getString("FechaSeHaceCargoMedico");
        if (!aux.equals("")) {
            fechaSeHaceCargoMedico = LocalDate.parse(aux);
        }

        aux = activacionJSON.getString("HoraSeHaceCargoMedico");
        if (!aux.equals("")) {
            horaSeHaceCargoMedico = LocalTime.parse(aux);
        }

        aux = activacionJSON.getString("FechaCierre");
        if (!aux.equals("")) {
            fechaCierre = LocalDate.parse(aux);
        }

        aux = activacionJSON.getString("HoraCierre");
        if (!aux.equals("")) {
            horaCierre = LocalTime.parse(aux);
        }

        aux = activacionJSON.getString("TrasladoHospital");
        if (!aux.equals("")) {
            trasladoHospital = new Traslado(aux);
        }

    }

    public Direccion getDireccionDondeAcudir() {
        return direccionDondeAcudir;
    }

    public void setDireccionDondeAcudir(Direccion direccionDondeAcudir) {
        this.direccionDondeAcudir = direccionDondeAcudir;
    }

    public LocalDate getFechaActivacion() {
        return fechaActivacion;
    }

    public void setFechaActivacion(LocalDate fechaActivacion) {
        this.fechaActivacion = fechaActivacion;
    }

    public LocalTime getHoraActivacion() {
        return horaActivacion;
    }

    public void setHoraActivacion(LocalTime horaActivacion) {
        this.horaActivacion = horaActivacion;
    }

    public LocalDate getFechaSeHaceCargoMedico() {
        return fechaSeHaceCargoMedico;
    }

    public void setFechaSeHaceCargoMedico(LocalDate fechaSeHaceCargoMedico) {
        this.fechaSeHaceCargoMedico = fechaSeHaceCargoMedico;
    }

    public LocalTime getHoraSeHaceCargoMedico() {
        return horaSeHaceCargoMedico;
    }

    public void setHoraSeHaceCargoMedico(LocalTime horaSeHaceCargoMedico) {
        this.horaSeHaceCargoMedico = horaSeHaceCargoMedico;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    public Traslado getTrasladoHospital() {
        return trasladoHospital;
    }

    public void setTrasladoHospital(Traslado trasladoHospital) {
        this.trasladoHospital = trasladoHospital;
    }

    public int getIdActivacion() {
        return idActivacion;
    }

    public void setIdActivacion(int idActivacion) {
        this.idActivacion = idActivacion;
    }

    public boolean seHizoCargo() {
        return fechaSeHaceCargoMedico != null && horaSeHaceCargoMedico != null;
    }

    public boolean huboTraslado() {
        return trasladoHospital != null;
    }

    public LlamadaNoCritica getLlamadaNoCritica() {
        return llamadaNoCritica;
    }

    public void setLlamadaNoCritica(LlamadaNoCritica llamadaNoCritica) {
        this.llamadaNoCritica = llamadaNoCritica;
    }
    
    

}
