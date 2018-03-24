package mx.org.irbing.inemvc.dao;

/**
 * Created by Irbing on 03/03/2018.
 */

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.org.irbing.inemvc.model.Elector;


public class ElectorDAO {
    private Context contexto;

    /**
     * Método constructor que inicializa la variable contexto
     * @param contexto
     */
    public ElectorDAO(Context contexto) {
        this.contexto = contexto;
    }

    /**
     * Método para insertar un objeto Elector en la BD
     * @param obj
     */
    public void insert(Elector obj) throws Exception{
        //Se crea la sentencia a ejecutar en la base de datos
        String comando = "INSERT INTO ELECTORES(idElector, nombre, voto, edad, fecha_votacion) " +
                "VALUES('" + obj.getIdElector() + "', '" + obj.getNombre() + "', '" + obj.getVoto() +
                "', " + obj.getEdad() + ",'" + obj.getFechaVotacion() + "' )";
        //Se crea un objeto Conexion
        Conexion con = new Conexion(contexto);
        try {
            //Se ejecuta la sentencia
            con.ejecutarSentencia(comando);
        } catch (Exception e) {
            //Lanzamos la exepción aquien invoca el método para que el lo maneje
            throw new Exception("Error al insertar: " + e.getMessage());
        }
    }

    /**
     * MÃ©todo para actualizar un objeto Elector en la BD
     * @param obj
     */
    public void update(Elector obj) throws Exception{
        //Se crea la sentencia a ejecutar en la base de datos
        String comando = "UPDATE ELECTORES SET " +
                "nombre='" + obj.getNombre() + "'," +
                "voto='" + obj.getVoto() + "'," +
                "edad=" + obj.getEdad() + "," +
                "fecha_votacion='" + obj.getFechaVotacion() + "' " +
                "WHERE idElector='" + obj.getIdElector() + "'";
        //Se crea un objeto Conexion
        Conexion con = new Conexion(contexto);
        try {
            //Se ejecuta la sentencia
            con.ejecutarSentencia(comando);
        } catch (Exception e) {
            //Lanzamos la excepción aquien invoca el método para que el lo maneje
            throw new Exception("Error al actualizar: " + e.getMessage());
        }
    }

    /**
     * MÃ©todo para eliminar un objeto Elector en la BD
     * @param obj
     */
    public void delete(Elector obj) throws Exception{
        //Se crea la sentencia a ejecutar en la base de datos
        String comando = "DELETE FROM ELECTORES WHERE idElector='" + obj.getIdElector() + "'";
        //Se crea un objeto Conexion
        Conexion con = new Conexion(contexto);
        try {
            //Se ejecuta la sentencia
            con.ejecutarSentencia(comando);
        } catch (Exception e) {
            //Lanzamos la excepción aquien invoca el método para que el lo maneje
            throw new Exception("Error al eliminar: " + e.getMessage());
        }
    }

    /**
     * Método para listar todos los objetos Elector de la BD
     * @return
     */
    public List<Elector> getAll() throws Exception{
        //Se especifica el nombre de la tabla a consultar
        String tabla = "ELECTORES";
        //Se indica los campos a consultar de la tabla
        String campos[] = new String[]{"idElector","nombre","voto","edad","fecha_votacion"};
        //Se crea una lista para almacenar los objetos elector almacenados en la tabla
        List<Elector> listaElectors = new ArrayList<>();
        //Se abre la conexión a la BD
        Conexion con = new Conexion(contexto);
        //Se consulta mediante la conexiÃ³n todos los registros y campos de la tabla
        List<HashMap<String, String>> resultado = con.ejecutarConsulta(tabla, campos, null);

        //Se crea una referencia a un objeto Elector
        Elector elec;
        //se recorre cada uno de los registros regresados de la consulta.
        for (HashMap<String, String> reg : resultado) {
            //Por cada registro se crea un objeto Elector
            elec = new Elector();
            //Se asigna cada uno de los atributos del objeto elector.
            elec.setIdElector(reg.get("idElector"));
            elec.setNombre(reg.get("nombre"));
            elec.setVoto(reg.get("voto"));
            elec.setEdad(Integer.valueOf(reg.get("edad")));
            elec.setFechaVotacion(reg.get("fecha_votacion"));
            //Se inserta el objeto al elector en la lista
            listaElectors.add(elec);
        }
        return listaElectors;
    }

    /**
     * MÃ©todo para buscar un objeto Elector por su ID en la BD
     * @param obj
     * @return
     */
    public Elector getById(Elector obj) throws Exception{
        //Se especifica el nombre de la tabla a consultar
        String tabla = "ELECTORES";
        //Se indica los campos a consultar de la tabla
        String campos[] = new String[]{"idElector","nombre","voto","edad","fecha_votacion"};
        //Se especifica la condiciÃ³n para realizar la consulta.
        String condicion = "idElector = '" + obj.getIdElector() + "'";
        //Se abre la conexiÃ³n a la BD
        Conexion con = new Conexion(contexto);
        //Se consulta mediante la conexiÃ³n todos los registros y campos de la tabla
        List<HashMap<String, String>> resultado = con.ejecutarConsulta(tabla, campos, condicion);
        //Se crea una referencia a un objeto Elector
        Elector elec = null;
        //se recorre cada uno de los registros regresados de la consulta.
        for (HashMap<String, String> reg : resultado) {
            //Por cada registro se crea un objeto Elector
            elec = new Elector();
            //Se asigna cada uno de los atributos del objeto elector.
            elec.setIdElector(reg.get("idElector"));
            elec.setNombre(reg.get("nombre"));
            elec.setVoto(reg.get("voto"));
            elec.setEdad(Integer.valueOf(reg.get("edad")));
            elec.setFechaVotacion(reg.get("fecha_votacion"));

        }
        //Se retorna el objeto elector
        return elec;
    }
}
