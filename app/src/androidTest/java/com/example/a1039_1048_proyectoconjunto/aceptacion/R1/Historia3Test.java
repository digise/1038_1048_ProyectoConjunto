package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia3Test {

    private ServicioOpenWeather servicioOpenWeather;

    @BeforeEach
    void setUp(){
        servicioOpenWeather = new ServicioOpenWeather();
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoValido(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.addServicio("Open Weather", servicioOpenWeather);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";


        //When
        boolean valido = gestor.toponimoValido(servicioOpenWeather, toponimo);


        //Then
        assertTrue(valido);
        assertTrue(servicioOpenWeather.isValid(toponimo));
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoNoValido(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.addServicio("Open Weather", servicioOpenWeather);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "NoExiste";


        //When
        boolean valido = gestor.toponimoValido(servicioOpenWeather, toponimo);


        //Then
        assertFalse(valido);
        assertFalse(servicioOpenWeather.isValid(toponimo));
    }
}
