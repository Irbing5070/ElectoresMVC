package mx.org.irbing.inemvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mx.org.irbing.inemvc.dao.ElectorDAO;
import mx.org.irbing.inemvc.model.Elector;


/**
 * Created by Irbing on 10/03/2018.
 */

public class GestionElectorActivity extends AppCompatActivity {

    //Se declaran los controles a recuperar de la vista
    private EditText txtIdElector;
    private EditText txtNombre;
    private EditText txtVoto;
    private EditText txtEdad;
    private EditText txtFechaVotacion;
    private Button btnActualizar;
    private Button btnCancelar;
    private Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //Se indica la vista a cargar
        setContentView(R.layout.activity_gestion_elector);
        //1. Se relacionan los controles de la actividad con los controles de la vista
        txtIdElector = (EditText)findViewById(R.id.txt_id_elector);
        txtNombre = (EditText)findViewById(R.id.txt_nombre);
        txtVoto = (EditText)findViewById(R.id.txt_voto);
        txtEdad = (EditText)findViewById(R.id.txt_edad);
        txtFechaVotacion = (EditText)findViewById(R.id.txt_fecha_votacion);
        btnActualizar = (Button)findViewById(R.id.btn_actualizar);
        btnCancelar = (Button)findViewById(R.id.btn_cancelar);
        btnEliminar = (Button)findViewById(R.id.btn_eliminar);
        //2. Se obtiene el valor CODIGO_DE_BARRAS de un bundle de la actividad
        String id_elector = getIntent().getExtras().getString("idElector");
        //Toast.makeText(getApplicationContext(), "Error: " + codigoBarras, Toast.LENGTH_SHORT).show();
        //3. Se crea un objeto Elector
        Elector el = new Elector();
        //Toast.makeText(getApplicationContext(), "Error: " + p.getIdElector(), Toast.LENGTH_SHORT).show();
        //4. Se asigna el codigo de barras del bundle al objeto para buscarlo en la BD
        el.setIdElector(id_elector);
        //Toast.makeText(getApplicationContext(), "Error: " + p.getIdElector(), Toast.LENGTH_SHORT).show();
        //5. Se crea un objeto DAO para buscar el Elector
        ElectorDAO dao = new ElectorDAO(getApplicationContext());
        try {
            el = dao.getById(el);
            //Se asigna el valor a los controles de acuerdo al valor de los atributos del objeto
            txtIdElector.setText(el.getIdElector());
            txtNombre.setText(el.getNombre());
            txtVoto.setText(el.getVoto());
            txtEdad.setText(String.valueOf(el.getEdad()));
            txtFechaVotacion.setText(el.getFechaVotacion());
        } catch (Exception e) {
            //En caso de excepción se muestra el mensaje
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        //6. Se agrega un escuchador de eventos al btnActualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Se crea un objeto Elector
                Elector p = new Elector();
                //Se asignan los atributos del objeto
                p.setIdElector(txtIdElector.getText().toString());
                p.setNombre(txtNombre.getText().toString());
                p.setVoto(txtVoto.getText().toString());
                p.setEdad(Integer.valueOf(txtEdad.getText().toString()));
                p.setFechaVotacion(txtFechaVotacion.getText().toString());
                //Se crea un objeto DAO para almacenar el objeto
                ElectorDAO dao = new ElectorDAO(getApplicationContext());
                try {
                    //Se trata de insertar el objeto
                    dao.update(p);
                    //Se muestra un mensaje ÃƒÂ©xito y se cierra la vista
                    Toast.makeText(getApplicationContext(), "Elector actualizado", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                } catch (Exception e) {
                    //En caso de excepción se muestra el mensaje
                    Toast.makeText(getApplicationContext(), "Error Update: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //7. Se agrega un escuchador de eventos al btnEliminar
        btnEliminar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Se crea un objeto Elector
                Elector p = new Elector();
                //Se asignan los atributos del objeto
                p.setIdElector(txtIdElector.getText().toString());
                //Se crea un objeto DAO para almacenar el objeto
                ElectorDAO dao = new ElectorDAO(getApplicationContext());
                try {
                    //Se trata de insertar el objeto
                    dao.delete(p);
                    //Se muestra un mensaje de éxito y se cierra la vista
                    Toast.makeText(getApplicationContext(), "Elector eliminado", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                } catch (Exception e) {
                    //En caso de excepción se muestra el mensaje
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //8. Se asigna escuchador de clics al btnCancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Se cierra la vista
                System.exit(0);
            }
        });
    }


}
