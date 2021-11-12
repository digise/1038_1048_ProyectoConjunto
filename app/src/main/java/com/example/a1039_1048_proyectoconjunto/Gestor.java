package com.example.a1039_1048_proyectoconjunto;

public class Gestor {

    private GestorUbicaciones gestorUbicaciones;
    private GestorServicios gestorServicios;

    public Gestor(GestorUbicaciones gestorUbicaciones, GestorServicios gestorServicios) {
        this.gestorUbicaciones = gestorUbicaciones;
        this.gestorServicios = gestorServicios;
    }

    public boolean darAltaToponimo(String toponimo){
        return false;
    }

    public boolean darAltaCoordenadas(double latitud, double longitud) {
        return false;
    }

    public boolean toponimoValido(Servicio servicio, String toponimo){
        return true;
    }

    public boolean coordenadasValidas(Servicio servicio, double latitud, double longitud){
        return true;
    }
}
