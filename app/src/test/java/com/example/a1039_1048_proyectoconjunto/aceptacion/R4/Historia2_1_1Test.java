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
import java.util.Map;

public class Historia2_1_1Test {
    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");

    }

    @Test
    public void altaUbicacion_toponimoExistente_guardarEstado(){
        //GIVEN
        Ubicacion alicante = gestor.getUbicacionPorToponimo("alicante");
        gestor.darAltaUbicacion(alicante);
        Map<String, Ubicacion> ubicacionesAntesDelCirreAplicacion = gestor.getAllUbicaciones();

        //WHEN
        gestor.borrarGestor();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //THEN
        assertEquals(ubicacionesAntesDelCirreAplicacion.size(), gestor.getAllUbicaciones().size());
        assertTrue(gestor.getUbicacionGuardada("alicant").equals(alicante));
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_noGuardarEstado(){
        //GIVEN
        Ubicacion ubicacionNoExistente = gestor.getUbicacionPorToponimo("noExiste");
        gestor.darAltaUbicacion(ubicacionNoExistente);
        Map<String, Ubicacion> ubicacionesAntesDelCierreAplicacion = gestor.getAllUbicaciones();

        //WHEN
        gestor.borrarGestor();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //THEN
        assertEquals(ubicacionesAntesDelCierreAplicacion.size(), gestor.getAllUbicaciones().size());
        assertNull(gestor.getUbicacionGuardada("noExiste"));
    }
}
