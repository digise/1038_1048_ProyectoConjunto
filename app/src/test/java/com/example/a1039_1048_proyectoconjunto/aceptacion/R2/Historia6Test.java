package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class Historia6Test {

    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
    }

    @Test
    public void anadirFavoritos_valido(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        Ubicacion alicante = gestor.getUbicacionPorToponimo("alicante");
        Ubicacion sagunto = gestor.getUbicacionPorToponimo("sagunto");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.darAltaUbicacion(alicante);
        gestor.darAltaUbicacion(sagunto);
        alicante.desactivar();
        sagunto.desactivar();
        castello.activar();
        valencia.activar();

        //WHEN
        boolean marcada = gestor.marcarComoFavorita(valencia, true);
        List<Ubicacion> favs = gestor.getUbicacionesFavoritas();

        //THEN
        assertTrue(marcada);
        assertEquals(1, favs.size());
    }

    @Test
    public void anadirFavoritos_noValido() {
        //GIVEN
        Map<String, Ubicacion> todasLasUbicaciones = gestor.getAllUbicaciones();

        //WHEN
        List<Ubicacion> favs = gestor.getUbicacionesFavoritas();

        //THEN
        assertEquals(todasLasUbicaciones.size(), 0);
        assertEquals(favs.size(),0);
        assertEquals(todasLasUbicaciones.size(), favs.size());

    }
}
