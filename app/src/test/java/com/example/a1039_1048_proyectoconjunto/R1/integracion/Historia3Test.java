package com.example.a1039_1048_proyectoconjunto.R1.integracion;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Gestor;
import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia3Test {

    @Mock private ServicioOpenWeather servicioOpenWeather;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoValido(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.addServicio("Open Weather", servicioOpenWeather);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";
        when(servicioOpenWeather.isValid(toponimo)).thenReturn(true);


        //When
        boolean valido = gestor.toponimoValido(servicioOpenWeather, toponimo);


        //Then
        verify(servicioOpenWeather, times(1)).isValid(toponimo);
        assertTrue(valido);
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoNoValido(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.addServicio("Open Weather", servicioOpenWeather);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "NoExiste";
        when(servicioOpenWeather.isValid(toponimo)).thenReturn(false);

        //When
        boolean valido = gestor.toponimoValido(servicioOpenWeather, toponimo);


        //Then
        verify(servicioOpenWeather, times(1)).isValid(toponimo);
        assertFalse(valido);
    }
}
