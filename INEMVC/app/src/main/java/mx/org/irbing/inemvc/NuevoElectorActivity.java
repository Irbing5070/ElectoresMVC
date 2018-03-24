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

public class NuevoElectorActivity extends AppCompatActivity{

    //Se declaran los controles a recuperar de la vista
    private EditText txtIdElector;
    private EditText txtNombre;
    private EditText txtVoto;
    private EditText txtEdad;
    private EditText txtFechaVotacion;
    private Button btnGuardar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_elector);
        //1. Se relacionan los controles de la actividad con los controles de la vista
        txtIdElector = (EditText)findViewById(R.id.txt_id_elector);
        txtNombre = (EditText)findViewById(R.id.txt_nombre);
        txtVoto = (EditText)findViewById(R.id.txt_voto);
        txtEdad = (EditText)findViewById(R.id.txt_edad);
        txtFechaVotacion = (EditText)findViewById(R.id.txt_fecha_votacion);
        btnGuardar = (Button)findViewById(R.id.btn_guardar);
        btnCancelar = (Button)findViewById(R.id.btn_cancelar);
        //2. Se asigna escuchador de clics al btnGuardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "datos: " , Toast.LENGTH_LONG);
                //Se crea un objeto Producto
                Elector el = new Elector();
                //Se asignan los atributos del objeto
                el.setIdElector(txtIdElector.getText().toString());
                el.setNombre(txtNombre.getText().toString());
                el.setVoto(txtVoto.getText().toString());
                el.setEdad(Integer.valueOf(txtEdad.getText().toString()));
                el.setFechaVotacion(txtFechaVotacion.getText().toString());
                //Se crea un objeto DAO para almacenar el objeto
                Toast.makeText(getApplicationContext(), "datos: " + el.toString() , Toast.LENGTH_LONG);
                ElectorDAO dao = new ElectorDAO(getApplicationContext());
                try {
                    //Se trata de insertar el objeto
                    dao.insert(el);
                    //Se muestra un mensaje éxito y se cierra la vista
                    Toast.makeText(getApplicationContext(), "Elector guardado", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                } catch (Exception e) {
                    //En caso de excepciÃƒÂ³n se muestra el mensaje
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //3. Se asigna escuchador de clics al btnCancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Se cierra la vista
                System.exit(0);
            }
        });
    }


}
