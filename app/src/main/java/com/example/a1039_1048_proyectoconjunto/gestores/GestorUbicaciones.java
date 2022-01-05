package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;


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
        /*
        Map<String, Object> objectosUbicaciones = getCollectionFirebase();
        ubicaciones = objectosUbicaciones.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (Ubicacion) e.getValue()));
        */
        generarUbicaciones();
        listaHastaTresUbicacionesMostradas = FixedSizeList.fixedSizeList(Arrays.asList(new Ubicacion[3]));
        generarListaTresUbicaciones();
    }

    private void generarListaTresUbicaciones(){
        for (Ubicacion ubicacion: ubicaciones.values()) {
            if (ubicacion.isEnListaTresUbicaciones()){
                listaHastaTresUbicacionesMostradas.add(ubicacion);
            }
        }
    }

    public void generarUbicaciones(){
        ubicaciones = getUbicacionesFirebase();
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
                ubicaciones.put(idDocumento, ubicacion);
                ubicaciones.get(idDocumento).setIdDocumento(idDocumento);
                anadido = ConexionFirebase.updateDocument("ubicaciones", ubicacion, idDocumento);
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


    public boolean removeDocument(String referencia, String idDocumento){
        return ConexionFirebase.removeDocument(referencia, idDocumento);
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

    public List<Ubicacion> getListaHastaTresUbicacionesMostradas(){
        return listaHastaTresUbicacionesMostradas;
    }

    public boolean replaceEnListaTresUbicaciones(Ubicacion ubicacionVieja, Ubicacion ubicacionNueva){
        boolean cambiado = false;
        for (int i = 0; i < listaHastaTresUbicacionesMostradas.size(); i++) {
            Ubicacion ubicacionActual = listaHastaTresUbicacionesMostradas.get(i);
            if (ubicacionActual.equals(ubicacionVieja)){
                listaHastaTresUbicacionesMostradas.set(i, ubicacionNueva);
                cambiado = true;
            }
        }
        return cambiado;
    }

    //Firebase

    public Map<String, Ubicacion> getUbicacionesFirebase(){
        return ConexionFirebase.getCollection("ubicaciones", Ubicacion.class);
    }


}
