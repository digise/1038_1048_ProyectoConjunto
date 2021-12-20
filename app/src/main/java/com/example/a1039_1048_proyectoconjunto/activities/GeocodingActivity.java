package com.example.a1039_1048_proyectoconjunto.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1039_1048_proyectoconjunto.R;

import java.util.ArrayList;
import java.util.List;

public class GeocodingActivity extends AppCompatActivity implements View.OnClickListener {

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
    }

    public void getWeatherDetailsByCoords(View view){
        addCiudad();
    }

    public void getWeatherDetailsByName(View view){
        addCiudad();
    }

    public void addCiudad(){
        //Aqui en vez de texto habría que poner toda la movida de conseguir la ubicacion.
        String texto = etCity.getText().toString().trim();

        ciudades.add(texto);
        etCity.getText().clear();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ciudades);
        lvNuevasUbicaciones.setAdapter(adapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        /*
        switch (v.getId()){
            case R.id.button_getByCoords:
                addCiudad();
                break;
        }
         */
    }
}
