package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Gestor;
import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia4Test {

    private ServicioOpenWeather servicioOpenWeather;

    @BeforeEach
    void setUp(){
        servicioOpenWeather = new ServicioOpenWeather();
    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasValidas(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.addServicio("Open Weather", servicioOpenWeather);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        double latitud = 39.987889;
        double longitud = 0.055778;


        //When
        boolean valido = gestor.coordenadasValidas(servicioOpenWeather, latitud, longitud);


        //Then
        assertTrue(valido);
        assertTrue(servicioOpenWeather.isValid(latitud, longitud));
    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasNoValidas(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.addServicio("Open Weather", servicioOpenWeather);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        double latitud = -100;
        double longitud = -100;


        //When
        boolean valido = gestor.coordenadasValidas(servicioOpenWeather, latitud, longitud);


        //Then
        assertFalse(valido);
        assertFalse(servicioOpenWeather.isValid(latitud, longitud));
    }
}
