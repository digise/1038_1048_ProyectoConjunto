package com.example.a1039_1048_proyectoconjunto.adapter;

import org.json.JSONObject;

import java.util.HashMap;

public interface APIsAdapter {
    HashMap<String, String> JsonToHahsMap(JSONObject jsonResponse);
}
