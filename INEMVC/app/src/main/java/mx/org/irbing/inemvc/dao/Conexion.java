package mx.org.irbing.inemvc.dao;

/**
 * Created by Irbing on 03/03/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Conexion {
    //Se define una varible Context. A partir de ésta se crea la Base de datos
    private Context contexto;

    /**
     * método constructor que inicializa la variable contexto
     * @param contexto
     */
    public Conexion(Context contexto) {
        this.contexto = contexto;
    }

    /**
     * método para abrir la conexióna la base de datos
     * @return
     */
    private SQLiteDatabase abrirConexion(){
        //Se crea un objeto SQLiteDatabase
        SQLiteDatabase conexion = contexto.openOrCreateDatabase(
                "ine.db", //NOmbre de la BD
                SQLiteDatabase.OPEN_READWRITE,//Modo de apertura
                null);//Factory para la BD
        //se regresa el objeto SQLiteDatabase
        return conexion;
    }

    /**
     * método para cerrar la conexión con la base de datos
     * @param conexion
     */
    private void cerrarConexion(SQLiteDatabase conexion){
        //Si existe una conexión
        if(conexion != null){
            //Se cierra la conexión
            conexion.close();
        }
    }

    /**
     * método para ejecutar sentencias de SQL(Insert, Update, Delete)
     * @param sentencia
     * @return
     * @throws Exception
     */
    public boolean ejecutarSentencia(String sentencia) throws Exception{
        //Se abre la conexióncon la base de datos y se almacena
        SQLiteDatabase conexion = abrirConexion();
        try {
            //Se ejecuta la sentencia SQL sobre la conexión
            conexion.execSQL(sentencia);
            //Se cierra la conexión
            conexion.close();
        } catch (Exception e) {
            //Lanzamos la excepción aquien invoca el método para que el lo maneje
            throw new Exception("Error al ejecutar la sentencia: " + e.getMessage());
        }
        //Si no ha habido ningún error se retorna true
        return true;
    }

    /**
     * método para ejecutar consultas SQL (Select)
     * @param tabla
     * @param campos
     * @param condicion
     * @return
     * @throws Exception
     */
    public List<HashMap<String, String>> ejecutarConsulta(String tabla, String campos[], String condicion) throws Exception{
        //Se crea una lista de objetos HashMap, donde cada Hashmap representarÃ¡ un objeto de la BD
        List<HashMap<String, String>> datos = new ArrayList<HashMap<String,String>>();
        try {
            //Se abre la conexióna la base de datos y se almacena
            SQLiteDatabase conexion = abrirConexion();
            //Se consulta de la tabla indicada los campos indicados de acuerdo a la condición
            Cursor resultado = conexion.query(tabla, campos, condicion, null, null, null, null);
            //Se crea un HashMap para formar un registro de la base de datos
            HashMap<String, String> registro;
            //Se itera sobre cada uno de los registros de la consulta
            while (resultado.moveToNext()){
                //Por cada registro en la Base de Datos se crea un HashMap
                registro = new HashMap<String, String>();
                //Se recorre cada uno de los campos solicitadosen la consulta
                for(int i = 0; i < campos.length; i++){
                    //Por cada elemento cmpo se inserta el nombre y el valor en el HashMap
                    registro.put(campos[i], resultado.getString(i));
                }
                //Se agrega el registro (grupo de campos y valores) en la lista
                datos.add(registro);
            }
            //Se cierra la conexión
            conexion.close();
        } catch (Exception e) {
            //Lanzamos la excepción aquien invoca el método para que el lo maneje
            throw new Exception("Error al ejecutar la consulta: " + e.getMessage());
        }
        //Si no ha habido ningún error se retorna el resultado de la consulta
        return datos;
    }

    /**
     * método para inicializar (crear estructura) de la base de datos
     * @throws Exception
     */
    public void inicializaBD() throws Exception{
        //Se abre la conexióna la base de datos
        SQLiteDatabase conexion = abrirConexion();
        //Se ejecuta sentencia para borrar la tabla de electores si es que ya existe
        conexion.execSQL("DROP TABLE IF EXISTS ELECTORES");
        //Se ejecuta la sentencia para crear la tabla electores
        conexion.execSQL("CREATE TABLE ELECTORES(idElector TEXT, nombre TEXT, voto TEXT, edad  INTEGER, fecha_votacion TEXT)");
        //Se cierra la conexión
        conexion.close();
    }

}

