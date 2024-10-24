package negocio.modelos.operativo;

import java.time.LocalDate;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import java.io.StringReader;

public class Vehiculo {

    private CoordenadaGPS ubicacion;
    private LocalDate fechaAlta;
    private EstadoDeVehiculo estado;
    private String modelo;
    private String marca;

    public Vehiculo(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject vehiculoJSON = reader.readObject();
        
        Double ubicacionLatitud = Double.valueOf(vehiculoJSON.getString("UbicacionLatitud"));
        Double ubicacionLongitud = Double.valueOf(vehiculoJSON.getString("UbicacionLongitud"));
        ubicacion = new CoordenadaGPS(ubicacionLatitud,ubicacionLongitud);
        fechaAlta = LocalDate.parse(vehiculoJSON.getString("FechaAlta"));
        modelo = vehiculoJSON.getString("Modelo");
        marca = vehiculoJSON.getString("Marca");
        estado = EstadoDeVehiculo.valueOf(vehiculoJSON.getString("Estado"));
   
    }

    public CoordenadaGPS getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(CoordenadaGPS ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public EstadoDeVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeVehiculo estado) {
        this.estado = estado;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    

}
