package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;


import org.apache.commons.collections4.list.FixedSizeList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {

    private Map<String, Ubicacion> ubicaciones;
    private List<Ubicacion> listaHastaTresUbicacionesMostradas;

    protected GestorUbicaciones() {
        ubicaciones = new HashMap<>();
        Map<String, Object> objectosUbicaciones = getCollectionFirebase();
        ubicaciones = objectosUbicaciones.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (Ubicacion) e.getValue()));
        listaHastaTresUbicacionesMostradas = FixedSizeList.fixedSizeList(Arrays.asList(new Ubicacion[3]));
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
            borrado = removeDocument("ubicaciones", idDocumento);
            if (borrado) {
                ubicaciones.remove(idDocumento);
            }
        }
        return borrado;
    }
    public Map<String, Object> getCollectionFirebase(){
        return ConexionFirebase.getCollection("ubicaciones");
    }

    public boolean removeDocument(String referencia, String idDocumento){
        return ConexionFirebase.removeDocument("ubicaciones", idDocumento);
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
