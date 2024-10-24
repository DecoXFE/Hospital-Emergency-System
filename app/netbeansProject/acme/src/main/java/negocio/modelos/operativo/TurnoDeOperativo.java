package negocio.modelos.operativo;

import negocio.modelos.persona.Empleado;
import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;

/**
 * @author Alex
 */
public class TurnoDeOperativo {

    private LocalDate fechaCreacion;
    private LocalDate fechaTurno;
    private TipoDeTurnoOperativo tipoDeTurnoOperativo;
    private ArrayList<Empleado> medicos;
    private ArrayList<Empleado> conductores;

    public TurnoDeOperativo(String json) {
        medicos = new ArrayList<>();
        conductores = new ArrayList<>();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject turnoDeOperativoJSON = reader.readObject();

        reader = factory.createReader(new StringReader(turnoDeOperativoJSON.getString("MedicosEnTurno")));
        JsonArray medicosEnTurnoJSON = reader.readArray();

        reader = factory.createReader(new StringReader(turnoDeOperativoJSON.getString("ConductoresEnTurno")));
        JsonArray conductoresEnTurnoJSON = reader.readArray();

        this.tipoDeTurnoOperativo = TipoDeTurnoOperativo.valueOf(turnoDeOperativoJSON.getString("NombreTipo"));
        this.fechaTurno = LocalDate.parse(turnoDeOperativoJSON.getString("FechaTurno"));
        this.fechaCreacion = LocalDate.parse(turnoDeOperativoJSON.getString("FechaCreacion"));

        for (JsonValue v : medicosEnTurnoJSON) {
            Empleado e = new Empleado(v.toString());
            medicos.add(e);
        }

        for (JsonValue v : conductoresEnTurnoJSON) {
            Empleado e = new Empleado(v.toString());
            conductores.add(e);
        }
        
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public TipoDeTurnoOperativo getTipoDeTurnoOperativo() {
        return tipoDeTurnoOperativo;
    }

    public void setTipoDeTurnoOperativo(TipoDeTurnoOperativo tipoDeTurnoOperativo) {
        this.tipoDeTurnoOperativo = tipoDeTurnoOperativo;
    }

    public ArrayList<Empleado> getMedicos() {
        return medicos;
    }

    public void setMedicos(ArrayList<Empleado> medicos) {
        this.medicos = medicos;
    }

    public ArrayList<Empleado> getConductores() {
        return conductores;
    }

    public void setConductores(ArrayList<Empleado> conductores) {
        this.conductores = conductores;
    }
    
    

}
