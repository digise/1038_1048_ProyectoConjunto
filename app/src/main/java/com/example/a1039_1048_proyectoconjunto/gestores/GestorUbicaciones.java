package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {


    private Set<Ubicacion> ubicaciones;


    protected GestorUbicaciones() {
        ubicaciones = new HashSet<>();
        Set<Object> objectosUbicaciones = ConexionFirebase.getCollection("ubicaciones");
        for (Object ubicacion : objectosUbicaciones) {
            ubicaciones.add((Ubicacion) ubicacion);
        }
    }

    public Set<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }


    public boolean addUbicacion(Ubicacion ubicacion) {
        boolean anadido = false;
        if (ubicacion != null) {
            anadido = ConexionFirebase.createDocument("ubicaciones", ubicacion, null);
            if (anadido) {
                ubicaciones.add(ubicacion);
            }
        }
        return anadido;
    }

    public Ubicacion getUbicacionPorToponimo(String name) {
        return null;
    }

    public Ubicacion getUbicacionPorCoordenadas(double latitud, double longitud) {
        return null;
    }

    public boolean activarUbicacion(String toponimo){
        for (Ubicacion ubicacion : ubicaciones){
            if (ubicacion.getToponimo().equals(toponimo)){
                return ubicacion.activar();
            }
        }
        return false;
    }

}
