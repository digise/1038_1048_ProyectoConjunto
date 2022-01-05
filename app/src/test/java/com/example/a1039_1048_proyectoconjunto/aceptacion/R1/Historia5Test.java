package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Historia5Test {

    private Gestor gestor;

    @BeforeEach
    public void crear_gestor() {
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
    }

    @Test
    public void ubicacionDisponible_activar() {
        //Given
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("sagunto"));
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        Ubicacion sagunto = gestor.getUbicacionGuardada("sagunto");


        //When
        gestor.activarUbicacion("sagunto");
        sagunto.activarServicio("openweather", true);


        //Then
        assertTrue(sagunto.isActivada());
        assertNotNull(gestor.getUbicacionPorToponimo("sagunto"));
        assertNotNull(gestor.getTiempoPorUbicacion(sagunto));

        
    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar() {
        //Given
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo("sagunto");
        gestor.darAltaUbicacion(ubicacion);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());

        gestor.desactivarServicio("OPENWEATHER");
        gestor.desactivarUbicacion("sagunto");


        //When
        gestor.activarUbicacion("sagunto");
        Ubicacion sagunto = gestor.getUbicacionGuardada("sagunto");

        //Then
        assertTrue(sagunto.isActivada());
        assertNull(gestor.getTiempoPorUbicacion(sagunto));
    }
}
