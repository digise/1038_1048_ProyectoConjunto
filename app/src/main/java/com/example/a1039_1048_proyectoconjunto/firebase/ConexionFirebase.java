package com.example.a1039_1048_proyectoconjunto.firebase;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConexionFirebase {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static String generarURL(String aux) {
        return "https://proyectoparadis-8f0c6-default-rtdb.europe-west1.firebasedatabase.app/" + aux;
    }

    public static Set<Object> getCollection(String referencia) {
        Set<Object> res = new HashSet<>();
        String url = generarURL(referencia);
        url = url + ".json";
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String jsonData = response.body().string();
            System.out.println(jsonData);
            JSONObject jsonObject = new JSONObject(jsonData);
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                String x = it.next();
                JSONObject y = jsonObject.getJSONObject(x);
                Ubicacion data = new Gson().fromJson(y.toString(), Ubicacion.class);
                res.add(data);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return res;

    }

    public static Object getDocument(String referencia, String idDocumento) {
        String url = generarURL(referencia);
        if (idDocumento != null) {
            url = url + "/" + idDocumento + ".json";
        } else {
            return null;
        }
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String dataDocumento = response.body().string();
            if (dataDocumento.equals("null"))
                return null;

            JSONObject jsonObjectDocumento = new JSONObject(dataDocumento);
            Ubicacion data = new Gson().fromJson(jsonObjectDocumento.toString(), Ubicacion.class);
            return data;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean createDocument(String referencia, Object data, String idDocumento) {

        if (contieneUbicacion((Ubicacion) data)) {
            return false;
        }

        //Si el documento tiene clave aleatoria o específica
        String url = generarURL(referencia);

        //Convertir el objeto a json y enviarlo en el body por put
        Gson gson = new Gson();
        String jsonBody = gson.toJson(data);
        RequestBody body = RequestBody.create(jsonBody, JSON);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = null;
        if (idDocumento != null) {
            url = url + "/" + idDocumento + ".json";
            request = new Request.Builder().url(url).put(body).build();
        } else {
            url = url + ".json";
            request = new Request.Builder().url(url).post(body).build();
        }

        //Hacer llamada y recibir respuesta
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful())
                return true;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateDocument(String referencia, Object data, String idDocumento) {

        if (! contieneUbicacion( (Ubicacion) data)) {
            return false;
        }

        //Url para identificar documento
        String url = generarURL(referencia);
        url = url + "/" + idDocumento + ".json";

        //Convertir el objeto a json y enviarlo en el body por put
        Gson gson = new Gson();
        String jsonBody = gson.toJson(data);
        RequestBody body = RequestBody.create(jsonBody, JSON);
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(url).patch(body).build();

        //Hacer llamada y recibir respuesta
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            Ubicacion ubicacion = new Gson().fromJson(response.body().string(), Ubicacion.class);
            if (response.isSuccessful() && ubicacion.equals((Ubicacion) data))
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean contieneUbicacion(Ubicacion ubicacion){
        Set<Ubicacion> ubicaciones = Gestor.getInstance().getGestorUbicaciones().getListadoUbicaciones();
        for (Ubicacion u : ubicaciones){
            if (u.equals(ubicacion)){
                return true;
            }
        }
        return false;
    }


}
