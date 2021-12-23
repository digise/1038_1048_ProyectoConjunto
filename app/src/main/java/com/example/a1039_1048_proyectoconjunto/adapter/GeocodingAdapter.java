package com.example.a1039_1048_proyectoconjunto.adapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

public class GeocodingAdapter{

    private Ubicacion ubicacion;

    public GeocodingAdapter(){
        ubicacion = new Ubicacion("sagunto");
    }

    public Ubicacion doRequest(String tempUrl){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ubicacion = new Ubicacion("castellon");

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        System.out.println("ubicacion: " + ubicacion);
        return ubicacion;
    }




}
