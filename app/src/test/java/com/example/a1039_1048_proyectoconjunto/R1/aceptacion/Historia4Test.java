package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;

public class Historia4Test {

    @Test
    public void validarCoordenadasExistente_APIOpenWeather_muestraInfo(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        double latitud = 39.987889;
        double longitud = 0.055778;
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
        Ubicacion ubicacion = gestorServicios.getUbicacionByCoordenadas(latitud, longitud);
        gestorUbicaciones.addUbicacion(ubicacion);

        //When
        Ubicacion ubicacionCastellon = gestorUbicaciones.getUbicacionByCoordinates(latitud, longitud);
        String info = gestorServicios.getInfoOpenWeather(ubicacionCastellon);

        //Then
        assertFalse(info.isEmpty() || info == null);
    }

    @Test
    public void validarCoordenadasNoExistente_APIOpenWeather_noInfo(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        double latitud = -100;
        double longitud = -100;
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
        Ubicacion ubicacion = gestorServicios.getUbicacionByCoordenadas(latitud, longitud);
        gestorUbicaciones.addUbicacion(ubicacion);

        //When
        Ubicacion ubicacionCastellon = gestorUbicaciones.getUbicacionByCoordinates(latitud, longitud);
        String info = gestorServicios.getInfoOpenWeather(ubicacionCastellon);

        //Then
        assertTrue(info.isEmpty());
    }
}
