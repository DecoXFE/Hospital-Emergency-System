/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.daos.operativo;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import persistencia.DBAccess.DBConnection;
import persistencia.daos.general.DAODireccion;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
class DAOTraslado {

    private static final String SELECT_TRASLADO_BY_ID = "SELECT D.FechaInicioTraslado,D.HoraInicioTraslado, H.nombre, H.direccion FROM DecisionesDeTrasladoAHospital D NATURAL JOIN HOSPITALES H WHERE D.id = ?";

    public static String consultaTrasladoPorID(int idTraslado) throws DBConnectionException {

        String entryJSON = "";
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        PreparedStatement ps = connection.getStatement(SELECT_TRASLADO_BY_ID);
        ResultSet rs;
        try {
            ps.setInt(1, idTraslado);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fechaInicioTraslado = rs.getString("FechaInicioTraslado");
                String horaInicioTraslado = rs.getString("HoraInicioTraslado");
                String nombreHospital = rs.getString("nombre");
                short direccion = rs.getShort("Direccion");
                
                String direccionHospital = DAODireccion.getDireccionPorId(direccion);
                        
                entryJSON = mapEntryAsJSON(fechaInicioTraslado,horaInicioTraslado,nombreHospital,direccionHospital);
            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(DAOTraslado.class.getName()).log(Level.SEVERE, "Error executing query.", e);
            connection.closeConnection();
        }
        connection.closeConnection();
        return entryJSON;

    }

    private static String mapEntryAsJSON(String fechaInicioTraslado,String horaInicioTraslado,String nombreHospital,String direccionHospital) {
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("FechaInicioTraslado", fechaInicioTraslado)
                .add("HoraInicioTraslado",horaInicioTraslado)
                .add("NombreHospital",nombreHospital)
                .add("Direccion",direccionHospital)
                .build();
        try (
                StringWriter stringWriter = new StringWriter(); JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOTraslado.class.getName()).log(Level.SEVERE, "Error creating EntryJSON.", ex);
        }
        return entryJSON;
    }

}
