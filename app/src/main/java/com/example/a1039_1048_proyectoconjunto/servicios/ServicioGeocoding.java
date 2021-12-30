package com.example.a1039_1048_proyectoconjunto.servicios;

import android.content.Context;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.google.protobuf.CodedOutputStream;

public class ServicioGeocoding implements Servicio{

    private final String url = "https://geocode.xyz/";
    private final String auth = "57673066339488579050x115589";

    public ServicioGeocoding() {}

    public Ubicacion getInformacionPorToponimo(String toponimoCoords) {
        // https://geocode.xyz/castellon?json=1&auth=57673066339488579050x115589
        String tempUrl = url + toponimoCoords;
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
}
