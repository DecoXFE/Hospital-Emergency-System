/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio.controladoresCU;

import serviciosComunes.exceptions.ActivacionesNoEncontradasException;
import serviciosComunes.exceptions.OperativoNoEncontradoException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;
import javax.json.JsonValue;
import negocio.modelos.operativo.Activacion;
import negocio.modelos.persona.Empleado;
import negocio.modelos.operativo.Operativo;
import negocio.modelos.Session;
import negocio.modelos.operativo.filtroStrategy.*;
import persistencia.daos.operativo.DAOOperativo;
import persistencia.daos.operativo.DAOActivacion;
import persistencia.exceptions.DBConnectionException;

/**
 *
 * @author diego
 */
public class ControladorCUConsultarEmergencias {

    private Operativo operativoConsultado = null;
    private ArrayList<Activacion> activaciones = null;

    public void identificarMiOperativoPorFechaTurno(LocalDate fecha) throws OperativoNoEncontradoException {

        Empleado e = Session.getInstance().getEmpleado();
        String d = e.getNif();
        try{
            String jsonOperativo = DAOOperativo.consultaOperativoPorNifYFechaTurno(d, fecha);
        if (!jsonOperativo.equals("")) {
            operativoConsultado = new Operativo(jsonOperativo);
            definirActivaciones();
        } else {
            throw new OperativoNoEncontradoException("No has estado en un operativo en dicha fecha");
        }
        }catch(DBConnectionException dbe){
            Logger.getLogger(ControladorCUConsultarEmergencias.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOOperativo.", dbe);
            throw new OperativoNoEncontradoException("No se ha encontrado operativo en turno por error en conexión a base de datos",dbe);
        }catch(ActivacionesNoEncontradasException ane){
            Logger.getLogger(ControladorCUConsultarEmergencias.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOActivacion.", ane);
            throw new OperativoNoEncontradoException("No se ha encontrado operativo en turno por error en conexión a base de datos",ane);
        }

    }

    public Operativo getOperativoConsultado() {
        return operativoConsultado;
    }

    public void setOperativoConsultado(Operativo operativoConsultado) {
        this.operativoConsultado = operativoConsultado;
    }

    private void definirActivaciones() throws ActivacionesNoEncontradasException {
        activaciones = new ArrayList<>();

        int idOperativo = operativoConsultado.getId();
        try{
            String activacionesString = DAOActivacion.consultaActivacionesPorIdOperativo(idOperativo);

        JsonReaderFactory factory = Json.createReaderFactory(null);
        JsonReader reader = factory.createReader(new StringReader(activacionesString));
        JsonArray activacionesJSON = reader.readArray();

        for (JsonValue v : activacionesJSON) {
            Activacion a = new Activacion(v.toString());
            activaciones.add(a);
        }
        }catch(DBConnectionException e){
            Logger.getLogger(ControladorCUConsultarEmergencias.class.getName()).log(Level.SEVERE, "Error on DBConnection from DAOActivacion.", e);
            throw new ActivacionesNoEncontradasException("No encontradas activaciones para el operativo debido a error en DBConnection",e);
        }

    }

    public ArrayList<String> getDatosActivaciones(String filtroString) {
        ArrayList<String> datosActivaciones;
        FiltroActivacion filtroStrat;
        FiltroContext filtroContext;
        switch (filtroString) {
            default:
            case ("Ambas activaciones"):
                filtroStrat = new FiltroTodas();
                break;
            case ("Con traslado"):
                filtroStrat = new FiltroConTraslado();
                break;
            case ("Sin traslado"):
                filtroStrat = new FiltroSinTraslado();
                break;
        }
        filtroContext = new FiltroContext(filtroStrat);
        datosActivaciones = filtroContext.filtrarActivaciones(activaciones);
        return datosActivaciones;
    }

    public Boolean operativoEncontrado() {
        return operativoConsultado != null;
    }

    public String[] conseguirDatosCompletosActivacion(String[] datosActivacionSeleccionada) {
        String[] datosCompletos = new String[8];
        int id = Integer.parseInt(datosActivacionSeleccionada[0]);
        Activacion a = buscarActivacionPorId(id);
        datosCompletos[0] = datosActivacionSeleccionada[0];
        datosCompletos[1] = a.getDireccionDondeAcudir().getNombreVia();
        datosCompletos[2] = datosActivacionSeleccionada[1];
        datosCompletos[3] = datosActivacionSeleccionada[2];
        if (a.seHizoCargo()) {
            datosCompletos[4] = a.getFechaSeHaceCargoMedico().toString();
            datosCompletos[5] = a.getHoraSeHaceCargoMedico().toString();
        } else {
            datosCompletos[4] = "No se hizo cargo ningún médico";
            datosCompletos[5] = "No se hizo cargo ningún médico";
        }
        if (a.huboTraslado()) {
            datosCompletos[6] = "Se produjo un traslado a un hospital";
        } else {
            datosCompletos[6] = "No hubo ningún traslado a un hospital";
        }
        datosCompletos[7] = a.getLlamadaNoCritica().getDescripcionDeLaEmergencia();

        return datosCompletos;

    }

    private Activacion buscarActivacionPorId(int idBuscado) {
        for (Activacion activacion : activaciones) {
            if (activacion.getIdActivacion() == idBuscado) {
                return activacion;
            }
        }
        return null; // Retorna null si no se encuentra ninguna activación con el id buscado
    }

}
