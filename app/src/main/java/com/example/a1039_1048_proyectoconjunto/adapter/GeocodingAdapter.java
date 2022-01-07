package com.example.a1039_1048_proyectoconjunto.adapter;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class GeocodingAdapter {


    public GeocodingAdapter() {
    }

    public Ubicacion doRequest(String tempUrl) {

        OkHttpClient client = new OkHttpClient.Builder().build();
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

        if (jsonData != null){
            return jsonAUbicacion(jsonData);
        }
        return null;
    }

    private Ubicacion jsonAUbicacion(String jsonData){
        Ubicacion ubicacion = null;
        String toponimo = null;
        String latitud = null;
        String longitud = null;
        String pais = null;
        try {
            JSONObject jsonResponse = new JSONObject(jsonData);
            if (jsonResponse.isNull("error")) { //si no hay errores
                if (jsonResponse.isNull("standard")) {
                    //toponimo
                    toponimo = jsonResponse.getString("city");
                    //pais
                    pais = jsonResponse.getString("country");
                } else {
                    JSONObject jsonObject = jsonResponse.getJSONObject("standard");
                    //toponimo
                    toponimo = jsonObject.getString("city");
                    //pais
                    pais = jsonObject.getString("countryname");
                }

                //latitud
                latitud = jsonResponse.getString("latt");
                //longitud
                longitud = jsonResponse.getString("longt");

                ubicacion = new Ubicacion();

                ubicacion.setToponimo(toponimo);
                ubicacion.setPais(pais);
                ubicacion.setLatitud(latitud);
                ubicacion.setLongitud(longitud);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ubicacion;
    }

}
