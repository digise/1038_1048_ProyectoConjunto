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

import com.example.a1039_1048_proyectoconjunto.R;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.customview.WrapperBotonUbicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import java.util.List;

public class AllUbicacionesActividad extends AppCompatActivity {

    private Gestor gestor;
    private List<Ubicacion> listaAlfabeticamente;
    private List<Ubicacion> listaRecientemente;
    private List<Ubicacion> listaSeleccionada;

    private Spinner spinnerTipoLista;
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






        // Elemento spinner

        spinnerTipoLista = (Spinner) findViewById(R.id.tipoLista);
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
                actualizarVista();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        actualizarLista();






        // View con la lista

        listaView = findViewById(R.id.contenedor_ubicaciones);
        actualizarVista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizarLista();
        actualizarVista();
    }

    private void actualizarVista() {
        listaView.removeAllViews();

        for (Ubicacion ubicacion: listaSeleccionada) {
            WrapperBotonUbicacion wrapperUbicacion = new WrapperBotonUbicacion(getBaseContext());
            wrapperUbicacion.setAlias(ubicacion.getAlias());
            wrapperUbicacion.setToponimo(ubicacion.getToponimo());
            wrapperUbicacion.setCoordenadas(ubicacion.getLatitud() + ", " + ubicacion.getLongitud());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 20, 0, 20);

            wrapperUbicacion.setLayoutParams(params);
            listaView.addView(wrapperUbicacion);

            wrapperUbicacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String toponimo = ubicacion.getToponimo();
                    Intent intent = new Intent(AllUbicacionesActividad.this, UbicacionActividad.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("codigoUbicacion", toponimo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }


    private void actualizarLista() {
        listaAlfabeticamente = gestor.getUbicacionesOrdenadasAlfabeticamente();
        listaRecientemente = gestor.getUbicacionesOrdenadasRecientes();
        listaSeleccionada = listaAlfabeticamente;
        spinnerTipoLista.setSelection(0);
    }

}