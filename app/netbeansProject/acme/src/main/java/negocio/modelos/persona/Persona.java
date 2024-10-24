/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.modelos.persona;

import java.io.StringReader;
import java.time.LocalDate;
import javax.json.Json;

import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import negocio.modelos.general.Direccion;

/**
 *
 * @author diego
 */
public class Persona {
    private String nif;
    private String nombre;
    private String apellidos;
    private LocalDate fechaDeNacimiento;
    private String telefono;
    private Direccion direccion;

    public Persona(String json) {
        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(json));
        JsonObject entryJSON = reader.readObject();
        
        reader = factory.createReader(new StringReader(entryJSON.getString("Persona")));
        JsonObject personaJSON = reader.readObject();
        
        this.nif = personaJSON.getString("Nif");
        this.nombre = personaJSON.getString("Nombre");
        this.apellidos = personaJSON.getString("Apellidos");
        this.fechaDeNacimiento = LocalDate.parse(personaJSON.getString("FechaDeNacimiento"));
        this.telefono =personaJSON.getString("Telefono");
        String direccionJSON = personaJSON.getString("Direccion");
        this.direccion = new Direccion(direccionJSON); 
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.apellidos = Apellidos;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate FechaDeNacimiento) {
        this.fechaDeNacimiento = FechaDeNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    @Override
    public boolean equals(Object obj) {
        // Verificar si es el mismo objeto
        if (this == obj)
            return true;
        
        // Verificar si el objeto es nulo
        if (obj == null)
            return false;
        
        // Verificar si los tipos son diferentes
        if (getClass() != obj.getClass())
            return false;
        
        // Castear el objeto a Persona
        Persona otraPersona = (Persona) obj;
        
        // Comparar atributos
        if (nif == null) {
            if (otraPersona.nif!= null)
                return false;
        }else if (!nif.equals(otraPersona.nif))
            return false;
        
        return true;
    }
   
    
}
