/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.daos.operativo;

import persistencia.daos.llamada.DAOLlamadaNoCritica;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import persistencia.DBAccess.DBConnection;
import persistencia.daos.general.DAODireccion;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class DAOActivacion {

    private static final String SELECT_ACTIVACIONES_POR_ID_OPERATIVO = "SELECT * FROM ActivacionesDeOperativos WHERE OperativoActivado = ?";

    public static String consultaActivacionesPorIdOperativo(int idOperativo) throws DBConnectionException{
        String entryJSON = "";
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        ResultSet rs;
        try (PreparedStatement ps = connection.getStatement(SELECT_ACTIVACIONES_POR_ID_OPERATIVO);) {
            ps.setInt(1, idOperativo);
            rs = ps.executeQuery();
            while (rs.next()) {
                short direccionId = rs.getShort("DireccionDondeAcudir");
                String direccionDondeAcudir = DAODireccion.getDireccionPorId(direccionId);
                
                String fechaActivacion = rs.getString("FechaActivación");
                String horaActivacion = rs.getString("HoraActivación");

                String fechaSeHaceCargoMedico = rs.getString("FechaSeHaceCargoMedico");
                String horaSeHaceCargoMedico = rs.getString("HoraSeHaceCargoMedico");
                String fechaCierre = rs.getString("FechaCierre");
                String horaCierre = rs.getString("HoraCierre");

                int trasladoID = rs.getInt("DecisionTrasladoAHospital");
                String trasladoHospital = DAOTraslado.consultaTrasladoPorID(trasladoID);
                
                int id = rs.getInt("Id");
                String llamadaNoCritica = DAOLlamadaNoCritica.consultaLlamadaPorIdActivacion(id);

                if (fechaSeHaceCargoMedico == null) {
                    fechaSeHaceCargoMedico = "";
                }
                if (horaSeHaceCargoMedico == null) {
                    horaSeHaceCargoMedico = "";
                }
                if (fechaCierre == null) {
                    fechaCierre = "";
                }
                if (horaCierre == null) {
                    horaCierre = "";
                }
                
                
                String activacionJSON = mapEntryAsJSON(direccionDondeAcudir, fechaActivacion, horaActivacion, fechaSeHaceCargoMedico, horaSeHaceCargoMedico, fechaCierre, horaCierre, trasladoHospital,llamadaNoCritica,id);

                arrayBuilder.add(Json.createReader(new StringReader(activacionJSON)).readObject());

            }
            JsonArray jsonArray = arrayBuilder.build();
            StringWriter stringWriter = new StringWriter();
            JsonWriter writer = Json.createWriter(stringWriter);
            writer.write(jsonArray);
            entryJSON = stringWriter.toString();

            rs.close();
        } catch (SQLException e) {
            Logger.getLogger(DAOActivacion.class.getName()).log(Level.SEVERE, "Error executing query.", e);
            connection.closeConnection();
        }
        connection.closeConnection();
        return entryJSON;
    }

    private static String mapEntryAsJSON(String direccionDondeAcudir, String fechaActivacion, String horaActivacion, String fechaSeHaceCargoMedico, String horaSeHaceCargoMedico, String fechaCierre, String horaCierre, String trasladoHospital,String llamadaNoCritica,int idActivacion) {
        String entryJSON = "";
        JsonObject json = Json.createObjectBuilder()
                .add("DireccionDondeAcudir", direccionDondeAcudir)
                .add("FechaActivacion", fechaActivacion)
                .add("HoraActivacion", horaActivacion)
                .add("FechaSeHaceCargoMedico", fechaSeHaceCargoMedico)
                .add("HoraSeHaceCargoMedico", horaSeHaceCargoMedico)
                .add("FechaCierre", fechaCierre)
                .add("HoraCierre", horaCierre)
                .add("TrasladoHospital", trasladoHospital)
                .add("LlamadaNoCritica",llamadaNoCritica)
                .add("IdActivacion",idActivacion)
                .build();
        try (
                StringWriter stringWriter = new StringWriter(); JsonWriter writer = Json.createWriter(stringWriter);) {
            writer.writeObject(json);
            entryJSON = stringWriter.toString();
        } catch (Exception ex) {
            Logger.getLogger(DAOActivacion.class.getName()).log(Level.SEVERE, "Error creating EntryJSON.", ex);
        }
        return entryJSON;
    }
}
