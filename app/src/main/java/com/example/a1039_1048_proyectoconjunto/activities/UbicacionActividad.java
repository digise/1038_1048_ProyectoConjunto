package com.example.a1039_1048_proyectoconjunto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.a1039_1048_proyectoconjunto.Main;
import com.example.a1039_1048_proyectoconjunto.MainActivity;
import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.google.android.material.textfield.TextInputLayout;

public class UbicacionActividad extends AppCompatActivity {

    private Ubicacion ubicacion;

    private TextInputLayout alias;
    private CheckBox openWeatherBox;
    private CheckBox currentBox;
    private CheckBox activadaBox;
    private CheckBox favoritaBox;
    private TextView informacionUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        Bundle bundle = getIntent().getExtras();
        int value = -1;
        if (bundle != null)
            value = bundle.getInt("codigoUbicacion");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextInputLayout textInput = findViewById(R.id.alias);

        openWeatherBox = findViewById(R.id.openweather_box);
        currentBox = findViewById(R.id.currents_box);
        activadaBox = findViewById(R.id.activada_box);
        favoritaBox = findViewById(R.id.favorita_box);

        informacionUbicacion = findViewById(R.id.informacion_ubicacion);

        Button botonGuardar = findViewById(R.id.boton_guardar_ubicacion);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textInput.getEditText().getText().toString().trim();
                //TODO serveis
                informacionUbicacion.setText("Ha cambiado el texto");
            }
        });

        Button botonDarBaja = findViewById(R.id.boton_dar_baja);
        botonDarBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}