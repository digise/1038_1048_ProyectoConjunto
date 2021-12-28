package com.example.a1039_1048_proyectoconjunto.gestores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashSet;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {


    private HashMap<String, Ubicacion> ubicaciones;

    private FirebaseFirestore db;

    public GestorUbicaciones() {
        ubicaciones = new HashMap<>();
        //db = FirebaseFirestore.getInstance();
    }

    public HashSet<Ubicacion> getListadoUbicaciones() {
        return null;
    }


    public boolean addUbicacion(Ubicacion ubicacion) {
        DocumentReference doc = db.collection("ubicaciones")
                .add(ubicacion).getResult();
        return doc != null;
    }

    public Ubicacion getUbicacionPorToponimo(String name) {
        return null;
    }

    public Ubicacion getUbicacionPorCoordenadas(double latitud, double longitud) {
        return null;
    }

}
