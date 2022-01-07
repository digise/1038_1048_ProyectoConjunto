package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;

public class ServicioGeocoding {

    private final String url = "https://geocode.xyz/";
    private final String auth = "734427685642956462938x6476";
    private GeocodingAdapter geocodingAdapter;

    public ServicioGeocoding() {
        geocodingAdapter = new GeocodingAdapter();
    }

    public GeocodingAdapter getGeocodingAdapter() {
        return geocodingAdapter;
    }

    public void setGeocodingAdapter(GeocodingAdapter geocodingAdapter) {
        this.geocodingAdapter = geocodingAdapter;
    }

    public Ubicacion getInformacion(String toponimo) {
        // https://geocode.xyz/castellon?json=1&auth=57673066339488579050x115589
        String tempUrl = url + toponimo;
        tempUrl += "?json=1&auth=" + auth;
        return geocodingAdapter.doRequest(tempUrl);
    }

    public Ubicacion getInformacion(String latitud, String longitud) {
        //https://geocode.xyz/25.311261,-44.156168?geoit=json&auth=57673066339488579050x115589
        String tempUrl = url + latitud + ',' + longitud;
        tempUrl += "?geoit=json&auth=" + auth;
        return geocodingAdapter.doRequest(tempUrl);
    }

}
