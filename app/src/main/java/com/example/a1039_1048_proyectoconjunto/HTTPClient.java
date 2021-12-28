package com.example.a1039_1048_proyectoconjunto;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {

    public static class GetExample {
        final OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try{
                Response response = client.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(response.body().string());
                System.out.println(jsonObject);
                return response.body().string();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void main(String[] args) throws IOException {
            GetExample example = new GetExample();
            String response = example.run("https://geocode.xyz/torreblanca?json=1&auth=57673066339488579050x115589");
            System.out.println(response);
        }
    }
}
