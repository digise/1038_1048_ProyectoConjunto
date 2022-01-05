package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;


import org.apache.commons.collections4.list.FixedSizeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//EN ESTA CLASE ESTÁN TODAS LAS UBICACIONES

public class GestorUbicaciones {

    private Map<String, Ubicacion> ubicaciones;
    private int ultimoNumUbicacionAnadido;
    private List<Ubicacion> ubicacionesOrdenadasRecientes;
    private List<Ubicacion> ubicacionesOrdenadasAlfabeticamente;

    protected GestorUbicaciones() {
        ubicaciones = new HashMap<>();
        generarUbicaciones();
        generarUbicacionesOrdenadasRecientes(); //para saber ultimo num añadido
    }

    public void generarUbicaciones() {
        ubicaciones = getUbicacionesFirebase();
    }

    public void generarUbicacionesOrdenadasRecientes() {
        ubicacionesOrdenadasRecientes = new ArrayList<>(ubicaciones.values());
        ubicacionesOrdenadasRecientes.sort(new Comparator<Ubicacion>() {
            @Override
            public int compare(Ubicacion o1, Ubicacion o2) {
                return o1.getNumCreacion() - o2.getNumCreacion();
            }
        });
        if (ubicacionesOrdenadasRecientes.isEmpty()) {
            ultimoNumUbicacionAnadido = 0;
        } else {
            ultimoNumUbicacionAnadido =
                    ubicacionesOrdenadasRecientes.get(ubicacionesOrdenadasRecientes.size() - 1).getNumCreacion();
        }
    }


    public Map<String, Ubicacion> getAllUbicaciones() {
        return ubicaciones;
    }

    public List<Ubicacion> getUbicacionesOrdenadasRecientes() {
        return ubicacionesOrdenadasRecientes;
    }

    public List<Ubicacion> getUbicacionesOrdenadasAlfabeticamente() {
        return ubicacionesOrdenadasAlfabeticamente;
    }

    public Map<String, Ubicacion> getUbicacionesActivas() {
        Map<String, Ubicacion> ubicacionesActivas = new HashMap<>();
        for (String id : ubicaciones.keySet()) {
            Ubicacion ubicacion = ubicaciones.get(id);
            if (ubicacion.isActivada())
                ubicacionesActivas.put(id, ubicacion);
        }
        return ubicacionesActivas;
    }

    public boolean darAltaUbicacion(Ubicacion ubicacion) {
        boolean anadido = false;
        if (ubicacion != null) {
            ubicacion.setNumCreacion(ultimoNumUbicacionAnadido);
            ultimoNumUbicacionAnadido += 1;
            String idDocumento = crearUbicacionFirebase(ubicacion);
            if (idDocumento != null) {
                ubicaciones.put(idDocumento, ubicacion);
                ubicaciones.get(idDocumento).setIdDocumento(idDocumento);
                anadido = updateUbicacionFirebase(ubicacion, idDocumento);
            }
        }
        return anadido;
    }

    public boolean darBajaUbicacion(Ubicacion ubicacion) {
        boolean borrado = false;
        if (ubicacion != null) {
            String idDocumento = null;
            for (String id : ubicaciones.keySet()) {
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


    public boolean removeDocument(String referencia, String idDocumento) {
        return ConexionFirebase.removeDocument(referencia, idDocumento);
    }

    public Ubicacion getUbicacionPorToponimo(String name) {
        return null;
    }

    public Ubicacion getUbicacionPorCoordenadas(double latitud, double longitud) {
        return null;
    }

    public boolean activarUbicacion(String toponimo) {
        for (Ubicacion ubicacion : ubicaciones.values()) {
            if (ubicacion.getToponimo().equalsIgnoreCase(toponimo)) {
                return ubicacion.activar();
            }
        }
        return false;
    }


    public boolean desactivarUbicacion(String toponimo) {
        for (Ubicacion ubicacion : ubicaciones.values()) {
            if (ubicacion.getToponimo().equalsIgnoreCase(toponimo)) {
                return ubicacion.desactivar();
            }
        }
        return false;
    }

    //Firebase

    public Map<String, Ubicacion> getUbicacionesFirebase() {
        return ConexionFirebase.getCollection("ubicaciones", Ubicacion.class);
    }

    public String crearUbicacionFirebase(Ubicacion ubicacion){
        return ConexionFirebase.createDocument("ubicaciones", ubicacion, null);
    }

    public boolean updateUbicacionFirebase(Ubicacion ubicacion, String idDocumento){
        return ConexionFirebase.updateDocument("ubicaciones", ubicacion, idDocumento);
    }
}
