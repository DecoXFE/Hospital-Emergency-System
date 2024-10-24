/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.llamada;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 *
 * @author diego
 */
public class Consejo {
    String descripcion;
    boolean soluciona;
    String resultado;
    
    public Consejo(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject consejoJSON = reader.readObject();
        
        descripcion = consejoJSON.getString("Descripcion");
        soluciona = Boolean.parseBoolean(consejoJSON.getString("Soluciona"));
        resultado = consejoJSON.getString("Resultado");
    }
    
    public Consejo(String descripcion, boolean soluciona, String resultado){
        this.descripcion = descripcion;
        this.resultado = resultado;
        this.soluciona = soluciona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean soluciona() {
        return soluciona;
    }

    public void setSoluciona(boolean soluciona) {
        this.soluciona = soluciona;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    
}
