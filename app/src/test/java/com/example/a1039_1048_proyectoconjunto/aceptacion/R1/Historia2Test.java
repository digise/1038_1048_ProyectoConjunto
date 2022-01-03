package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.Test;

public class Historia2Test {

    @Test
    public void altaUbicacion_coordenadasExistentes_anadir(){
        // Given
        Gestor gestor = Gestor.getInstance();
        String latitud = "40.4619719";
        String longitud = "0.3548686";

        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
    }

    @Test
    public void altaUbicacion_coordenadasNoExistentes_anadir(){
        // Given
        Gestor gestor = Gestor.getInstance();
        String latitud = "-91";
        String longitud = "100";
        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When --> Cuando se va a dar de alta ubicacion inexistente devuelve null
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
    }

}
