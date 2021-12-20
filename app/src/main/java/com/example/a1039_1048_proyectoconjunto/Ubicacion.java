package com.example.a1039_1048_proyectoconjunto;

public class Ubicacion {

    private String toponimo;
    private Double latitud; //-90, 90
    private Double longitud; //-180, 180
    private boolean activada = true;

    public Ubicacion(String toponimo) {
        this.toponimo = toponimo;
        this.latitud = null;
        this.longitud = null;
    }

    public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.toponimo = null;
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

    public boolean activar(){
        return true;
    }

    public boolean isActivada(){
        return this.activada;
    }

    public boolean issetCoord(){
        return this.latitud == null && this.longitud == null;
    }

    public boolean issetToponimo(){
        return this.toponimo == null;
    }
}
