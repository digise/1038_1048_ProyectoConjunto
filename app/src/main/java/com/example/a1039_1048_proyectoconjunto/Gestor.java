package com.example.a1039_1048_proyectoconjunto;

// ESTA CLASE ESTÁ A UN NIVEL MAS ALTO QUE LOS GESTORES DE SERVICIO Y UBICACIONES, Y ES LA QUE
// APLICA LA LÓGICA.

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

    public Ubicacion getUbicacion(String toponimo){
        return null;
    }
}
