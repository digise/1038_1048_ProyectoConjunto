package com.example.a1039_1048_proyectoconjunto.adapter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

public class GeocodingAdapter {

    private Ubicacion ubicacion;

    public GeocodingAdapter(){
        ubicacion = new Ubicacion("sagunto");
    }

    public Ubicacion doRequest(String tempUrl, Context contexto){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl,
                response -> {
                    Log.d("funciona", "FUNCIONA");
                    ubicacion.setToponimo("castellon");
                },
                error -> Log.d("no funciona", "That didn't work!"));

        Log.d("ubicacion: ", ubicacion.getToponimo());

        RequestQueue requestQueue = Volley.newRequestQueue(contexto);
        requestQueue.add(stringRequest);

        return ubicacion;
    }

}
