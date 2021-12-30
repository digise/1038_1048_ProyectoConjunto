package com.example.a1039_1048_proyectoconjunto.gestores;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y Currents)

public class GestorServicios {

    private ServicioGeocoding servicioGeocoding;
    private ServicioOpenWeather servicioOpenWeather;
    private ServicioCurrents servicioCurrents;

    protected GestorServicios() {
        servicioGeocoding = null;
        servicioOpenWeather = null;
        servicioCurrents = null;
    }
    
    public int getNumeroServiciosActivos(){
        int nServiciosActivos = 0;
        if (servicioGeocoding != null){
            if (servicioGeocoding.isActivo()){
                nServiciosActivos+=1;
            }
        }
        if (servicioOpenWeather != null){
            if (servicioOpenWeather.isActivo()){
                nServiciosActivos+=1;
            }
        }
        if (servicioCurrents != null){
            if (servicioCurrents.isActivo()){
                nServiciosActivos+=1;
            }
        }
        return nServiciosActivos;
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
        this.servicioOpenWeather = servicioOpenWeather;
    }

    public ServicioOpenWeather getServicioOpenWeather() {
        return servicioOpenWeather;
    }

    public String getInfoOpenWeather(Ubicacion ubicacion) {
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
}
