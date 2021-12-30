package com.example.a1039_1048_proyectoconjunto;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws JSONException {
        /*String jsonPrueba2 = "{\n" +
              "    \"1\": {\n" +
              "        \"activada\": true,\n" +
              "        \"latitud\": \"40.22963\",\n" +
              "        \"longitud\": \"0.18229\",\n" +
              "        \"pais\": \"Spain\",\n" +
              "        \"toponimo\": \"torreblanca\"\n" +
              "    },\n" +
              "    \"-Ms5phrNs0dndj8b_7v7\": {\n" +
              "        \"activada\": true,\n" +
              "        \"latitud\": \"40.22963\",\n" +
              "        \"longitud\": \"0.18229\",\n" +
              "        \"pais\": \"Spain\",\n" +
              "        \"toponimo\": \"torreblanca\"\n" +
              "    },\n" +
              "    \"-Ms5rTIOk4O8XeGuAtCW\": {\n" +
              "        \"longitud\": \"0.18229\",\n" +
              "        \"pais\": \"Spain\",\n" +
              "        \"toponimo\": \"torreblanca\"\n" +
              "    }\n" +

              "}";
      try {
         JSONObject jsonObject = new JSONObject(jsonPrueba2);
         for (Iterator<String> it = jsonObject.keys(); it.hasNext(); ) {
            String x = it.next();
            JSONObject y = jsonObject.getJSONObject(x);
            Ubicacion data = new Gson().fromJson(y.toString(), Ubicacion.class);
            System.out.println(data.toString());
         }
      } catch (JSONException e) {
         e.printStackTrace();
      }*/

    }
}
