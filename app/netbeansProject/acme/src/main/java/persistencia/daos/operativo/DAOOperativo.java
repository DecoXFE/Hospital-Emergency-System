package persistencia.daos.operativo;

import persistencia.daos.persona.DAOEmpleado;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import persistencia.DBAccess.DBConnection;
import persistencia.exceptions.DBConnectionException;

public class DAOOperativo {

    private static final String SELECT_OPERATIVO_BY_NIF_AND_DATE = "SELECT O.*, E.NombreEstado,T.id as IdTurno FROM OPERATIVOS O INNER JOIN TURNOSDEOPERATIVO T ON O.turno = T.id INNER JOIN TIPOSDETURNODEOPERATIVO TT ON TT.idTipo =  T.tipoDeTurno INNER JOIN ESTADOSDEOPERATIVO E ON E.idEstado = O.Estado WHERE O.Conductor = ? OR O.Medico = ? AND T.FechaTurno = ?";
            
    public static String consultaOperativoPorNifYFechaTurno(String nif, LocalDate date) throws DBConnectionException{
        String entryJSON = "";
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        ResultSet rs;
        try (PreparedStatement ps = connection.getStatement(SELECT_OPERATIVO_BY_NIF_AND_DATE);) {
            ps.setString(1, nif);
            ps.setString(2, nif);
            ps.setDate(3, Date.valueOf(date));
            rs = ps.executeQuery();
            if (rs.next()) {
                int idOperativo = rs.getInt("Id");

                Date fechaCreacionOperativo = rs.getDate("FechaCreacion");
                String nombreEstado = rs.getString("NombreEstado");

                short base = rs.getShort("base");
                String consultorio = DAOConsultorio.consultaConsultorioPorID(base);

                String matricula = rs.getString("Vehiculo");
                String vehiculo = DAOVehiculo.consultaVehiculoPorMatricula(matricula);

                String nifConductor = rs.getString("Conductor");
                String nifMedico = rs.getString("Medico");

                String conductor = DAOEmpleado.consultaEmpleadoPorNif(nifConductor);
                String medico = DAOEmpleado.consultaEmpleadoPorNif(nifMedico);

                int idTurno = rs.getInt("idTurno");
                String turnoOperativo = DAOTurnoDeOperativo.consultaTurnoOperativoPorIDTurno(idTurno);
                
                entryJSON = mapEntryAsJSON(idOperativo, fechaCreacionOperativo, nombreEstado, consultorio, vehiculo, medico, conductor,turnoOperativo);

            }
            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(DAOOperativo.class.getName()).log(Level.SEVERE, "Error executing query.", e);
            connection.closeConnection();
        }
        connection.closeConnection();

        return entryJSON;

    }
    
    private static String mapEntryAsJSON(int idOperativo, Date fechaCreacionOperativo, String nombreEstado, String consultorio, String vehiculo, String medico, String conductor,String turnoOperativo) {

        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("IdOperativo",idOperativo)
                .add("FechaCreacion", fechaCreacionOperativo.toString())
                .add("Estado", nombreEstado)
                .add("Consultorio", consultorio)
                .add("Vehiculo", vehiculo)
                .add("Conductor", conductor)
                .add("Medico", medico)
                .add("TurnoDeOperativo", turnoOperativo)
                .build();
        try (
                StringWriter stringWriter = new StringWriter(); JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOOperativo.class.getName()).log(Level.SEVERE, "Error creating EntryJSON.", ex);
        }
        return entryJSON;
    }

}
