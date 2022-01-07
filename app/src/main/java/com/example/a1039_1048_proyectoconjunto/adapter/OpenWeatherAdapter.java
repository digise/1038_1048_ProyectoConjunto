package com.example.a1039_1048_proyectoconjunto.adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenWeatherAdapter {

    public OpenWeatherAdapter() {
    }

    public HashMap<String, String> doRequest(String tempUrl) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(tempUrl)
                .get().build();
        Call call = client.newCall(request);

        Response response = null;
        String jsonData = null;
        try {
            response = call.execute();
            jsonData = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (jsonData != null) {
            return jsonAHashMap(jsonData);
        }
        return null;
    }

    private HashMap<String, String> jsonAHashMap(String jsonData) {
        HashMap<String, String> datosOpenWeather = null;
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            if (jsonResponse.isNull("message")) { //si no hay errores

                if (!jsonResponse.has("main")) {//buscado por corrdenadas 
                    jsonResponse = jsonResponse.getJSONObject("current");
                } else {//buscado por toponimo 
                    jsonResponse = jsonResponse.getJSONObject("main");
                }
                datosOpenWeather = new HashMap<>();
                
                datosOpenWeather.put("temperaturaMedia", 
                        jsonResponse.getDouble("temp") - 273 +"");
                datosOpenWeather.put("sensacionTermica", 
                        jsonResponse.getDouble("feels_like") - 273 +"");
                datosOpenWeather.put("presion",
                        jsonResponse.getDouble("pressure") - 273 +"");
                datosOpenWeather.put("humedad",
                        jsonResponse.getDouble("humidity") - 273 +"");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return datosOpenWeather;

    }
}
