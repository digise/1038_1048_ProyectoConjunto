package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia4Test {

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
    public void validarCoordenadas_APIOpenWeather_coordenadasValidas(){
        //Given
        String latitud = "39.987570";
        String longitud = "-0.054678";
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("sensacionTerminca", "9.509999999999991");
        mapa.put("temperaturaMedia", "11.410000000000025");
        mapa.put("humedad", "-238.0");
        mapa.put("presion", "751.0");

        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(mapa);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", latitud, longitud);


        //Then
        assertTrue(valido);

    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasNoValidas(){
        //Given
        String latitud = "-100";
        String longitud = "-100";
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(null);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", latitud, longitud);


        //Then
        assertFalse(valido);
    }
}
