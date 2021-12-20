package com.example.a1039_1048_proyectoconjunto.adapter;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.json.JSONObject;

import java.util.HashMap;

public interface APIsAdapter {
    HashMap<String, String> getInformacion(JSONObject jsonResponse);
}
