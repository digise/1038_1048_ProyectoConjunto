package com.example.a1039_1048_proyectoconjunto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.google.android.material.textfield.TextInputLayout;

import java.net.URL;
import java.util.HashMap;

public class UbicacionActividad extends AppCompatActivity {

    private Ubicacion ubicacion;
    private Gestor gestor;
    private HashMap<String, String> informacionOpenweather;
    private HashMap<String, HashMap<String, String>> informacionCurrents;

    private TextInputLayout aliasView;
    private TextView toponimoView;
    private TextView coordenadasView;

    private CheckBox openWeatherBox;
    private CheckBox currentBox;
    private CheckBox activadaBox;
    private CheckBox favoritaBox;
    private TextView informacionUbicacionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        Bundle bundle = getIntent().getExtras();
        String toponimo = "";
        if (bundle != null)
            toponimo = bundle.getString("codigoUbicacion");

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        // Recopilacion de datos

        gestor = Gestor.getInstance();
        ubicacion = gestor.getUbicacionGuardada(toponimo);

        aliasView = findViewById(R.id.alias);
        aliasView.getEditText().setText(ubicacion.getAlias());
        toponimoView = findViewById(R.id.toponimo);
        toponimoView.setText(ubicacion.getToponimo());
        coordenadasView = findViewById(R.id.coordenadas);
        coordenadasView.setText(ubicacion.getLatitud() + ", " + ubicacion.getLongitud());

        openWeatherBox = findViewById(R.id.openweather_box);
        openWeatherBox.setChecked(ubicacion.isServicioActivo("openweather"));
        currentBox = findViewById(R.id.currents_box);
        currentBox.setChecked(ubicacion.isServicioActivo("currents"));
        activadaBox = findViewById(R.id.activada_box);
        activadaBox.setChecked(ubicacion.isActivada());
        favoritaBox = findViewById(R.id.favorita_box);
        favoritaBox.setChecked(ubicacion.getFavorita());


        informacionOpenweather = new HashMap<>();
        informacionCurrents = new HashMap<>();
        informacionUbicacionView = findViewById(R.id.informacion_ubicacion);

        getInformacion();

        Button botonGuardar = findViewById(R.id.boton_guardar_ubicacion);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubicacion.setAlias(aliasView.getEditText().getText().toString());
                ubicacion.activarServicio("openweather", openWeatherBox.isChecked());
                ubicacion.activarServicio("currents", currentBox.isChecked());
                if (activadaBox.isChecked())
                    ubicacion.activar();
                else
                    ubicacion.desactivar();
                ubicacion.setFavorita(favoritaBox.isChecked());

                getInformacion();
            }
        });

        Button botonDarBaja = findViewById(R.id.boton_dar_baja);
        botonDarBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestor.darBajaUbicacion(ubicacion);
                finish();
            }
        });
    }


    private void getInformacion() {
        if (ubicacion.isServicioActivo("openweather")) {
            if (gestor.getServicio("openweather").isActivo()) {
                informacionOpenweather = gestor.getTiempoPorUbicacion(ubicacion);
            } else {
                Toast toast = Toast.makeText(getBaseContext(), "El servicio Openweather esta desactivado", Toast.LENGTH_LONG);
                toast.show();
            }
        }

        if (ubicacion.isServicioActivo("currents")) {
            if (gestor.getServicio("currents").isActivo()) {
                informacionCurrents = gestor.getNoticiasPorUbicacion(ubicacion);
            } else {
                Toast toast = Toast.makeText(getBaseContext(), "El servicio Currents esta desactivado", Toast.LENGTH_LONG);
                toast.show();
            }
        }

        showInformacion();
    }

    private void showInformacion() {
        String resultado = "";
        if (ubicacion.isActivada()) {
            if (!informacionOpenweather.isEmpty()) {
                for (String key : informacionOpenweather.keySet()) {
                    resultado = resultado + "- " + key + ": " + informacionOpenweather.get(key) + "\n";
                }
                resultado = resultado + "\n\n\n\n";
            }
            if (!informacionCurrents.isEmpty())
                for (String news: informacionCurrents.keySet()){
                    for (String key : informacionCurrents.get(news).keySet()) {
                        resultado = resultado + "- " + key + ": " + informacionCurrents.get(news).get(key) + "\n";
                    }
                    resultado = resultado + "\n\n\n\n";
                }
        }

        informacionUbicacionView.setText(resultado);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ubicacion.setAlias(aliasView.getEditText().getText().toString());
        ubicacion.activarServicio("openweather", openWeatherBox.isChecked());
        ubicacion.activarServicio("currents", currentBox.isChecked());
        if (activadaBox.isChecked())
            ubicacion.activar();
        else
            ubicacion.desactivar();
        ubicacion.setFavorita(favoritaBox.isChecked());
    }
}