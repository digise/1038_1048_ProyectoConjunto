package com.example.a1039_1048_proyectoconjunto.adapter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GeocodingAdapter {

    private Ubicacion ubicacion;

    public GeocodingAdapter() {
        ubicacion = new Ubicacion("sagunto");
    }

    public Ubicacion doRequest(String tempUrl) {

        BufferedReader bufferedReader;
        String linea;
        StringBuffer responseContent = new StringBuffer();

        try {
            Log.d("entra al try",  "try");

            URL url = new URL(tempUrl);
            Log.d("hace el url",  "try");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.d("conexion",  "try");

            connection.setRequestMethod("GET");
            Log.d("hace get",  "try");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            Log.d("falla?",  "try");
            int status = connection.getResponseCode();

            Log.d("estado", status + "");

            if(status > 299){
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while( (linea = bufferedReader.readLine()) != null ){
                    responseContent.append(linea);
                }
            }else{
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while( (linea = bufferedReader.readLine()) != null ){
                    responseContent.append(linea);
                }
            }
            bufferedReader.close();

            Log.d("response", responseContent.toString());

        } catch (MalformedURLException e) {
            Log.d("malformed", e.toString());
        } catch (IOException e){
            Log.d("ioException", e.toString());
        }

        return ubicacion;
    }

}
