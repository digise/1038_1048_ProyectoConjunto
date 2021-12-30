package com.example.a1039_1048_proyectoconjunto.gestores;

import android.content.Context;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y Currents)

public class GestorServicios {

    ServicioGeocoding servicioGeocoding;
    ServicioOpenWeather servicioOpenWeather;

    protected GestorServicios() {
        servicioGeocoding = null;
        servicioOpenWeather = null;
    }

    //-------------------------------------------------------------------------------------------//
    //GEOCODE

    public void setServicioGeocoding(ServicioGeocoding servicioGeocoding) {
        this.servicioGeocoding = servicioGeocoding;
    }

    public ServicioGeocoding getServicioGeocoding() {
        return servicioGeocoding;
    }

    public Ubicacion darAltaUbicacionPorToponimo(String toponimo){
        return servicioGeocoding.getInformacionPorToponimo(toponimo);
    }

    public Ubicacion darAltaUbicacionPorCoordenadas(Coordenadas coordenadas){
        if ((Double.parseDouble(coordenadas.getLatitud()) > -90.0 && Double.parseDouble(coordenadas.getLatitud()) < 90.0)
                && (Double.parseDouble(coordenadas.getLongitud()) > -180.0 && Double.parseDouble(coordenadas.getLongitud()) < 180.0))
            return servicioGeocoding.getInformacionPorCoordenadas(coordenadas);
        return null;
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
