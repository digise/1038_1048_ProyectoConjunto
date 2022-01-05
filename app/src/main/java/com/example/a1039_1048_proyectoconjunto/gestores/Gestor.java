package com.example.a1039_1048_proyectoconjunto.gestores;

// ESTA CLASE ESTÁ A UN NIVEL MAS ALTO QUE LOS GESTORES DE SERVICIO Y UBICACIONES, Y ES LA QUE
// APLICA LA LÓGICA.


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gestor {

    //Singleton
    private static Gestor INSTANCE;

    private GestorUbicaciones gestorUbicaciones;
    private GestorServicios gestorServicios;

    public Gestor() {
        this.gestorUbicaciones = new GestorUbicaciones();
        this.gestorServicios = new GestorServicios();
    }

    public synchronized static Gestor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Gestor();
        }
        return INSTANCE;
    }

    public void recuperarTodaLaInformacionDeLaAplicacion(){
        gestorServicios.recuperarInformacionServicios();
        gestorUbicaciones.recuperarInformacionUbicaciones();
    }

    public void borrarTodaLaInformacionDeLaAplicacion(){
        ConexionFirebase conexionFirebase = new ConexionFirebase();
        conexionFirebase.removeDocument("","");
        gestorServicios.setServicioCurrents(null);
        gestorServicios.setServicioOpenWeather(null);
        gestorServicios.getAllServicios().clear();
        gestorUbicaciones.getAllUbicaciones().clear();

    }

    public GestorUbicaciones getGestorUbicaciones() {
        return gestorUbicaciones;
    }

    public void setGestorUbicaciones(GestorUbicaciones gestorUbicaciones){
        this.gestorUbicaciones = gestorUbicaciones;
    }
    public void setGestorServicios(GestorServicios gestorServicios){
        this.gestorServicios = gestorServicios;
    }

    public void borrarGestor(){
        INSTANCE = null;
    }

    public GestorServicios getGestorServicios() {
        return gestorServicios;
    }

    public Ubicacion getUbicacionPorToponimo(String toponimo) {
        return gestorServicios.getInformacionPorToponimo(toponimo);
    }

    public Ubicacion getUbicacionPorCoordenadas(String latitud, String longitud) {
        return gestorServicios.getInformacionPorCoordenadas(latitud, longitud);
    }

    public boolean darAltaUbicacion(Ubicacion ubicacion) {
        return gestorUbicaciones.darAltaUbicacion(ubicacion);
    }

    public boolean darBajaUbicacion(Ubicacion ubicacion){
        return gestorUbicaciones.darBajaUbicacion(ubicacion);
    }

    public boolean validarToponimo(String servicio, String toponimo) {
        switch (servicio) {
            case "GEOCODING":
                return gestorServicios.getServicioGeocoding().getInformacion(toponimo) != null;
            case "OPENWEATHER":
                return gestorServicios.getServicioOpenWeather().getInformacion(toponimo) != null;
            case "CURRENTS":
                return gestorServicios.getServicioCurrents().getInformacion(toponimo) != null;
            default:
                return false;
        }
    }

    public boolean validarCoordenadas(String servicio, String latitud, String longitud) {
        switch (servicio.toUpperCase()) {
            case "GEOCODING":
                return gestorServicios.getServicioGeocoding().getInformacion(latitud, longitud) != null;
            case "OPENWEATHER":
                return gestorServicios.getServicioOpenWeather().getInformacion(latitud, longitud) != null;
            default:
                return false;
        }
    }

    public void activarServicio(String servicio) {
        gestorServicios.activarServicio(servicio);
    }

    public void desactivarServicio(String servicio) {
        gestorServicios.desactivarServicio(servicio);
    }

    public HashMap<String, String> getTiempoPorUbicacion(Ubicacion ubicacion) {
        return gestorServicios.getTiempoPorUbicacion(ubicacion);
    }

    public HashMap<String, HashMap<String, String>> getNoticiasPorUbicacion(Ubicacion ubicacion) {
        return gestorServicios.getNoticiasPorUbicacion(ubicacion);
    }

    public Ubicacion getUbicacionGuardada(String toponimo) {
        toponimo = toponimo.toLowerCase();
        for (Ubicacion ubicacion : getAllUbicaciones().values()) {
            if (ubicacion.getToponimo().toLowerCase().equals(toponimo)) {
                return ubicacion;
            }
        }
        return null;
    }

    public Map<String, Servicio> getAllServicios(){
        return gestorServicios.getAllServicios();
    }

    public Map<String, Ubicacion> getAllUbicaciones() {
        return gestorUbicaciones.getAllUbicaciones();
    }

    public List<Ubicacion> getUbicacionesOrdenadas(String tipo){
        String tipoFormateado = tipo.toLowerCase();
        switch (tipoFormateado){
            case "alfabeticamente":
                return gestorUbicaciones.getUbicacionesOrdenadasAlfabeticamente();
            case "recientemente":
                return gestorUbicaciones.getUbicacionesOrdenadasRecientes();
            default:
                return null;
        }
    }

    public Map<String, Ubicacion> getUbicacionesActivas(){
        return gestorUbicaciones.getUbicacionesActivas();
    }

    public boolean activarUbicacion(String toponimo) {
        return gestorUbicaciones.activarUbicacion(toponimo);
    }

    public boolean desactivarUbicacion(String toponimo) {
        return gestorUbicaciones.desactivarUbicacion(toponimo);
    }

    public boolean marcarComoFavorita(Ubicacion ubicacion, boolean marcar){
        return ubicacion.setFavorita(marcar);
    }

    public List<Ubicacion> getUbicacionesFavoritas(){
        return gestorUbicaciones.getUbicacionesFavoritas();
    }

    public void borrarServicio(String servicio) {
        switch (servicio.toUpperCase()) {
            case "CURRENTS":
                if (gestorServicios.getServicioCurrents() != null){
                    gestorServicios.setServicioCurrents(null);
                }
                break;
            case "OPENWEATHER":
                if (gestorServicios.getServicioOpenWeather() != null){
                    gestorServicios.setServicioOpenWeather(null);
                }
                break;
            default:
                break;
        }
    }





}
