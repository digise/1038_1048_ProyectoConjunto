package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.google.gson.Gson;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class Historia1Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crear_gestor() {
        gestor = Gestor.getInstance();

    }

    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        // Given
        String toponimo = "alicante";
        Ubicacion ubicacionMock = new Ubicacion(toponimo, "Spain", "39.69250", "-0.28686");

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
        assertTrue(ubicacionMock.getToponimo().equalsIgnoreCase(gestor.getUbicacionGuardada(toponimo).getToponimo()));
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anadir() {
        // Given
        Gestor gestor = Gestor.getInstance();
        String toponimo = "noExiste";

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When --> Cuando se va a dar de alta ubicacion inexistente devuelve null
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
    }
}
