package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

public class Historia3Test {

    @Mock private ServicioOpenWeather mockServicioOpenWeather;
    private Gestor  gestor;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioOpenWeather(mockServicioOpenWeather);
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoValido(){
        //Given
        String toponimo = "Castello";
        when(mockServicioOpenWeather.getInformacion(toponimo)).thenReturn(new HashMap<>());


        //When
        boolean valido = gestor.validarToponimo("OPENWEATHER", toponimo);


        //Then
        assertTrue(valido);
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoNoValido(){
        //Given
        String toponimo = "NoExiste";
        when(mockServicioOpenWeather.getInformacion(toponimo)).thenReturn(null);


        //When
        boolean valido = gestor.validarToponimo("OPENWEATHER", toponimo);


        //Then
        assertFalse(valido);
    }
}
