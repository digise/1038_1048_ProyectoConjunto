package com.example.a1039_1048_proyectoconjunto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;

public class ActividadServicioIndiv extends AppCompatActivity {

    private Gestor gestor;
    private Servicio servicio;

    private Button botonActivarDesactivar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_indiv);

        Bundle bundle = getIntent().getExtras();
        String nombre = "";
        if (bundle != null)
            nombre = bundle.getString("nombre");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gestor = Gestor.getInstance();

        servicio = gestor.getServicio(nombre);

        TextView nombreView = findViewById(R.id.nombre_view);
        nombreView.setText(nombre);

        TextView descripcionView = findViewById(R.id.descripcion_view);
        descripcionView.setText(gestor.getServicio(nombre).getDescripcion());

        botonActivarDesactivar = findViewById(R.id.activar_desactivar_boton);
        configurarBoton();
        botonActivarDesactivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (servicio.isActivo())
                    servicio.servicioActivo(false);
                else
                    servicio.servicioActivo(true);
                configurarBoton();
            }
        });
    }


    private void configurarBoton() {
        if (servicio.isActivo()) {
            botonActivarDesactivar.setText("Activado");
            botonActivarDesactivar.setBackgroundColor(getResources().getColor(R.color.verde_activar));
        } else {
            botonActivarDesactivar.setText("Desactivado");
            botonActivarDesactivar.setBackgroundColor(getResources().getColor(R.color.rojo_desactivar));
        }
    }
}