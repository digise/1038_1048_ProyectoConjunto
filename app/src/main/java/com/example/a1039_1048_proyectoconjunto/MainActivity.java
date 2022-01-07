package com.example.a1039_1048_proyectoconjunto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.a1039_1048_proyectoconjunto.activities.ActividadServicios;
import com.example.a1039_1048_proyectoconjunto.activities.AllUbicacionesActividad;
import com.example.a1039_1048_proyectoconjunto.activities.UbicacionActividad;


public class MainActivity extends AppCompatActivity {

    private int posicionLista = 0;

    private TextView posicionTextView;

    WrapperBotonUbicacion ubicacion1;
    WrapperBotonUbicacion ubicacion2;
    WrapperBotonUbicacion ubicacion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        Button botonMenuServicios = findViewById(R.id.boton_menu_servicios);
        botonMenuServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ActividadServicios.class);
                startActivity(intent);
            }
        });

        Spinner spinnerTipoLista = (Spinner) findViewById(R.id.tipoLista);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_lista, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoLista.setAdapter(adapter);

        posicionTextView = findViewById(R.id.posicion_lista);
        posicionTextView.setText(String.valueOf(posicionLista));

        final ImageButton botonAnterior = findViewById(R.id.boton_anterior);
        botonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO cambiar los botones_ubicacion a las 3 ubicaciones anteriores
                posicionLista--;
                posicionTextView.setText(String.valueOf(posicionLista));
            }
        });

        final ImageButton botonSiguiente = findViewById(R.id.boton_siguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO cambiar los botones_ubicacion a las siguientes 3 ubicaciones
                posicionLista++;
                posicionTextView.setText(String.valueOf(posicionLista));
            }
        });


        ubicacion1 = findViewById(R.id.boton_ubicacion1);
        ubicacion2 = findViewById(R.id.boton_ubicacion2);
        ubicacion3 = findViewById(R.id.boton_ubicacion3);
        setUpBotonesubicacion();


        final Button botonFlotante = findViewById(R.id.boton_todas_ubicaciones);
        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AllUbicacionesActividad.class);
                startActivity(intent);
            }
        });
    }

    private void setUpBotonesubicacion() {
        ubicacion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO ir a la ubicacion
                int ubicacion = 1;
                Intent intent = new Intent(MainActivity.this, UbicacionActividad.class);
                Bundle bundle = new Bundle();
                bundle.putInt("codigoUbicacion", ubicacion);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ubicacion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO ir a la ubicacion
                ubicacion2.setAlias("Proba");
            }
        });

        ubicacion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO ir a la ubicacion
            }
        });
    }


}