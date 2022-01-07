package com.example.a1039_1048_proyectoconjunto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.a1039_1048_proyectoconjunto.MainActivity;
import com.example.a1039_1048_proyectoconjunto.R;

public class ActividadServicios extends AppCompatActivity {

    private LinearLayout contenedorServicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        contenedorServicios = findViewById(R.id.contenedor_servicios);

        addRowServicio("OpenWeather");
        addRowServicio("Currents");
    }


    private void addRowServicio(String nombre) {
        LinearLayout contenedor = new LinearLayout(this);
        addParametros(contenedor);

        Button boton = new Button(this);
        addParametros(boton, nombre);

        contenedor.addView(boton);

        contenedorServicios.addView(contenedor);
    }

    private void addParametros(LinearLayout layout) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 30, 10, 30);
        layout.setLayoutParams(params);
        layout.setGravity(Gravity.CENTER);
    }

    private void addParametros(Button boton, String nombre) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        );
        params.setMargins(30, 30, 30, 30);
        boton.setLayoutParams(params);

        String texto = nombre + " - Mas informacion";
        boton.setText(texto);
        boton.setBackgroundResource(R.drawable.boton_redondo);
        boton.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.white));
        boton.setTextSize(15);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto = (String) boton.getText();

                texto = texto.trim();
                String[] stringArg = texto.split("-");

                Intent intent = new Intent(ActividadServicios.this, ActividadServicioIndiv.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombre", stringArg[0].trim());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}