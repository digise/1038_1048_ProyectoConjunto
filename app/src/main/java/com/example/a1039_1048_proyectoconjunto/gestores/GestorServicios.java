package com.example.a1039_1048_proyectoconjunto.gestores;


import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
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
        servicios = new HashMap<>();
    }

    public Map<String, Servicio> getAllServicios() {
        return servicios;
    }

    public int nServiciosActivos() {
        int total = 0;
        for (Servicio servicio : servicios.values()) {
            if (servicio.isActivo())
                total += 1;
        }
        return total;
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
        if (servicioOpenWeather == null && servicios.containsKey("openweather")) {
            boolean eliminar = eliminarServicioFirebase("openweather");
            if (eliminar)
                servicios.remove("openweather");
        } else {
            if (this.servicioOpenWeather == null) {
                this.servicioOpenWeather = servicioOpenWeather;
                String idDocumento = crearServicioFirebase(servicioOpenWeather);
                if (idDocumento != null)
                    servicios.put("openweather", servicioOpenWeather);
            }
        }
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
        if (servicioCurrents == null && servicios.containsKey("currents")) {
            boolean eliminar = eliminarServicioFirebase("currents");
            if (eliminar)
                servicios.remove("currents");
        } else {
            if (this.servicioCurrents == null) {
                this.servicioCurrents = servicioCurrents;
                String idDocumento = crearServicioFirebase(servicioCurrents);
                if (idDocumento != null)
                    servicios.put("currents", servicioCurrents);
            }
        }
    }

    public ServicioCurrents getServicioCurrents() {
        return servicioCurrents;
    }

    public HashMap<String, HashMap<String, String>> getNoticiasPorUbicacion(Ubicacion ubicacion) {
        if (servicioCurrents != null && ubicacion != null) {
            if (ubicacion.isServicioActivo("currents") && ubicacion.isActivada()) {
                return servicioCurrents.getInformacion(ubicacion.getToponimo());
            }
        }
        return null;
    }

    //Firebase
    public ServicioOpenWeather getServicioOpenWeatherFirebase() {
        return ConexionFirebase.getDocument("servicios", "openweather", ServicioOpenWeather.class);
    }

    public ServicioCurrents getServicioCurrentsFirebase() {
        return ConexionFirebase.getDocument("servicios", "currents", ServicioCurrents.class);
    }

    public String crearServicioFirebase(Servicio servicio) {
        if (servicio.getClass() == ServicioCurrents.class)
            return ConexionFirebase.createDocument("servicios", servicio, "currents");
        else
            return ConexionFirebase.createDocument("servicios", servicio, "openweather");
    }

    public boolean eliminarServicioFirebase(String idDocumento) {
        return ConexionFirebase.removeDocument("servicios", idDocumento);
    }

}
