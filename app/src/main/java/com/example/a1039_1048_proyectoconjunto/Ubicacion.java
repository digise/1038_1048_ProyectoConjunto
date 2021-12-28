package com.example.a1039_1048_proyectoconjunto;

public class Ubicacion {

    private String toponimo;
    private String latitud; //-90, 90
    private String longitud; //-180, 180
    private boolean activada = true;
    private String pais;

    public Ubicacion(){
        this.toponimo = null;
        this.latitud = null;
        this.longitud = null;
        this.pais = null;
    }

    public Ubicacion(String toponimo) {
        this.toponimo = toponimo;
        this.latitud = null;
        this.longitud = null;
        this.pais = null;
    }

    public Ubicacion(String latitud, String longitud){
        this.toponimo = null;
        this.pais = null;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Ubicacion(String toponimo, String pais, String latitud, String longitud) {
        this.toponimo = toponimo;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
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
}
