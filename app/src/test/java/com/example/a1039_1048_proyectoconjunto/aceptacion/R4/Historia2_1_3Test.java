package com.example.a1039_1048_proyectoconjunto.aceptacion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import kotlin.jvm.internal.MagicApiIntrinsics;

public class Historia2_1_3Test {

    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        Ubicacion sagunto = gestor.getUbicacionPorToponimo("sagunto");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.darAltaUbicacion(sagunto);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");
        gestor.activarUbicacion("sagunto");
    }

    @Test
    public void eliminarUbicacion_guardarEstado(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castellon");
        Map<String, Ubicacion> ubicacionesAntesDeBorrar = new HashMap<>(gestor.getAllUbicaciones());

        //WHEN
        gestor.darBajaUbicacion(castellon);
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, Ubicacion> ubicacionesDespuesDeBorrarYReiniciarLaAplicacion = gestor.getAllUbicaciones();



        //THEN
        assertNull(gestor.getUbicacionGuardada("castello"));
        assertNotNull(gestor.getUbicacionGuardada("valencia"));
        assertNotNull(gestor.getUbicacionGuardada("sagunto"));
        assertEquals(ubicacionesAntesDeBorrar.size() - 1, ubicacionesDespuesDeBorrarYReiniciarLaAplicacion.size());
    }

    @Test
    public void noEliminarUbicacion_guardarEstado(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castellon");
        Map<String, Ubicacion> ubicacionesAntesDeBorrar = new HashMap<>(gestor.getAllUbicaciones());

        //WHEN
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, Ubicacion> ubicacionesDespuesDeReiniciarLaAplicacion = gestor.getAllUbicaciones();



        //THEN
        assertNotNull(gestor.getUbicacionGuardada("castello"));
        assertNotNull(gestor.getUbicacionGuardada("valencia"));
        assertNotNull(gestor.getUbicacionGuardada("sagunto"));
        assertEquals(ubicacionesAntesDeBorrar.size(), ubicacionesDespuesDeReiniciarLaAplicacion.size());
    }
}
