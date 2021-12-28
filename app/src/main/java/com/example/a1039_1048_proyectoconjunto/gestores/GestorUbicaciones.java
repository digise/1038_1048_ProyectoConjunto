package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.HashSet;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {


    private HashMap<String, Ubicacion> ubicaciones;


    public GestorUbicaciones() {
        ubicaciones = new HashMap<>();

    }

    public HashSet<Ubicacion> getListadoUbicaciones() {
        return null;
    }


    public void addUbicacion(Ubicacion ubicacion) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ubicaciones").add(ubicacion);
    }

    public Ubicacion getUbicacionPorToponimo(String name) {
        return null;
    }

    public Ubicacion getUbicacionPorCoordenadas(double latitud, double longitud) {
        return null;
    }

}
