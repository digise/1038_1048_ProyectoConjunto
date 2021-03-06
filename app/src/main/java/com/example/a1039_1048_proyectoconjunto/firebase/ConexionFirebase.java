package com.example.a1039_1048_proyectoconjunto.firebase;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConexionFirebase {

    public final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public String generarURL(String aux) {
        return "https://proyectoparadis-8f0c6-default-rtdb.europe-west1.firebasedatabase.app/" + aux;
    }

    public <T> Map<String, T> getCollection(String referencia, Class<T> c) {
        Map<String, T> res = new HashMap<>();
        String url = generarURL(referencia);
        url = url + ".json";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String jsonData = response.body().string();
            if (jsonData.equals("null")) {
                return res;
            }
            JSONObject jsonObject = new JSONObject(jsonData);
            res = new Gson().fromJson(jsonObject.toString(), HashMap.class);
            for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
                String x = it.next();
                JSONObject y = jsonObject.getJSONObject(x);
                T data = new Gson().fromJson(y.toString(), c);
                res.put(x, data);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return res;

    }

    public <T> T getDocument(String referencia, String idDocumento, Class<T> c) {
        String url = generarURL(referencia);
        if (idDocumento != null) {
            url = url + "/" + idDocumento + ".json";
        } else {
            return null;
        }
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).get().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String dataDocumento = response.body().string();
            if (dataDocumento.equals("null"))
                return null;

            JSONObject jsonObjectDocumento = new JSONObject(dataDocumento);
            T data = new Gson().fromJson(jsonObjectDocumento.toString(), c);
            return data;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public <T> String createDocument(String referencia, T data, String idDocumento) {
        if (data.getClass() == Ubicacion.class) {
            if (contieneUbicacion((Ubicacion) data))
                return null;
        } else {
            if (contieneServicio((Servicio) data))
                return null;
        }

        //Si el documento tiene clave aleatoria o espec??fica
        String url = generarURL(referencia);

        //Convertir el objeto a json y enviarlo en el body por put
        Gson gson = new Gson();
        String jsonBody = gson.toJson(data);
        RequestBody body = RequestBody.create(jsonBody, JSON);
        OkHttpClient client = new OkHttpClient.Builder().build();
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
            if (!response.isSuccessful())
                return null;
            if (idDocumento == null) {
                JSONObject jsonObjectResponse = new JSONObject(response.body().string());
                idDocumento = jsonObjectResponse.getString("name");
            }
            return idDocumento;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> boolean updateDocument(String referencia, T data, String idDocumento) {
        if (data.getClass() == Ubicacion.class) {
            if (!contieneUbicacion((Ubicacion) data))
                return false;
        }

        //Url para identificar documento
        String url = generarURL(referencia);
        url = url + "/" + idDocumento + ".json";

        //Convertir el objeto a json y enviarlo en el body por put
        Gson gson = new Gson();
        String jsonBody = gson.toJson(data);
        RequestBody body = RequestBody.create(jsonBody, JSON);
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).patch(body).build();

        //Hacer llamada y recibir respuesta
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            String x = response.body().string();
            if (response.isSuccessful())
                return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeDocument(String referencia, String idDocumento) {
        //Url para identificar documento
        String url = generarURL(referencia);
        url += "/" + idDocumento + ".json";

        //Comprobar si la url es correcta
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).delete().build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private boolean contieneUbicacion(Ubicacion ubicacion) {
        Map<String, Ubicacion> ubicaciones = Gestor.getInstance().getAllUbicaciones();
        for (Ubicacion u : ubicaciones.values()) {
            if (u.equals(ubicacion)) {
                return true;
            }
        }
        return false;
    }

    private boolean contieneUbicacion(String idDocumento) {
        Map<String, Ubicacion> ubicaciones = Gestor.getInstance().getAllUbicaciones();
        return ubicaciones.containsKey(idDocumento);
    }

    private boolean contieneServicio(Servicio servicio) {
        Map<String, Servicio> servicios = Gestor.getInstance().getAllServicios();
        for (Servicio s : servicios.values()) {
            if (s.equals(servicio)) {
                return true;
            }
        }
        return false;
    }

    private boolean contieneServicio(String idDocumento) {
        Map<String, Servicio> servicios = Gestor.getInstance().getAllServicios();
        return servicios.containsKey(idDocumento);
    }


}
