package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;
import java.util.Set;

public class Historia5Test {

    // TODO Revisar

    private static Map<String, Ubicacion> ubicaciones;
    private static Gestor gestor;
    private ServicioOpenWeather mockServicioOpenWeather;

    @BeforeAll
    public static void crear_firebase(){
        ubicaciones = ConexionFirebase.getCollection("ubicaciones", Ubicacion.class);
    }

    // TODO Igual hay que cambiar la ubicación de ejemplo y insertarla en este mismo test. Sino depende de si se realiza o no la historia 1.
    @BeforeAll
    public static void crear_gestor(){
        gestor = Gestor.getInstance();
        ServicioGeocoding mockServicioGeocoding = Mockito.mock(ServicioGeocoding.class);
        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
        when(mockServicioGeocoding.getInformacion("sagunto"))
                .thenReturn(new Ubicacion("Sagunto", "Spain", "39.6833", "-0.2667"));
    }


    @Test
    public void activarUbicacion_servicioDisponible_activar(){
        //Given
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("sagunto"));
        gestor.activarUbicacion("sagunto");

        //When
        gestor.activarUbicacion("sagunto");


        //Then
        Ubicacion castellon = gestor.getUbicacionGuardada("sagunto");
        assertTrue(castellon.isActivada());
    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar(){
        //Given
        gestor.desactivarUbicacion("alicante");

        gestor.desactivarServicio("OPENWEATHER");
        gestor.desactivarServicio("GEOCODING");

        //When
        boolean alicanteActiva = gestor.activarUbicacion("alicante");


        //Then
        Ubicacion alicante = gestor.getUbicacionGuardada("alicante");
        assertNull(alicante);
        assertFalse(alicanteActiva);
    }
}
