package com.example.a1039_1048_proyectoconjunto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1039_1048_proyectoconjunto.activities.ActividadServicios;
import com.example.a1039_1048_proyectoconjunto.activities.AllUbicacionesActividad;
import com.example.a1039_1048_proyectoconjunto.activities.UbicacionActividad;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Gestor gestor;
    private int posicionLista = 0;
    private int nUbicaciones = 0;
    private List<Ubicacion> listaActivadas;
    private List<Ubicacion> listaFavoritas;
    private List<Ubicacion> listaSeleccionada;



    private TextView posicionTextView;

    TextView stringListaVacia;
    WrapperBotonUbicacion ubicacionBoton1;
    WrapperBotonUbicacion ubicacionBoton2;
    WrapperBotonUbicacion ubicacionBoton3;
    Ubicacion ubicacion1;
    Ubicacion ubicacion2;
    Ubicacion ubicacion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(posicionLista*3 + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaAAAAAAAAAA");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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




        // Recopilacion de datos

        if (!Gestor.isInstanced()) {
            gestor = Gestor.getInstance();
            gestor.recuperarTodaLaInformacionDeLaAplicacion();
        } else {
            gestor = Gestor.getInstance();
        }


        listaActivadas = new ArrayList<>(gestor.getUbicacionesActivas().values());
        listaFavoritas = gestor.getUbicacionesFavoritas();
        listaSeleccionada = listaActivadas;
        nUbicaciones = listaSeleccionada.size();





        // Spinner con tipos de listas

        Spinner spinnerTipoLista = (Spinner) findViewById(R.id.tipoListaActivadas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_lista_activadas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoLista.setAdapter(adapter);

        spinnerTipoLista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0)
                    listaSeleccionada = listaActivadas;
                else
                    listaSeleccionada = listaFavoritas;
                posicionLista = 0;
                nUbicaciones = listaSeleccionada.size();
                actualizaPuntero();
                setUpBotonesubicacion();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });






        // Botones para las paginas de ubicaciones

        posicionTextView = findViewById(R.id.posicion_lista);
        actualizaPuntero();

        final ImageButton botonAnterior = findViewById(R.id.boton_anterior);
        botonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicionLista > 0) {
                    posicionLista--;
                    actualizaPuntero();
                    setUpBotonesubicacion();
                }
            }
        });

        final ImageButton botonSiguiente = findViewById(R.id.boton_siguiente);
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(nUbicaciones / 3 == posicionLista & nUbicaciones % 3 == 0)) {
                    posicionLista++;
                    actualizaPuntero();
                    setUpBotonesubicacion();
                }

            }
        });






        // Botones para las tres ubicaciones que se ven al mismo tiempo

        stringListaVacia = findViewById(R.id.text_activos_vacia);
        ubicacionBoton1 = findViewById(R.id.boton_ubicacion1);
        ubicacionBoton2 = findViewById(R.id.boton_ubicacion2);
        ubicacionBoton3 = findViewById(R.id.boton_ubicacion3);
        System.out.println(posicionLista*3 + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaaaaaaaaaaaaaaAAAAAAAAAA");
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





    // Metodos privados

    private void actualizaPuntero(){
        posicionTextView.setText(String.valueOf(posicionLista));
    }

    private void setUpBotonesubicacion() {


        if (posicionLista*3 < nUbicaciones) {
            stringListaVacia.setVisibility(View.GONE);
            ubicacionBoton1.setVisibility(View.VISIBLE);
            ubicacion1 = listaSeleccionada.get(posicionLista*3);

            ubicacionBoton1.setAlias(ubicacion1.getAlias());
            ubicacionBoton1.setToponimo(ubicacion1.getToponimo());
            ubicacionBoton1.setCoordenadas(ubicacion1.getLatitud() + ", " + ubicacion1.getLongitud());
            ubicacionBoton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ubicacion = ubicacion1.getToponimo();
                    Intent intent = new Intent(MainActivity.this, UbicacionActividad.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("codigoUbicacion", ubicacion);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else {
            ubicacionBoton1.setVisibility(View.INVISIBLE);
            stringListaVacia.setVisibility(View.VISIBLE);
        }


        if (posicionLista*3+1 < nUbicaciones){
            ubicacionBoton2.setVisibility(View.VISIBLE);
            ubicacion2 = listaSeleccionada.get(posicionLista*3+1);

            ubicacionBoton2.setAlias(ubicacion2.getAlias());
            ubicacionBoton2.setToponimo(ubicacion2.getToponimo());
            ubicacionBoton2.setCoordenadas(ubicacion2.getLatitud() + ", " + ubicacion2.getLongitud());
            ubicacionBoton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ubicacion = ubicacion2.getToponimo();
                    Intent intent = new Intent(MainActivity.this, UbicacionActividad.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("codigoUbicacion", ubicacion);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else
            ubicacionBoton2.setVisibility(View.INVISIBLE);


        if (posicionLista*3+2 < nUbicaciones) {
            ubicacionBoton3.setVisibility(View.VISIBLE);
            ubicacion3 = listaSeleccionada.get(posicionLista*3+2);

            ubicacionBoton3.setAlias(ubicacion3.getAlias());
            ubicacionBoton3.setToponimo(ubicacion3.getToponimo());
            ubicacionBoton3.setCoordenadas(ubicacion3.getLatitud() + ", " + ubicacion3.getLongitud());
            ubicacionBoton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String ubicacion = ubicacion3.getToponimo();
                    Intent intent = new Intent(MainActivity.this, UbicacionActividad.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("codigoUbicacion", ubicacion);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        } else
            ubicacionBoton3.setVisibility(View.INVISIBLE);

    }
















    private class CargarDatos extends AsyncTask<URL, Integer, Long> {
        // Do the long-running work in here
        protected Long doInBackground(URL... urls) {





            int count = urls.length;
            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                //totalSize += Downloader.downloadFile(urls[i]);
                publishProgress((int) ((i / (float) count) * 100));
                // Escape early if cancel() is called
                if (isCancelled()) break;
            }
            return totalSize;
        }

        // This is called each time you call publishProgress()
        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        // This is called when doInBackground() is finished
        protected void onPostExecute(Long result) {
            //showNotification("Downloaded " + result + " bytes");
        }
    }

}

