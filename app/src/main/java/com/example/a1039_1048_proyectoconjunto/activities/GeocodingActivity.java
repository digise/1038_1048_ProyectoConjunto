package com.example.a1039_1048_proyectoconjunto.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GeocodingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // https://geocode.xyz/51.50354,-0.12768?geoit=json&auth=57673066339488579050x115589 --> latitud longitud
    // https://geocode.xyz/sagunto?json=1&auth=57673066339488579050x115589

    private final String url = "https://geocode.xyz/";
    private final String auth = "57673066339488579050x115589";

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

    public void getWeatherDetailsByCoords(View view){
        addCiudad("coordenadas");
    }

    public void getWeatherDetailsByName(View view){
        addCiudad("toponimo");
    }

    public void addCiudad(String tipo){
        //Aqui en vez de texto habría que poner toda la movida de conseguir la ubicacion.
        String toponimoCoords = etCity.getText().toString().trim();
        String tempUrl = "";
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
/*
        if (toponimoCoords.length() > 0){
            tempUrl = url + toponimoCoords;
            if (tipo.equals("coordenadas")){
                tempUrl += "?geoit=json&auth=" + auth;
            }else{
                tempUrl += "?json=1&auth=" + auth;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        HashMap<String, String> datosGeocoding = geocodingAdapter.JsonToHahsMap(new JSONObject(response));
                        String nombreCiudad = datosGeocoding.get("nombre");
                        String nombreComunidad = datosGeocoding.get("comunidad");
                        String nombrePais = datosGeocoding.get("pais");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });

        }*/

        ciudades.add(toponimoCoords);
        etCity.getText().clear();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ciudades);
        lvNuevasUbicaciones.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "clicked item: " + position, Toast.LENGTH_SHORT).show();
    }
}
