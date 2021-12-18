package com.example.a1039_1048_proyectoconjunto.aceptacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Gestor;
import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;


import org.junit.jupiter.api.Test;

public class Historia2Test {

    @Test
    public void altaUbicacion_coordenadasExistentes_anadir(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioGeocoding(new ServicioGeocoding());

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        double latitud = 39.987889;
        double longitud = 0.055778;


        //When
        boolean dadoAlta = gestor.darAltaCoordenadas(latitud, longitud);


        //Then
        int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
        assertEquals(1, nUbicaciones);
        assertTrue(dadoAlta);
    }

    @Test
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
    }
}
