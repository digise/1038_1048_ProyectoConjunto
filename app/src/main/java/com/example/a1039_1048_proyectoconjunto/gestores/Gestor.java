package com.example.a1039_1048_proyectoconjunto.gestores;

// ESTA CLASE ESTÁ A UN NIVEL MAS ALTO QUE LOS GESTORES DE SERVICIO Y UBICACIONES, Y ES LA QUE
// APLICA LA LÓGICA.

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import java.io.Serializable;
import java.util.Locale;
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

    public GestorServicios getGestorServicios() {
        return gestorServicios;
    }

    public boolean darAltaUbicacionPorToponimo(String toponimo) {
        return gestorUbicaciones.addUbicacion(gestorServicios.darAltaUbicacionPorToponimo(toponimo));
    }

    public boolean darAltaUbicacionPorCoordenadas(Coordenadas coordenadas) {
        return gestorUbicaciones.addUbicacion(gestorServicios.darAltaUbicacionPorCoordenadas(coordenadas));
    }

    public boolean validarToponimo(String servicio, String toponimo) {
        switch (servicio) {
            case "GEOCODING":
                return gestorServicios.getServicioGeocoding().getInformacionPorToponimo(toponimo) != null;
            case "OPENWEATHER":
                return gestorServicios.getServicioOpenWeather().getInformacionPorToponimo(toponimo) != null;
            case "CURRENTS":
                return gestorServicios.getServicioCurrents().getInformacionPorToponimo(toponimo) != null;
            default:
                return false;
        }
    }

    public boolean validarCoordenadas(String servicio, Coordenadas coordenadas) {
        switch (servicio.toUpperCase()) {
            case "GEOCODING":
                return gestorServicios.getServicioGeocoding().getInformacionPorCoordenadas(coordenadas) != null;
            case "OPENWEATHER":
                return gestorServicios.getServicioOpenWeather().getInformacionPorCoordenadas(coordenadas) != null;
            case "CURRENTS":
                return gestorServicios.getServicioCurrents().getInformacionPorCoordenadas(coordenadas) != null;
            default:
                return false;
        }
    }

    public void activarServicio(String servicio) {
        servicio = servicio.toUpperCase();
        switch (servicio) {
            case "GEOCODING":
                gestorServicios.getServicioGeocoding().servicioActivo(true);
                break;
            case "OPENWEATHER":
                gestorServicios.getServicioOpenWeather().servicioActivo(true);
                break;
            case "CURRENTS":
                gestorServicios.getServicioCurrents().servicioActivo(true);
                break;
            default:
                break;
        }
    }

    public void desactivarServicio(String servicio) {
        servicio = servicio.toUpperCase();
        switch (servicio) {
            case "GEOCODING":
                ServicioGeocoding servicioGeocoding = gestorServicios.getServicioGeocoding();
                if (servicioGeocoding != null){
                    servicioGeocoding.servicioActivo(false);
                }
                break;
            case "OPENWEATHER":
                ServicioOpenWeather servicioOpenWeather = gestorServicios.getServicioOpenWeather();
                if (servicioOpenWeather != null){
                    servicioOpenWeather.servicioActivo(false);
                }
                break;
            case "CURRENTS":
                ServicioCurrents servicioCurrents = gestorServicios.getServicioCurrents();
                if (servicioCurrents != null){
                    servicioCurrents.servicioActivo(false);
                }
                break;
            default:
                break;
        }
    }

    public Ubicacion getUbicacion(String toponimo) {
        for (Ubicacion ubicacion : gestorUbicaciones.getUbicaciones()) {
            if (ubicacion.getToponimo().toLowerCase(Locale.ROOT).equals(toponimo)) {
                return ubicacion;
            }
        }
        return null;
    }

    public Set<Ubicacion> getAllUbicaciones() {
        return gestorUbicaciones.getUbicaciones();
    }

    public boolean activarUbicacion(String toponimo) {
        if (gestorServicios.getNumeroServiciosActivos() > 0) {
            return gestorUbicaciones.activarUbicacion(toponimo);
        }
        return false;
    }


}
