package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {
    
    private Map<String, Ubicacion> ubicaciones;
    
    protected GestorUbicaciones() {
        ubicaciones = new HashMap<>();
        Map<String, Object> objectosUbicaciones = getCollectionFirebase();
        ubicaciones = objectosUbicaciones.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (Ubicacion) e.getValue()));
    }

    public Map<String, Object> getCollectionFirebase(){
        return ConexionFirebase.getCollection("ubicaciones");
    }

    public Map<String, Ubicacion> getAllUbicaciones() {
        return ubicaciones;
    }

    public Map<String, Ubicacion> getUbicacionesActivas(){
        Map<String, Ubicacion> ubicacionesActivas = new HashMap<>();
        for (String id: ubicaciones.keySet()){
            Ubicacion ubicacion = ubicaciones.get(id);
            if (ubicacion.isActivada())
                ubicacionesActivas.put(id, ubicacion);
        }
        return ubicacionesActivas;
    }

    public boolean darAltaUbicacion(Ubicacion ubicacion) {
        boolean anadido = false;
        if (ubicacion != null) {
            String idDocumento = ConexionFirebase.createDocument("ubicaciones", ubicacion, null);
            if (idDocumento!=null) {
                anadido = true;
                ubicaciones.put(idDocumento, ubicacion);
            }
        }
        return anadido;
    }

    public boolean darBajaUbicacion(Ubicacion ubicacion){
        boolean borrado = false;
        if (ubicacion != null){
            String idDocumento = null;
            for (String id : ubicaciones.keySet()){
                Ubicacion aux = ubicaciones.get(id);
                if (aux.equals(ubicacion)) {
                    idDocumento = id;
                    break;
                }
            }
            borrado = ConexionFirebase.removeDocument("ubicaciones", idDocumento);
            if (borrado) {
                ubicaciones.remove(idDocumento);
            }
        }
        return borrado;
    }

    public Ubicacion getUbicacionPorToponimo(String name) {
        return null;
    }

    public Ubicacion getUbicacionPorCoordenadas(double latitud, double longitud) {
        return null;
    }

    public boolean activarUbicacion(String toponimo){
        for (Ubicacion ubicacion : ubicaciones.values()){
            if (ubicacion.getToponimo().equalsIgnoreCase(toponimo)){
                return ubicacion.activar();
            }
        }
        return false;
    }

    public boolean desactivarUbicacion(String toponimo) {
        for (Ubicacion ubicacion : ubicaciones.values()){
            if (ubicacion.getToponimo().equalsIgnoreCase(toponimo)){
                return ubicacion.desactivar();
            }
        }
        return false;
    }
}
