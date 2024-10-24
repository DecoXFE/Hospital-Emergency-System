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
import persistencia.daos.general.DAOEmpleadoEnTurno;
import persistencia.exceptions.DBConnectionException;

/**
 * @author diego
 */
public class DAOTurnoDeOperativo {

    private static final String SELECT_TURNOSDEOPERATIVO_BY_ID_OPERATIVO = "SELECT T.FechaTurno, T.FechaCreacion, TDO.NombreTipo FROM TURNOSDEOPERATIVO T INNER JOIN TIPOSDETURNODEOPERATIVO TDO ON T.TipoDeTurno = TDO.IdTipo WHERE T.id = ?";

    public static String consultaTurnoOperativoPorIDTurno(int idTurno) throws DBConnectionException {
        String entryJSON = "";
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        ResultSet rs;
        try (PreparedStatement ps = connection.getStatement(SELECT_TURNOSDEOPERATIVO_BY_ID_OPERATIVO);) {
            ps.setInt(1, idTurno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fechaTurno = rs.getString("FechaTurno");
                String fechaCreacion = rs.getString("FechaCreacion");
                String nombreTipo = rs.getString("NombreTipo");
                String medicosEnTurno = DAOEmpleadoEnTurno.consultaMedicosEnTurno(idTurno);
                String conductoresEnTurno = DAOEmpleadoEnTurno.consultaConductoresEnTurno(idTurno);

                entryJSON = mapEntryAsJSON(nombreTipo, fechaTurno, fechaCreacion,medicosEnTurno,conductoresEnTurno);
            }

            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(DAOTurnoDeOperativo.class.getName()).log(Level.SEVERE, "Error executing query.", e);
            connection.closeConnection();
        }
        connection.closeConnection();
        return entryJSON;
    }


    private static String mapEntryAsJSON(String nombreTipo, String fechaTurno, String fechaCreacion,String medicosEnTurno,String conductoresEnTurno) {
        
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("NombreTipo", nombreTipo)
                .add("FechaTurno", fechaTurno)
                .add("FechaCreacion", fechaCreacion)
                .add("MedicosEnTurno",medicosEnTurno)
                .add("ConductoresEnTurno",conductoresEnTurno)
                .build();
        try (
                StringWriter stringWriter = new StringWriter(); JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOTurnoDeOperativo.class.getName()).log(Level.SEVERE, "Error creating EntryJSON.", ex);
        }
        return entryJSON;

    }
}
