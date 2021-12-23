package com.example.a1039_1048_proyectoconjunto.gestores;

import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y TicketMaster)

public class GestorServicios {

    ServicioGeocoding servicioGeocoding;
    ServicioOpenWeather servicioOpenWeather;

    public GestorServicios() {
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

    //-------------------------------------------------------------------------------------------//
    //GEOCODE
    public ServicioGeocoding getServicioGeoCoding() {
        return this.servicioGeocoding;
    }

    public void setServicioGeocoding(ServicioGeocoding servicioGeocoding) {
    }

    public ServicioGeocoding getServicioGeocoding() {
        return servicioGeocoding;
    }

    //-------------------------------------------------------------------------------------------//
    //OPENWEATHER
    public void setServicioOpenWeather(ServicioOpenWeather servicioOpenWeather) {

    }

    public ServicioOpenWeather getServicioOpenWeather(ServicioOpenWeather servicioOpenWeather) {
        return servicioOpenWeather;
    }

    public String getInfoOpenWeather(Ubicacion ubicacion) {
        return null;
    }
}
