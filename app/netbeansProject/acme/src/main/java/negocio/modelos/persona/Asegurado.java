/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.persona;

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
public class Asegurado extends Persona {

    Sexo sexo;
    String numeroDeCuenta;
    ArrayList<Poliza> polizas;
    
    public Asegurado(String json) {
        super(json);
        polizas = new ArrayList<>();
        
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject aseguradoJSON = reader.readObject();
        
        reader = factory.createReader(new StringReader(aseguradoJSON.getString("Polizas")));
        JsonArray polizasJSON = reader.readArray();
        
        sexo = Sexo.valueOf(aseguradoJSON.getString("Sexo"));
        numeroDeCuenta = aseguradoJSON.getString("NumeroDeCuenta");
        
        for (JsonValue v : polizasJSON){
            Poliza p = new Poliza(v.toString());
            polizas.add(p);
        }
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }
    
    
    
    
}
