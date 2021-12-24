package com.example.a1039_1048_proyectoconjunto.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import java.util.ArrayList;
import java.util.List;

public class GeocodingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // https://geocode.xyz/51.50354,-0.12768?geoit=json&auth=57673066339488579050x115589 --> latitud longitud
    // https://geocode.xyz/sagunto?json=1&auth=57673066339488579050x115589

    private Button btnAnadirByToponimo;
    private Button btnAnadirByCoords;
    private EditText etCity;
    private ListView lvNuevasUbicaciones;

    private List<String> ciudades = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAnadirByCoords = findViewById(R.id.button_getByCoords);
        btnAnadirByToponimo = findViewById(R.id.button_getByName);
        etCity = findViewById(R.id.etCity);
        lvNuevasUbicaciones = findViewById(R.id.listView_nuevasUbicaciones);
        lvNuevasUbicaciones.setOnItemClickListener(this);
    }

    public void getUbicacionPorCoords(View view){
        String toponimoCoords = etCity.getText().toString().trim();
        getUbicacionPorCoords(toponimoCoords);
    }

    public void getUbicacionPorNombre(View view){
        String toponimoCoords = etCity.getText().toString().trim();
        getUbicacionPorNombre(toponimoCoords);
    }

    public void getUbicacionPorCoords(String toponimoCoords){
        Gestor gestor = createGestorGeocoding();
        gestor.getGestorServicios().getServicioGeocoding().getInformacionPorCoordenadas(toponimoCoords);
    }

    public void getUbicacionPorNombre(String toponimoCoords){
        Gestor gestor = createGestorGeocoding();
        gestor.getGestorServicios().getServicioGeocoding().getInformacionPorCoordenadas(toponimoCoords);

    }

    private Gestor createGestorGeocoding(){
        Gestor gestor = new Gestor();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
        gestor.setContexto(getApplicationContext());
        return gestor;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "clicked item: " + position, Toast.LENGTH_SHORT).show();
    }
}
