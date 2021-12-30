package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.Test;

public class Historia1Test {

    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        // Given
        Gestor gestor = Gestor.getInstance();
        String toponimo = "sagunto";
        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);


        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


        // When

        boolean dadoAlta = gestor.darAltaUbicacionPorToponimo(toponimo);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anadir() {
        // Given
        Gestor gestor = Gestor.getInstance();
        String toponimo = "noExiste";
        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


        // When --> Cuando se va a dar de alta ubicacion inexistente devuelve null
        boolean dadoAlta = gestor.darAltaUbicacionPorToponimo(toponimo);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
    }
}
