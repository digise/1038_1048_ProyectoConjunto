package com.example.a1039_1048_proyectoconjunto.aceptacion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Historia2_1_4Test {
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
    public void anadirUbicacionAFavoritos_guardarEstado_valido(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castellon");
        List<Ubicacion> ubicacionesAntesDeAnadirAFavoritos = new ArrayList<>(gestor.getUbicacionesFavoritas());

        //WHEN
        gestor.marcarComoFavorita(castellon, true);
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        List<Ubicacion> ubicacionesDespuesDeAnadirAFavoritosYReiniciarLaAplicacion = gestor.getUbicacionesFavoritas();



        //THEN
        assertTrue(gestor.getUbicacionGuardada("castello").getFavorita());
        assertEquals(ubicacionesAntesDeAnadirAFavoritos.size() + 1, ubicacionesDespuesDeAnadirAFavoritosYReiniciarLaAplicacion.size());
    }

    @Test
    public void anadirUbicacionAFavoritos_guardarEstado_noValido(){
        Ubicacion castellon = gestor.getUbicacionGuardada("castellon");
        List<Ubicacion> ubicacionesAntesDeAnadirAFavoritos = new ArrayList<>(gestor.getUbicacionesFavoritas());

        //WHEN
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        List<Ubicacion> ubicacionesDespuesDeReiniciarLaAplicacionSinAnadirFavorita = gestor.getUbicacionesFavoritas();



        //THEN
        assertFalse(gestor.getUbicacionGuardada("castello").getFavorita());
        assertEquals(ubicacionesAntesDeAnadirAFavoritos.size(), ubicacionesDespuesDeReiniciarLaAplicacionSinAnadirFavorita.size());
    }
}
