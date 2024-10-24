package persistencia.daos.operativo;

import java.io.StringWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import persistencia.DBAccess.DBConnection;
import persistencia.exceptions.DBConnectionException;

public class DAOVehiculo {

    private static final String SELECT_BY_M = "SELECT V.*,M.nombre as nombreModelo, Ma.nombre as nombreMarca,EV.* FROM VEHICULOS V INNER JOIN ESTADOSDEVEHICULO EV ON EV.IdEstado = V.Estado INNER JOIN MODELOS M ON V.Modelo = M.id INNER JOIN MARCAS MA ON MA.id = M.Marca WHERE V.Matricula = ?";

    public static String consultaVehiculoPorMatricula(String matricula) throws DBConnectionException {
        String entryJSON = "";
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        PreparedStatement ps = connection.getStatement(SELECT_BY_M);
        ResultSet rs;
        try {
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            if (rs.next()) {
                double ubicacionLatitud = rs.getDouble("UbicacionLatitud");
                double ubicacionLongitud = rs.getDouble("UbicacionLongitud");
                Date fechaAlta = rs.getDate("FechaAlta");
                String estado = rs.getString("NombreEstado");
                String marca = rs.getString("nombreMarca");
                String modelo = rs.getString("nombreModelo");

                entryJSON = mapEntryAsJSON(ubicacionLatitud, ubicacionLongitud, fechaAlta, marca, modelo, estado);

            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(DAOVehiculo.class.getName()).log(Level.SEVERE, "Error executing query.", e);
            connection.closeConnection();
        }
        connection.closeConnection();

        return entryJSON;

    }

    private static String mapEntryAsJSON(double latitud, double longitud, Date fechaAlta, String marca, String modelo, String estado) {
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("UbicacionLatitud", String.valueOf(latitud))
                .add("UbicacionLongitud", String.valueOf(longitud))
                .add("FechaAlta", fechaAlta.toString())
                .add("Marca", marca)
                .add("Modelo", modelo)
                .add("Estado", estado)
                .build();
        try (
                StringWriter stringWriter = new StringWriter(); JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOVehiculo.class.getName()).log(Level.SEVERE, "Error creating EntryJSON.", ex);
        }
        return entryJSON;
    }
}
