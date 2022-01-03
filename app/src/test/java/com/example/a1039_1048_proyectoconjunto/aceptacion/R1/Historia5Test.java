package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class Historia5Test {

    private static Gestor gestor;

    @BeforeAll
    public static void crear_gestor() {
        gestor = Gestor.getInstance();

        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("alicante"));
        gestor.activarUbicacion("alicante");
    }

    @Test
    public void activarUbicacion_servicioDisponible_activar() {
        //Given
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());


        //When
        gestor.activarUbicacion("alicante");


        //Then
        Ubicacion alicante = gestor.getUbicacionGuardada("alicante");
        assertTrue(alicante.isActivada());
        assertNotNull(gestor.getUbicacionPorToponimo("alicante"));
        assertNotNull(gestor.getTiempoPorUbicacion(alicante));

        
    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar() {
        //Given
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());

        gestor.desactivarServicio("OPENWEATHER");
        gestor.desactivarServicio("GEOCODING");
        gestor.desactivarServicio("CURRENTS");
        gestor.desactivarUbicacion("alicante");


        //When
        gestor.activarUbicacion("alicante");


        //Then
        Ubicacion alicante = gestor.getUbicacionGuardada("alicante");
        assertTrue(alicante.isActivada());

        assertNull(gestor.getUbicacionPorToponimo("alicante"));
        assertNull(gestor.getTiempoPorUbicacion(alicante));
        assertNull(gestor.getNoticiasPorUbicacion(alicante));
    }
}
