package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.Assert.assertEquals;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;

public class Historia2Test {


    @Test
    public void altaUbicacion_coordenadasExistentes_anyadir(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        double latitud = 39.987889;
        double longitud = 0.055778;
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

        //When
        Ubicacion ubicacion = gestorServicios.getUbicacionByCoordenadas(latitud, longitud);
        gestorUbicaciones.addUbicacion(ubicacion);


        //Then
        assertEquals(1, gestorUbicaciones.getListadoUbicaciones().size());
    }

    @Test
    public void altaUbicacion_coordenadasNoExistentes_anyadir(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        double latitud = -91;
        double longitud = 165;
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

        //When
        Ubicacion ubicacion = gestorServicios.getUbicacionByCoordenadas(latitud, longitud);
        gestorUbicaciones.addUbicacion(ubicacion);


        //Then
        assertEquals(0, gestorUbicaciones.getListadoUbicaciones().size());
    }


}
