package com.example.a1039_1048_proyectoconjunto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.activities.AllUbicacionesActividad;
import com.google.android.material.textfield.TextInputLayout;

public class ActividadDarAlta extends AppCompatActivity {

    private TextInputLayout toponimo;
    private TextInputLayout latitud;
    private TextInputLayout longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_alta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toponimo = findViewById(R.id.nuevo_toponimo);
        latitud = findViewById(R.id.latitud);
        longitud = findViewById(R.id.longitud);

        Button botonBuscarToponimo = findViewById(R.id.boton_buscar_toponimo);
        botonBuscarToponimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        Button botonBuscarCoordenadas = findViewById(R.id.boton_buscar_coordenadas);
        botonBuscarCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}