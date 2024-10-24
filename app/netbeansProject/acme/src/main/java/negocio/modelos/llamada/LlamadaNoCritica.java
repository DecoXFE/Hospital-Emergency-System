/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.llamada;

import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;

/**
 *
 * @author diego
 */
public class LlamadaNoCritica extends LlamadaDeAsegurado {
    boolean esLeve;
    ArrayList<Consejo> consejos;
    
    public LlamadaNoCritica(String json){
        super(json);
        consejos = new ArrayList<>();
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject llamadaNoCriticaJSON = reader.readObject();
        
        reader = factory.createReader(new StringReader(llamadaNoCriticaJSON.getString("Consejos")));
        JsonArray consejosJSON = reader.readArray();
        
        esLeve = Boolean.parseBoolean(llamadaNoCriticaJSON.getString("EsLeve"));
        
        for (JsonValue v : consejosJSON){
            Consejo c = new Consejo(v.toString());
            consejos.add(c);
        }
        
    }

    public boolean isEsLeve() {
        return esLeve;
    }

    public void setEsLeve(boolean esLeve) {
        this.esLeve = esLeve;
    }

    public ArrayList<Consejo> getConsejos() {
        return consejos;
    }

    public void setConsejos(ArrayList<Consejo> consejos) {
        this.consejos = consejos;
    }
    
}
