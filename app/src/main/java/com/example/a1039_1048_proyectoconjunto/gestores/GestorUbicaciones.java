package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;


import java.util.HashMap;
import java.util.Set;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {


    private HashMap<String, Ubicacion> ubicaciones;


    public GestorUbicaciones() {
        ubicaciones = new HashMap<>();

    }

    public Set<Object> getListadoUbicaciones() {
        return ConexionFirebase.getCollection("ubicaciones");
    }


    public boolean addUbicacion(Ubicacion ubicacion) {
        return ConexionFirebase.createDocument("ubicaciones", ubicacion, null);
    }

    public Ubicacion getUbicacionPorToponimo(String name) {
        return null;
    }

    public Ubicacion getUbicacionPorCoordenadas(double latitud, double longitud) {
        return null;
    }

}
