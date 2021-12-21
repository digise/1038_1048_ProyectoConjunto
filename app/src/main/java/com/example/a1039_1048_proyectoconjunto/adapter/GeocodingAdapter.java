package com.example.a1039_1048_proyectoconjunto.adapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GeocodingAdapter implements APIsAdapter{

    public GeocodingAdapter(){
    }

    public void doRequest(String tempUrl){

        hecho=false
        ubicacion = null
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                gestor.addUbicaicon(ubicaion)
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        while(! hecho){
            if (ubicacion != null){
                hecho = true
            }
        }

    }




}
