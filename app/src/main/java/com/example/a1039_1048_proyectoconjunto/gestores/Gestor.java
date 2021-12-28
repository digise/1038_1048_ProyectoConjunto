package com.example.a1039_1048_proyectoconjunto.gestores;

// ESTA CLASE ESTÁ A UN NIVEL MAS ALTO QUE LOS GESTORES DE SERVICIO Y UBICACIONES, Y ES LA QUE
// APLICA LA LÓGICA.

import android.content.Context;

import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

public class Gestor {

    //Singleton
    private static Gestor INSTANCE;

    private GestorUbicaciones gestorUbicaciones;
    private GestorServicios gestorServicios;

    public Gestor() {
        this.gestorUbicaciones = new GestorUbicaciones();
        this.gestorServicios = new GestorServicios();
    }

    public synchronized static Gestor getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Gestor();
        }
        return INSTANCE;
    }

    public GestorUbicaciones getGestorUbicaciones() {
        return gestorUbicaciones;
    }

    public GestorServicios getGestorServicios() {
        return gestorServicios;
    }

    /*public Ubicacion darAltaToponimo(String toponimo) {
        return gestorServicios.darAltaToponimo(toponimo);
    }*/

    /*public boolean darAltaCoordenadas(double latitud, double longitud) {
        return false;
    }

    public boolean toponimoValido(Servicio servicio, String toponimo) {
        return true;
    }

    public boolean coordenadasValidas(Servicio servicio, double latitud, double longitud) {
        return true;
    }

    public Ubicacion getUbicacion(String toponimo) {
        return null;
    }*/

}
