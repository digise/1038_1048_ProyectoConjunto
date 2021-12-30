package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;


import org.junit.jupiter.api.Test;

 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
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
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);
        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.getGestorServicios().darAltaUbicacionPorCoordenadas(coordenadas);
        boolean dadoAlta = gestor.getGestorUbicaciones().addUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
    }

    /*@Test
    public void altaUbicacion_coordenadasNoExistentes_anadir(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioGeocoding(new ServicioGeocoding());

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        double latitud = -91;
        double longitud = 165;


        //When
        boolean dadoAlta = gestor.darAltaCoordenadas(latitud, longitud);


        //Then
        int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
        assertEquals(0, nUbicaciones);
        assertFalse(dadoAlta);
    }*/


}
