package com.example.a1039_1048_proyectoconjunto.adapter;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;


public class GeocodingAdapter {


    public GeocodingAdapter() {
    }

    public Ubicacion doRequest(String tempUrl) {
        Ubicacion ubicacion = new Ubicacion();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(tempUrl)
                .get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("Fail");
                countDownLatch.countDown();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String jsonData = response.body().string();
                String toponimo = null;
                String latitud = null;
                String longitud = null;
                String pais = null;
                try {
                    JSONObject jsonResponse = new JSONObject(jsonData);
                    /*if (jsonResponse.isNull("standard")) {


                    } else{*/
                        JSONObject jsonObject = jsonResponse.getJSONObject("standard");

                        //toponimo
                        toponimo = jsonObject.getString("city");

                        //pais
                        pais = jsonObject.getString("countryname");

                        //latitud
                        latitud = jsonResponse.getString("latt");
                        //longitud
                        longitud = jsonResponse.getString("longt");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ubicacion.setToponimo(toponimo);
                ubicacion.setPais(pais);
                ubicacion.setLatitud(latitud);
                ubicacion.setLongitud(longitud);
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return ubicacion;

    }
}
