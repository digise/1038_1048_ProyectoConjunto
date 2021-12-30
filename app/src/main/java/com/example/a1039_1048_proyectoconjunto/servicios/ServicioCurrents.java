package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;

import java.util.HashMap;

public class ServicioCurrents implements Servicio{
    private final String url = "https://api.openweathermap.org/data/2.5/weather";
    private final String appid = "157e439fdbc93d1c11163f1bc3a488ab";
    private boolean activo;
    
    public ServicioCurrents(){
        activo = true;
    }

    public HashMap<String, String> getInformacionPorToponimo(String toponimo) {
        // https://geocode.xyz/castellon?json=1&auth=57673066339488579050x115589
        return null;
    }

    public HashMap<String, String> getInformacionPorCoordenadas(Coordenadas coordenadas) {
        return null;
    }
    public void servicioActivo(boolean activar){
        activo = activar;
    }

    public boolean isActivo() {
        return activo;
    }
}
