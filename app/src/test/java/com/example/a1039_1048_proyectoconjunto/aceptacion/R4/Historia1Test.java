package com.example.a1039_1048_proyectoconjunto.aceptacion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia1Test {
    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        Ubicacion alicante = gestor.getUbicacionPorToponimo("alicante");
        Ubicacion sagunto = gestor.getUbicacionPorToponimo("sagunto");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.darAltaUbicacion(alicante);
        gestor.darAltaUbicacion(sagunto);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");
        gestor.desactivarServicio("currents");
    }

    @Test
    public void recuperarEstadoAnterior_valido(){
        //GIVEN -> cargar estado (no habrá nada)
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        HashMap<String, Ubicacion> ubicacionesAlCerrarLaAplicacion = new HashMap<>(gestor.getAllUbicaciones());
        HashMap<String, Servicio> serviciosAlCerrarLaAplicacion = new HashMap<>(gestor.getAllServicios());


        //WHEN
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //THEN
        assertEquals(ubicacionesAlCerrarLaAplicacion.size(), 0);
        assertEquals(serviciosAlCerrarLaAplicacion.size(), 0);
        assertEquals(gestor.getAllServicios().size(), 2);
        assertEquals(gestor.getAllUbicaciones().size(), 4);
        assertEquals(gestor.getUbicacionesActivas().size(), 2);
        assertFalse(gestor.getAllServicios().get("currents").isActivo());
        assertTrue(gestor.getAllServicios().get("openweather").isActivo());
        assertTrue(gestor.getUbicacionGuardada("castello").isActivada());
        assertTrue(gestor.getUbicacionGuardada("valencia").isActivada());
        assertFalse(gestor.getUbicacionGuardada("alicante").isActivada());
        assertFalse(gestor.getUbicacionGuardada("sagunto").isActivada());
    }

    @Test
    public void noRecuperarEstadoAnterior_valido(){
        //GIVEN -> cargar estado (no habrá nada)
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        HashMap<String, Ubicacion> ubicacionesAlCerrarLaAplicacion = new HashMap<>(gestor.getAllUbicaciones());
        HashMap<String, Servicio> serviciosAlCerrarLaAplicacion = new HashMap<>(gestor.getAllServicios());

        //WHEN -> vacío por que no carga nada

        //THEN
        assertEquals(ubicacionesAlCerrarLaAplicacion.size(), 0);
        assertEquals(serviciosAlCerrarLaAplicacion.size(), 0);
        assertEquals(gestor.getAllServicios().size(), 0);
        assertEquals(gestor.getAllUbicaciones().size(), 0);
        assertEquals(gestor.getUbicacionesActivas().size(), 0);
        assertNull(gestor.getAllServicios().get("currents"));
        assertNull(gestor.getAllServicios().get("openweather"));
        assertNull(gestor.getUbicacionGuardada("castello"));
        assertNull(gestor.getUbicacionGuardada("valencia"));
        assertNull(gestor.getUbicacionGuardada("alicante"));
        assertNull(gestor.getUbicacionGuardada("sagunto"));
    }
}
