package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia4Test {

    @Mock private ServicioOpenWeather servicioOpenWeather;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
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
        when(servicioOpenWeather.isValid(latitud, longitud)).thenReturn(true);


        //When
        boolean valido = gestor.coordenadasValidas(servicioOpenWeather, latitud, longitud);


        //Then
        verify(servicioOpenWeather, times(1)).isValid(latitud, longitud);
        assertTrue(valido);
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
        when(servicioOpenWeather.isValid(latitud, longitud)).thenReturn(false);


        //When
        boolean valido = gestor.coordenadasValidas(servicioOpenWeather, latitud, longitud);


        //Then
        verify(servicioOpenWeather, times(1)).isValid(latitud, longitud);
        assertFalse(valido);
    }
}
