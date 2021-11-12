package com.example.a1039_1048_proyectoconjunto;

public class Ubicacion {

    private String toponimo;
    private double latitud; //-90, 90
    private double longitud; //-180, 180

    public Ubicacion(String toponimo) {
        this.toponimo = toponimo;
    }

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getToponimo() {
        return null;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
