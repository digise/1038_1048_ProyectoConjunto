package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import java.util.HashMap;
import java.util.Map;

//EN ESTA CLASE ESTÁN TODOS LOS SERVICIOS (OpenWeather, Geocoding y Currents)

public class GestorServicios {

    private ServicioGeocoding servicioGeocoding;
    private ServicioOpenWeather servicioOpenWeather;
    private ServicioCurrents servicioCurrents;

    private Map<String, Servicio> servicios;

    private ConexionFirebase conexionFirebase;

    protected GestorServicios() {
        servicioGeocoding = new ServicioGeocoding();
        servicioOpenWeather = null;
        servicioCurrents = null;
        servicios = new HashMap<>();
        conexionFirebase = new ConexionFirebase();
    }

    public Map<String, Servicio> getAllServicios(){
        return servicios;
    }

    public int nServiciosActivos(){
        int total = 0;
        for (Servicio servicio : servicios.values()){
            if (servicio.isActivo())
                total += 1;
        }
        return total;
    }

    public void setConexionFirebase(ConexionFirebase conexionFirebase){
        this.conexionFirebase = conexionFirebase;
    }


    public void activarServicio(String servicio) {
        servicio = servicio.toUpperCase();
        switch (servicio) {
            case "OPENWEATHER":
                ServicioOpenWeather servicioOpenWeather = this.servicioOpenWeather;
                if (servicioOpenWeather != null){
                    servicioOpenWeather.servicioActivo(true);
                }
                break;
            case "CURRENTS":
                ServicioCurrents servicioCurrents = this.servicioCurrents;
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
                ServicioOpenWeather servicioOpenWeather = this.servicioOpenWeather;
                if (servicioOpenWeather != null) {
                    servicioOpenWeather.servicioActivo(false);
                }
                break;
            case "CURRENTS":
                ServicioCurrents servicioCurrents = this.servicioCurrents;
                if (servicioCurrents != null) {
                    servicioCurrents.servicioActivo(false);
                }
                break;
            default:
                break;
        }
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
        if (servicioOpenWeather == null && this.servicioOpenWeather != null) {
            boolean eliminar = eliminarServicioFirebase("openweather");
            if (eliminar)
                servicios.remove("openweather");
        }else{
            if (this.servicioOpenWeather == null && servicioOpenWeather != null) {
                String idDocumento = crearServicioFirebase(servicioOpenWeather);
                if (idDocumento != null)
                    servicios.put("openweather", servicioOpenWeather);
            }
        }
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
        if (servicioCurrents == null && this.servicioCurrents != null) {
            boolean eliminar = eliminarServicioFirebase("currents");
            if (eliminar)
                servicios.remove("currents");
        }else {
            if (this.servicioCurrents == null && servicioCurrents != null) {
                String idDocumento = crearServicioFirebase(servicioCurrents);
                if (idDocumento != null)
                    servicios.put("currents", servicioCurrents);
            }
        }
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

    //Firebase
    public ServicioOpenWeather getServicioOpenWeatherFirebase(){
        return conexionFirebase.getDocument("servicios", "openweather", ServicioOpenWeather.class);
    }

    public ServicioCurrents getServicioCurrentsFirebase(){
        return conexionFirebase.getDocument("servicios", "currents", ServicioCurrents.class);
    }

    public String crearServicioFirebase(Servicio servicio){
        if (servicio.getClass() == ServicioCurrents.class)
            return conexionFirebase.createDocument("servicios", servicio, "currents");
        else
            return conexionFirebase.createDocument("servicios", servicio, "openweather");
    }

    public boolean eliminarServicioFirebase(String idDocumento){
        return conexionFirebase.removeDocument("servicios", idDocumento);
    }

    public boolean updateServicioFirebase(ServicioOpenWeather servicio, String idDocumento){
        return conexionFirebase.updateDocument("servicios", servicio, idDocumento);
    }

    public boolean updateServicioFirebase(ServicioCurrents servicio, String idDocumento){
        return conexionFirebase.updateDocument("servicios", servicio, idDocumento);
    }

}
