package com.example.a1039_1048_proyectoconjunto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GestorServicios {

    //Singleton
    private static GestorServicios INSTANCE;

    private GestorServicios() {
    }

    public synchronized  static GestorServicios getInstance(){
        if (INSTANCE == null){
            INSTANCE = new GestorServicios();
            INSTANCE.servicios = new HashMap<>();
        }
        return INSTANCE;
    }



    private HashMap<String, Servicio> servicios;
    private ServicioGeocoding servicioGeocoding;

    public Servicio addServicio(String nombre, Servicio servicio){
        return null;
    }

    public Servicio getServicio(String nombre){
        return null;
    }

    public Servicio removeServicio(String nombre){
        return null;
    }

    public Servicio getServicioGeoCoding(){
        return null;
    }

    public void setServicioGeocoding(ServicioGeocoding servicioGeocoding) {
    }

    //OpenWeather
    public String getInfoOpenWeather(Ubicacion ubicacion){
        return null;
    }
}
