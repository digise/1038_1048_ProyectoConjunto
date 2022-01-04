package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y Currents)

public class GestorServicios {

    private ServicioGeocoding servicioGeocoding;
    private ServicioOpenWeather servicioOpenWeather;
    private ServicioCurrents servicioCurrents;

    private Map<String, Servicio> servicios;

    protected GestorServicios() {
        servicioGeocoding = new ServicioGeocoding();
        servicioOpenWeather = null;
        servicioCurrents = null;
        generarServicios();
    }

    public Map<String, Object> getServiciosFirebase(){
        return ConexionFirebase.getCollection("servicios");
    }
    public Map<String, Servicio> getAllServicios(){
        return servicios;
    }

    public void generarServicios(){
        Map<String, Object> objetosServicios = getServiciosFirebase();
        servicios = objetosServicios.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (Servicio) e.getValue()));
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
        /*String idDocumento = ConexionFirebase.createDocument("servicios", servicioOpenWeather, null);
        if (idDocumento!=null)
            servicios.put("openweather", servicioOpenWeather);*/
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
        /*String idDocumento = ConexionFirebase.createDocument("servicios", servicioCurrents, null);
        if (idDocumento!=null)
            servicios.put("currents", servicioCurrents);*/
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
