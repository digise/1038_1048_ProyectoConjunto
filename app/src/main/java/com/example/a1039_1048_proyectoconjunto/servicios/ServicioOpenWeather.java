package com.example.a1039_1048_proyectoconjunto.servicios;

public class ServicioOpenWeather implements Servicio {

    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "157e439fdbc93d1c11163f1bc3a488ab";

    public boolean isValid(String toponimo){
        return false;
    }

    public boolean isValid(double latitud, double longitud){
        return false;
    }

}
