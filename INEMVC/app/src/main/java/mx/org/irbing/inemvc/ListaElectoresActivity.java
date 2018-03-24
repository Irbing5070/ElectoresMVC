package mx.org.irbing.inemvc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mx.org.irbing.inemvc.dao.ElectorDAO;
import mx.org.irbing.inemvc.model.Elector;


/**
 * Created by Irbing on 10/03/2018.
 */

public class ListaElectoresActivity extends AppCompatActivity{
    //Se declaran los controles a relacionar de la vista
    private Button btnNuevo;
    private ListView lsvElectores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_electores);
        //1. Se relacionan los controles de la vista con los controles de la actividad
        btnNuevo = (Button)findViewById(R.id.btn_agregar_elector);
        lsvElectores = (ListView)findViewById(R.id.lsv_electores);
        //2. Se asigna un escuhador de clics al btnNuevo
        btnNuevo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //Se crea un intento para iniciar la ActividadNuevoElector
                Intent intNueElec = new Intent(getApplicationContext(),
                        NuevoElectorActivity.class);
                //Se arranca la actividad
                startActivity(intNueElec);
            }
        });
        //3. Se crea un objeto ElectorDAO para el acceso a los datos
        ElectorDAO dao = new ElectorDAO(getApplicationContext());
        //4. se consulta la lista de electores almacenada y se le asigna a una variable
        List<Elector> listaElectores = new ArrayList<Elector>();

        try {
            //Se ejecuta la consulta
            listaElectores = dao.getAll();
        } catch (Exception e) {
            //En caso de haber alguna excepción esta se muestra mediante un mensaje
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //5. Se crea una lista que almacenará objetos HashMap, donde cada HashMap tendrá los datos
        //a visualizarse en la lista por cada objeto
        List<HashMap<String, String>> filas = new ArrayList<HashMap<String,String>>();
        //6. Se declara un objeto HashMap que almacenará los datos a mostrar en
        //el ListView por cada objeto en la lista.
        HashMap<String, String> registro;
        //7. Se recorre la lista de objetos elector
        for (Elector prod : listaElectores) {
            //Por cada objeto Elector en la lista se crea un HashMap para
            //asignar sus datos en cada vista del registro
            registro = new HashMap<String, String>();
            //Se agregan los datos al HashMap a mostrar en la vista
            registro.put("idElector", prod.getIdElector());
            registro.put("nombre", prod.getNombre());
            registro.put("edad", String.valueOf(prod.getEdad()));
            registro.put("voto", prod.getVoto());
            //Se agrega el HashMap a la lista
            filas.add(registro);
        }
        //8. Se crea un adaptador para mostrar los datos en el ListView
        SimpleAdapter adaptador = new SimpleAdapter(
                getApplicationContext(), //se pasa el contexto de la aplicación al adaptador
                filas, //Se indica la lista de registros a cargar en la vista
                R.layout.activity_registro_elector, //Se indica la vista a crear por cada HashMap
                new String[]{"idElector", "nombre", "edad", "voto"}, //Se indica los datos a cargar por cada HashMap
                new int[]{R.id.txt_id_elector2,R.id.txt_nombre2,R.id.txt_edad2,R.id.txt_voto2}); //Se indican los controles de la vista donde se cargarán los valores del HashMap
        //9. Se asigna el adaptador a la ListView
        lsvElectores.setAdapter(adaptador);
        //10. Se asigna un escuchador de clics a los items de la ListView
        lsvElectores.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Se obtiene el control que contiene el codigo de barras, del elemento seleccionado de la lista
                TextView txtIdElector = (TextView)arg1.findViewById(R.id.txt_id_elector2);

                //Se crea un objeto bundle
                Bundle bundle = new Bundle();
                //Se inserta en el Bundle el codigo de barras del objeto seleccionado
                bundle.putString("idElector", txtIdElector.getText().toString());
                //Se crea un objeto Intent para iniciar la ActividadGestionElector
                Intent intGesPro = new Intent(getApplicationContext(), GestionElectorActivity.class);
                //Se inserta el bundle en el intento
                intGesPro.putExtras(bundle);
                //Se inicia la actividad
                startActivity(intGesPro);
            }
        });

    }
}
