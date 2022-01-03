package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import java.util.HashMap;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y Currents)

public class GestorServicios {

    private ServicioGeocoding servicioGeocoding;
    private ServicioOpenWeather servicioOpenWeather;
    private ServicioCurrents servicioCurrents;

    protected GestorServicios() {
        servicioGeocoding = new ServicioGeocoding();
        servicioOpenWeather = null;
        servicioCurrents = null;
    }
    
    //-------------------------------------------------------------------------------------------//
    //GEOCODE

    public void setServicioGeocoding(ServicioGeocoding servicioGeocoding) {
        this.servicioGeocoding = servicioGeocoding;
    }

    public ServicioGeocoding getServicioGeocoding() {
        return servicioGeocoding;
    }

    public Ubicacion getInformacionPorToponimo(String toponimo) {
        return servicioGeocoding.getInformacion(toponimo);
    }

    public Ubicacion getInformacionPorCoordenadas(String latitud, String longitud) {
        if ((Double.parseDouble(latitud) > -90.0 && Double.parseDouble(latitud) < 90.0)
                && (Double.parseDouble(longitud) > -180.0 && Double.parseDouble(longitud) < 180.0))
            return servicioGeocoding.getInformacion(latitud, longitud);
        return null;
    }

    //-------------------------------------------------------------------------------------------//
    //OPENWEATHER
    public void setServicioOpenWeather(ServicioOpenWeather servicioOpenWeather) {
        this.servicioOpenWeather = servicioOpenWeather;
    }

    public ServicioOpenWeather getServicioOpenWeather() {
        return servicioOpenWeather;
    }

    public HashMap<String, String> getTiempoPorUbicacion(Ubicacion ubicacion) {
        if (servicioOpenWeather != null && ubicacion != null) {
            if (ubicacion.isServicioActivo("openweather") && ubicacion.isActivada()) {
                return servicioOpenWeather.getInformacion(ubicacion.getLatitud(), ubicacion.getLongitud());
            }
        }
        return null;
    }


    //-------------------------------------------------------------------------------------------//
    //CURRENTS
    public void setServicioCurrents(ServicioCurrents servicioCurrents) {
        this.servicioCurrents = servicioCurrents;
    }

    public ServicioCurrents getServicioCurrents() {
        return servicioCurrents;
    }
    
    public HashMap<String, HashMap<String, String>> getNoticiasPorUbicacion(Ubicacion ubicacion){
        if (servicioCurrents != null && ubicacion != null) {
            if (ubicacion.isServicioActivo("currents") && ubicacion.isActivada()) {
                return servicioCurrents.getInformacion(ubicacion.getToponimo());
            }
        }
        return null;
    }
}
