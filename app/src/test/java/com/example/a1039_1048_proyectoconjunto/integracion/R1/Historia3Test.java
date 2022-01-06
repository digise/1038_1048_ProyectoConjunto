package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

public class Historia3Test {

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static ConexionFirebase mockConexionFirebase;
    private static Gestor gestor;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
    }

    @BeforeAll
    public static void setUp(){
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        mockConexionFirebase = mock(ConexionFirebase.class);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        when(mockConexionFirebase.createDocument(anyString(), anyObject(),anyString())).thenReturn("true");
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoValido(){
        //Given
        String toponimo = "Castello";
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        //When
        boolean valido = gestor.validarToponimo("OPENWEATHER", toponimo);


        //Then
        assertTrue(valido);
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoNoValido(){
        //Given
        String toponimo = "NoExiste";
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(null);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);


        //When
        boolean valido = gestor.validarToponimo("OPENWEATHER", toponimo);


        //Then
        assertFalse(valido);
    }
}
