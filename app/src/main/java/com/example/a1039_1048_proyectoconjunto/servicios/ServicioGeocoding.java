package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;

public class ServicioGeocoding {

    private final String url = "https://geocode.xyz/";
    private final String auth = "57673066339488579050x115589";
    private boolean activo;

    public ServicioGeocoding() {
        activo = true;
    }

    public Ubicacion getInformacionPorToponimo(String toponimo) {
        // https://geocode.xyz/castellon?json=1&auth=57673066339488579050x115589
        String tempUrl = url + toponimo;
        tempUrl += "?json=1&auth=" + auth;
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
        return geocodingAdapter.doRequest(tempUrl);
    }

    public Ubicacion getInformacionPorCoordenadas(Coordenadas coordenadas) {
        String tempUrl = url + coordenadas.toString();
        tempUrl += "?geoit=json&auth=" + auth;
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
        return geocodingAdapter.doRequest(tempUrl);
    }
    public void servicioActivo(boolean activar){
        activo = activar;
    }

    public boolean isActivo() {
        return activo;
    }
}
