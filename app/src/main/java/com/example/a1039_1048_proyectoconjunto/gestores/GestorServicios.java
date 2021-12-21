package com.example.a1039_1048_proyectoconjunto.gestores;

import com.example.a1039_1048_proyectoconjunto.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y TicketMaster)

public class GestorServicios {

    ServicioGeocoding servicioGeocoding;
    ServicioOpenWeather servicioOpenWeather;

    protected GestorServicios() {
        servicioGeocoding = null;
        servicioOpenWeather = null;
    }

    public Servicio addServicio(String nombre, Servicio servicio) {
        return null;
    }

    public Servicio getServicio(String nombre) {
        return null;
    }

    public Servicio removeServicio(String nombre) {
        return null;
    }

    //Geocoding
    public Servicio getServicioGeoCoding() {
        return null;
    }

    public void setServicioGeocoding(ServicioGeocoding servicioGeocoding) {
    }

    public ServicioGeocoding getServicioGeocoding() {
        return servicioGeocoding;
    }

    //OpenWeather
    public void setServicioOpenWeather(ServicioOpenWeather servicioOpenWeather) {

    }

    public ServicioOpenWeather getServicioOpenWeather(ServicioOpenWeather servicioOpenWeather) {
        return servicioOpenWeather;
    }

    public String getInfoOpenWeather(Ubicacion ubicacion) {
        return null;
    }
}
