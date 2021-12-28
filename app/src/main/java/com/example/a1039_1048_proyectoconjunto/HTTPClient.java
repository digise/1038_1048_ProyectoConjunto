package com.example.a1039_1048_proyectoconjunto;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;

import java.io.IOException;



import com.google.cloud.firestore.Firestore;



public class HTTPClient {

        public static void main(String[] args) throws IOException {
            ConexionFirebase conexionFirebase = new ConexionFirebase();
            Firestore bd = conexionFirebase.iniciarFirebase();
            Ubicacion ubicacion = new Ubicacion();
            bd.collection("ubicaciones").document("hola").set(ubicacion);

            //mDatabase.child("ubicaciones").child("hola").setValue(ubicacion);
            /*GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
            Ubicacion ubicacion = new Ubicacion();
            gestorUbicaciones.addUbicacion(ubicacion);*/

        }
}
