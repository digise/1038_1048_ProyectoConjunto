package com.example.a1039_1048_proyectoconjunto.servicios;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.a1039_1048_proyectoconjunto.Servicio;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ServicioGeocoding {

    private final String url = "https://geocode.xyz/";
    private final String auth = "57673066339488579050x115589";

    public ServicioGeocoding(){

    }

    public void getInformacionByToponimo(String toponimoCoords){
        String tempUrl = url + toponimoCoords;
        tempUrl += "?json=1&auth=" + auth;
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
        geocodingAdapter.doRequest(tempUrl);
    }

    public void getInformacionByCoords(String toponimoCoords){
        String tempUrl = url + toponimoCoords;
        tempUrl += "?geoit=json&auth=" + auth;
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
        geocodingAdapter.doRequest(tempUrl);
    }

    public Ubicacion getUbicacionByToponimo(String toponimo){
        return null;
    }

    public Ubicacion getUbicacionByCoordenadas(double latitud, double longitud){
        return null;
    }


}
