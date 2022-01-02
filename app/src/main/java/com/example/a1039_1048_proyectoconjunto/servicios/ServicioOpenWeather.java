package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;

import java.util.HashMap;

public class ServicioOpenWeather implements Servicio {

    private final String url = "https://api.openweathermap.org/data/2.5/";
    private final String appid = "157e439fdbc93d1c11163f1bc3a488ab";
    private boolean activo;

    public ServicioOpenWeather() {
        this.activo = true;
    }

    public HashMap<String, String> getInformacion(String toponimo) {
        //https://api.openweathermap.org/data/2.5/weather?q=sagunto&appid=157e439fdbc93d1c11163f1bc3a488ab
        if (activo) {
            String tempUrl = url + "weather?q=" + toponimo + "&appid=" + appid;
            OpenWeatherAdapter openWeatherAdapter = new OpenWeatherAdapter();
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
            OpenWeatherAdapter openWeatherAdapter = new OpenWeatherAdapter();
            return openWeatherAdapter.doRequest(tempUrl);
        }
        return null;
    }

    public void servicioActivo(boolean activar) {
        activo = activar;
    }

    public boolean isActivo() {
        return activo;
    }
}
