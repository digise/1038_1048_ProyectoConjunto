package com.example.a1039_1048_proyectoconjunto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.a1039_1048_proyectoconjunto.MainActivity;
import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import java.util.ArrayList;
import java.util.List;

public class AllUbicacionesActividad extends AppCompatActivity {

    private Gestor gestor;
    private List<Ubicacion> listaAlfabeticamente;
    private List<Ubicacion> listaRecientemente;
    private List<Ubicacion> listaSeleccionada;

    private LinearLayout listaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ubicaciones);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button boton = findViewById(R.id.boton_nueva_ubicacion);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActividadDarAlta.class));
            }
        });





        // Recopilacion de datos

        if (!Gestor.isInstanced()) {
            gestor = Gestor.getInstance();
            gestor.recuperarTodaLaInformacionDeLaAplicacion();
        } else {
            gestor = Gestor.getInstance();
        }

        listaAlfabeticamente = gestor.getUbicacionesOrdenadasAlfabeticamente();
        listaRecientemente = gestor.getUbicacionesOrdenadasRecientes();
        listaSeleccionada = listaAlfabeticamente;





        // Elemento spinner

        Spinner spinnerTipoLista = (Spinner) findViewById(R.id.tipoLista);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_lista, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoLista.setAdapter(adapter);

        spinnerTipoLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0)
                    listaSeleccionada = listaAlfabeticamente;
                else
                    listaSeleccionada = listaRecientemente;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });





        // View con la lista

        listaView = findViewById(R.id.contenedor_ubicaciones);
        actualizarLista();
    }





    private void actualizarLista() {
        for (Ubicacion ubicacion: listaSeleccionada) {

        }
    }



}