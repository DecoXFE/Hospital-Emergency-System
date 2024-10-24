/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.daos.operativo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistencia.DBAccess.DBConnection;
import persistencia.daos.general.DAODireccion;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class DAOConsultorio {
    private static final String SELECT_DISPONIBILIDAD_BY_ID = "SELECT * FROM CONSULTORIOS WHERE id = ?";

    public static String consultaConsultorioPorID(Short id) throws DBConnectionException{
        String entryJSON = "";
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        PreparedStatement ps = connection.getStatement(SELECT_DISPONIBILIDAD_BY_ID);
        ResultSet rs;
        try {
            ps.setShort(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                String direccion = DAODireccion.getDireccionPorId(rs.getShort("Direccion"));
                entryJSON = mapEntryAsJSON(direccion);

            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(DAOConsultorio.class.getName()).log(Level.SEVERE, "Error executing query.", e);
            connection.closeConnection();
        }
        connection.closeConnection();

        return entryJSON;

    }

    private static String mapEntryAsJSON(String direccion) {
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("Direccion", direccion)
                .build();
        try (
                StringWriter stringWriter = new StringWriter(); JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOConsultorio.class.getName()).log(Level.SEVERE, "Error creating EntryJSON.", ex);
        }
        return entryJSON;
    }
}
