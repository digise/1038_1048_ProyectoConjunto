package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia1Test {
    private Gestor gestor;

    @BeforeEach
    public void crear_gestor() {
        Gestor.getInstance().borrarTodaLaInformacionDeLaAplicacion();
        gestor = Gestor.getInstance();

    }

    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        // Given
        String toponimo = "Castello De La Plana";
        Ubicacion ubicacionAuxiliar = new Ubicacion(toponimo, "Spain", "39.97990", "-0.03304");

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
        assertTrue(ubicacionAuxiliar.getToponimo().equalsIgnoreCase(gestor.getUbicacionGuardada(toponimo).getToponimo()));
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anadir() {
        // Given
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
