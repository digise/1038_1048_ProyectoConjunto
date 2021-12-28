package com.example.a1039_1048_proyectoconjunto;

import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


public class HTTPClient {

        public static void main(String[] args) throws IOException {
            DatabaseReference mDatabase;

            mDatabase = FirebaseDatabase.getInstance().getReference();
            Ubicacion ubicacion = new Ubicacion();
            //mDatabase.child("ubicaciones").child("hola").setValue(ubicacion);
            /*GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
            Ubicacion ubicacion = new Ubicacion();
            gestorUbicaciones.addUbicacion(ubicacion);*/

        }
}
