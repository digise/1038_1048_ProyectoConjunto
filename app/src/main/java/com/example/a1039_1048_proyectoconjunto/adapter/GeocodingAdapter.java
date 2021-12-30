package com.example.a1039_1048_proyectoconjunto.adapter;


import androidx.annotation.NonNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


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
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                String toponimo = null;
                String latitud = null;
                String longitud = null;
                String pais = null;
                try {
                    JSONObject jsonResponse = new JSONObject(jsonData);
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

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("Fail");
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

    public String doRequest(String tempUrl, boolean hola) {
        Ubicacion ubicacion = new Ubicacion();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        //llamada sincrona que no funciona
        /*OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try{
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()){
                String jsonData = response.body().string();
                System.out.println(jsonData);
                JSONObject jsonObjectPrueba = new JSONObject();
                jsonObjectPrueba.put("city", "Torreblanca");
                System.out.println(jsonObjectPrueba.toString());
                /*JSONArray jsonObject1 = jsonObject.getJSONArray("standard");
                String description = jsonObject1.getString(1);
                ubicacion.setToponimo(description);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(tempUrl)
                .get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String jsonData = response.body().string();
                String toponimo = null;
                String latitud = null;
                String longitud = null;
                String pais = null;
                try {
                    JSONObject jsonResponse = new JSONObject(jsonData);
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

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println("Fail");
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return null;

    }


}
