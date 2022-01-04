package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.iid.FirebaseInstanceId;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia1Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){

    }

    @Test
    public void listaUbicaciones_activas_encontradas(){
       Gestor gestor = Gestor.getInstance();
       ServicioCurrents servicioCurrents = gestor.getGestorServicios().getServicioCurrents();
        System.out.println(servicioCurrents);
       Ubicacion ubicacion = new Ubicacion("rkcr", "12", "12", "12");
       gestor.darAltaUbicacion(ubicacion);
       System.out.println(gestor.getAllUbicaciones().toString());
       gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
       System.out.println(gestor.getAllServicios().toString());
    }

    @Test
    public void listaUbicaciones_activas_noEncontradas() {
    }
}
