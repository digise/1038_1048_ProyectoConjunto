package com.example.a1039_1048_proyectoconjunto;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.google.firebase.firestore.FirebaseFirestore;

import org.junit.Test;

public class fdjk {

    private Gestor gestor;


    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Ubicacion ubicacion = new Ubicacion("12", "12", "12", "12");
        db.collection("ubicacines").add(ubicacion);

    }
}
