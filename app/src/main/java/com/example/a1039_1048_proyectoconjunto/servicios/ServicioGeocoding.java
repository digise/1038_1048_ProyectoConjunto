package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;

public class ServicioGeocoding {

    private final String url = "https://geocode.xyz/";
    private final String auth = "57673066339488579050x115589";

    public ServicioGeocoding() {

    }

    public Ubicacion getInformacion(String tipo, String toponimoCoords) {
        if (tipo.equals("toponimo")) {
            return getUbicacionByToponimo(toponimoCoords);
        } else {
            return getUbicacionByCoordenadas(toponimoCoords);
        }
    }


    private Ubicacion getUbicacionByToponimo(String toponimoCoords) {
        String tempUrl = url + toponimoCoords;
        tempUrl += "?json=1&auth=" + auth;
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
        return geocodingAdapter.doRequest(tempUrl);
    }

    private Ubicacion getUbicacionByCoordenadas(String toponimoCoords) {
        String tempUrl = url + toponimoCoords;
        tempUrl += "?geoit=json&auth=" + auth;
        GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
        return geocodingAdapter.doRequest(tempUrl);
    }


}
