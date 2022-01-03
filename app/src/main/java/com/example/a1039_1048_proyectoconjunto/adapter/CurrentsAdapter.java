package com.example.a1039_1048_proyectoconjunto.adapter;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrentsAdapter {


    public CurrentsAdapter() {
    }

    public HashMap<String, HashMap<String, String>> doRequest(String tempUrl) {

        OkHttpClient client = new OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(tempUrl).get().build();
        Call call = client.newCall(request);

        Response response = null;
        String jsonData = null;
        try {
            response = call.execute();
            jsonData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonData != null){
            return jsonANoticia(jsonData);
        }
        return null;
    }

    private HashMap<String, HashMap<String, String>> jsonANoticia(String jsonData){
        HashMap<String, HashMap<String, String>> noticias = new HashMap<>(new HashMap<>());
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            JSONArray noticiasArray = jsonResponse.getJSONArray("news");
            String id = "";
            String titulo = "";
            String descripcion = "";
            String urlNew = "";
            for (int i = 0; i < noticiasArray.length(); i++) {
                JSONObject jsonObjectInArray = noticiasArray.getJSONObject(i);
                
                id = jsonObjectInArray.getString("id");
                titulo = jsonObjectInArray.getString("title");
                descripcion = jsonObjectInArray.getString("description");
                urlNew = jsonObjectInArray.getString("url");

                noticias.put(id, new HashMap<String, String>());
                noticias.get(id).put("titulo", titulo);
                noticias.get(id).put("descripcion", descripcion);
                noticias.get(id).put("urlNew", urlNew);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noticias;
    }
}
