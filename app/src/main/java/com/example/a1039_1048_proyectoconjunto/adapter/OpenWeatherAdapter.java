package com.example.a1039_1048_proyectoconjunto.adapter;

import com.android.volley.Request;
import com.android.volley.Response;

import com.android.volley.toolbox.StringRequest;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.json.JSONObject;

import java.util.HashMap;

public class OpenWeatherAdapter implements APIsAdapter{

    public OpenWeatherAdapter() {
    }

    @Override
    public HashMap<String, String> getInformacion(JSONObject jsonResponse) {
        return null;
    }

}
