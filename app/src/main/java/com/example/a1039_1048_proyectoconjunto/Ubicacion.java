package com.example.a1039_1048_proyectoconjunto;

import java.util.Comparator;

public class Ubicacion {

    private String toponimo;
    private String latitud; //-90, 90
    private String longitud; //-180, 180
    private boolean activada = false;
    private String pais;
    private String alias;
    private boolean servicioGeocodingActivo;
    private boolean servicioOpenWeatherActivo;
    private boolean servicioCurrentsActivo;

    public Ubicacion(){
        this.toponimo = null;
        this.latitud = null;
        this.longitud = null;
        this.pais = null;
        this.alias = "";
        this.servicioGeocodingActivo = false;
        this.servicioOpenWeatherActivo = false;
        this.servicioCurrentsActivo = false;
    }

    public Ubicacion(String toponimo) {
        this.toponimo = toponimo;
        this.latitud = null;
        this.longitud = null;
        this.pais = null;
        this.alias = "";
    }

    public Ubicacion(String latitud, String longitud){
        this.toponimo = null;
        this.pais = null;
        this.latitud = latitud;
        this.longitud = longitud;
        this.alias = "";
    }

    public Ubicacion(String toponimo, String pais, String latitud, String longitud) {
        this.toponimo = toponimo;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
        this.alias = "";
    }

    public String getToponimo() {
        return this.toponimo;
    }

    public void setToponimo(String toponimo){
        this.toponimo = toponimo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public boolean activar(){
        activada = true;
        return true;
    }

    public boolean desactivar(){
        activada = false;
        return true;
    }

    public boolean isActivada(){
        return activada;
    }

    public boolean issetCoord(){
        return this.latitud == null && this.longitud == null;
    }

    public boolean issetToponimo(){
        return this.toponimo == null;
    }

    public String getAlias() {
        return alias;
    }

    public boolean setAlias(String alias) {
        if (alias.length() > 0 && alias.length() <= 20) {
            this.alias = alias;
            return true;
        }
        return false;
    }



    @Override
    public String toString() {
        return "Ubicacion{" +
                "toponimo='" + toponimo + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", activada=" + activada +
                ", pais='" + pais + '\'' +
                '}';
    }

    public boolean equals(Ubicacion ubicacion) {
        return ubicacion.longitud.equals(this.longitud) && ubicacion.latitud.equals(this.latitud);
    }

}
