package com.example.a1039_1048_proyectoconjunto;

import java.util.HashMap;
import java.util.HashSet;

public class GestorUbicaciones {

    // Singleton
    private static GestorUbicaciones INSTANCE;

    private GestorUbicaciones() {
    }

    public synchronized  static GestorUbicaciones getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GestorUbicaciones();
            INSTANCE.ubicaciones = new HashMap<>();
        }
        return INSTANCE;
    }



    private HashMap<String, Ubicacion> ubicaciones;

    public HashSet<Ubicacion> getListadoUbicaciones() {
        return null;
    }

    public boolean addUbicacion(Ubicacion ubicacion){
        return false;

    }

    public Ubicacion getUbicacion(String name) {
        return null;
    }

    public Ubicacion getUbicacionByCoordinates(double latitud, double longitud){
        return null;
    }

}
