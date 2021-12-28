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

        Gson gson = new Gson();

        guardarDatos(gson, "datos.json", ubicacion);

        System.out.println("ubicaciones actuales: " + leerDatos("datos.json"));

        return ubicacion;

    }

    private String leerDatos(String nombreFichero){
        StringBuilder ficheroBuilder = new StringBuilder();
        String fichero = "";

        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                ficheroBuilder.append(linea);
            }
            fichero = ficheroBuilder.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return fichero;
    }

    private void guardarDatos(Gson gson, String nombreFichero, Ubicacion ubicacion){
        String ubicacionJson = gson.toJson(ubicacion);
        System.out.println("ubicacion Json: " + ubicacionJson);

        Properties properties = gson.fromJson(nombreFichero, Properties.class);
        System.out.println(properties.get("ubicacion"));

        try (FileWriter fileWriter = new FileWriter(nombreFichero)) {
            fileWriter.write(ubicacionJson);
            fileWriter.flush();
            System.out.println("Fichero creado");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
