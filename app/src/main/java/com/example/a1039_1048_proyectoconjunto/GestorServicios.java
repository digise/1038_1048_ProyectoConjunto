package com.example.a1039_1048_proyectoconjunto;

import java.util.HashSet;
import java.util.Set;

public class GestorServicios {

    private static GestorServicios INSTANCE;

    private GestorServicios() {
    }

    public synchronized  static GestorServicios getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GestorServicios();
            INSTANCE.servicioGeocoding = new ServicioGeocoding();
            INSTANCE.servicioOpenWeather = new ServicioOpenWeather();
        }
        return INSTANCE;
    }


    private ServicioGeocoding servicioGeocoding;
    private ServicioOpenWeather servicioOpenWeather;


    public ServicioGeocoding getServicioGeocoding() {
        return servicioGeocoding;
    }

    public boolean addServicio(Servicio servicio){
        return false;
    }

    public Ubicacion getUbicacionByToponimo(String toponimo) {
        return null;
    }

    public String getInfoOpenWeather(){
        return null;
    }

    public boolean isValidOpenWeather(){
        return false;
    }
}
