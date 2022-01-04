package com.example.a1039_1048_proyectoconjunto.gestores;

// ESTA CLASE ESTÁ A UN NIVEL MAS ALTO QUE LOS GESTORES DE SERVICIO Y UBICACIONES, Y ES LA QUE
// APLICA LA LÓGICA.


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Gestor implements Serializable {

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

    public GestorUbicaciones getGestorUbicaciones() {
        return gestorUbicaciones;
    }

    public void setGestorUbicaciones(GestorUbicaciones gestorUbicaciones){
        this.gestorUbicaciones = gestorUbicaciones;
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
        servicio = servicio.toUpperCase();
        switch (servicio) {
            case "OPENWEATHER":
                ServicioOpenWeather servicioOpenWeather = gestorServicios.getServicioOpenWeather();
                if (servicioOpenWeather != null){
                    servicioOpenWeather.servicioActivo(true);
                }
                break;
            case "CURRENTS":
                ServicioCurrents servicioCurrents = gestorServicios.getServicioCurrents();
                if (servicioCurrents != null){
                    servicioCurrents.servicioActivo(true);
                }
                break;
            default:
                break;
        }
    }

    public void desactivarServicio(String servicio) {
        servicio = servicio.toUpperCase();
        switch (servicio) {
            case "OPENWEATHER":
                ServicioOpenWeather servicioOpenWeather = gestorServicios.getServicioOpenWeather();
                if (servicioOpenWeather != null) {
                    servicioOpenWeather.servicioActivo(false);
                }
                break;
            case "CURRENTS":
                ServicioCurrents servicioCurrents = gestorServicios.getServicioCurrents();
                if (servicioCurrents != null) {
                    servicioCurrents.servicioActivo(false);
                }
                break;
            default:
                break;
        }
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

    public Map<String, Ubicacion> getUbicacionesActivas(){
        return gestorUbicaciones.getUbicacionesActivas();
    }

    public boolean activarUbicacion(String toponimo) {
        return gestorUbicaciones.activarUbicacion(toponimo);
    }

    public boolean desactivarUbicacion(String toponimo) {
        return gestorUbicaciones.desactivarUbicacion(toponimo);
    }

    public List<Ubicacion> getListaHastaTresUbicacionesMostradas(){
        return gestorUbicaciones.getListaHastaTresUbicacionesMostradas();
    }

    public boolean replaceEnListaTresUbicaciones(Ubicacion ubicacionVieja, Ubicacion ubicacionNueva){
        return gestorUbicaciones.replaceEnListaTresUbicaciones(ubicacionVieja, ubicacionNueva);
    }


}
