package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.Set;

public class Historia5Test {

    private static Gestor gestor;

    // TODO Cambiar la ubicación de ejemplo y insertarla en este mismo test. Sino depende de si se realiza o no la historia 1.
    @BeforeAll
    public static void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());

        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("sagunto"));
        gestor.activarUbicacion("sagunto");
    }

    @Test
    public void activarUbicacion_servicioDisponible_activar(){
        //Given
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());


        //When
        gestor.activarUbicacion("sagunto");


        //Then
        Ubicacion castellon = gestor.getUbicacionGuardada("sagunto");
        assertTrue(castellon.isActivada());
    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar(){
        //Given
        gestor.desactivarServicio("OPENWEATHER");
        gestor.desactivarServicio("GEOCODING");

        //When
        gestor.activarUbicacion("sagunto");


        //Then
        Ubicacion castellon = gestor.getUbicacionGuardada("sagunto");
        assertTrue(castellon.isActivada());
    }
}
