package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import java.util.HashMap;

public class ServicioOpenWeather implements Servicio {

    private final String url = "https://api.openweathermap.org/data/2.5/";
    private final String appid = "157e439fdbc93d1c11163f1bc3a488ab";
    private boolean activo;
    private final String descripcion = "";/*"OpenWeather es un equipo de expertos en TI y científicos de datos que ha estado practicando la ciencia de datos meteorológicos profundos desde 2014. Para cada punto del mundo, OpenWeather proporciona datos meteorológicos históricos, actuales y pronosticados a través de API a la velocidad de la luz. Sede en Londres, Reino Unido.\n" +
            "Caracterisitcas de la api gratuita (la que hemos utilizado):\n" +
            "- 60 llamadas/minuto\n" +
            "- Clima actual, pronóstico en minutos para 1 hora, pronóstico por hora para 48 horas, pronóstico diario para 7 días, alertas meteorológicas globales y datos históricos\\n\" +\n" +
            "            \"para 5 días anteriores para cualquier ubicación";*/

    OpenWeatherAdapter openWeatherAdapter;

    public ServicioOpenWeather() {
        this.activo = true;
        this.openWeatherAdapter = new OpenWeatherAdapter();
    }

    public OpenWeatherAdapter getOpenWeatherAdapter() {
        return openWeatherAdapter;
    }

    public void setOpenWeatherAdapter(OpenWeatherAdapter openWeatherAdapter) {
        this.openWeatherAdapter = openWeatherAdapter;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public HashMap<String, String> getInformacion(String toponimo) {
        //https://api.openweathermap.org/data/2.5/weather?q=sagunto&appid=157e439fdbc93d1c11163f1bc3a488ab
        if (activo) {
            String tempUrl = url + "weather?q=" + toponimo + "&appid=" + appid;
            return openWeatherAdapter.doRequest(tempUrl);
        }
        return null;
    }

    public HashMap<String, String> getInformacion(String latitud, String longitud) {
        //https://api.openweathermap.org/data/2.5/onecall?lat=39.6833&lon=-0.2667&exclude=minutely,hourly,daily,alerts&appid=157e439fdbc93d1c11163f1bc3a488ab
        if (activo) {
            String tempUrl = url + "onecall?lat=" + latitud +
                    "&lon=" + longitud +
                    "&exclude=minutely,hourly,daily,alerts&appid=" + appid;
            return openWeatherAdapter.doRequest(tempUrl);
        }
        return null;
    }

    public void servicioActivo(boolean activar){
        activo = activar;
        boolean s = updateServicioFirebase(this, "openweather");
        if (!s)
            activo = !activar;
    }

    public boolean isActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "ServicioOpenWeather{" +
                "activo=" + activo +
                '}';
    }

    //Firebase
    public boolean updateServicioFirebase(ServicioOpenWeather servicio, String idDocumento){
        return Gestor.getInstance().getGestorServicios().updateServicioFirebase(servicio, idDocumento);
    }
}
