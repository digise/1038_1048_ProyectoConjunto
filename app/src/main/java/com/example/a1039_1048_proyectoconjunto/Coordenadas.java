package com.example.a1039_1048_proyectoconjunto;

public class Coordenadas {
    String longitud;
    String latitud;

    public Coordenadas(String latitud, String longitud) {
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    @Override
    public String toString() {
        return latitud + "," + longitud;
    }
}
