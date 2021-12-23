package com.example.a1039_1048_proyectoconjunto.gestores;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;

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

    public boolean addUbicacion(Ubicacion ubicacion) {
        return false;

    }

    public Ubicacion getUbicacion(String name) {
        return null;
    }

    public Ubicacion getUbicacionByCoordinates(double latitud, double longitud) {
        return null;
    }

}
