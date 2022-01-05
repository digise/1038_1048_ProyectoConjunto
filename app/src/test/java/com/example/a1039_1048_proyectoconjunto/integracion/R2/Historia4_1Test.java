package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia4_1Test {

    private static Gestor gestor;
    private static OpenWeatherAdapter mockOpenWeatherAdapter;

    private static ConexionFirebase mockConexionFirebaseServicios;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;

    private static Ubicacion castellon;

    @BeforeEach
    public void setGestor() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
    }

    @BeforeAll
    public static void setUp() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);

        mockConexionFirebaseServicios = mock(ConexionFirebase.class);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(false);
        when(mockConexionFirebaseServicios.createDocument(anyString(), anyObject(), anyString())).thenReturn("HOLA");
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);

        castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        castellon.activar();
    }

    @Test
    public void informacionOpenweather_valido() {
        //GIVEN
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        castellon.activarServicio("openweather", true);

        //WHEN
        HashMap<String, String> tiempoCastellon = gestor.getTiempoPorUbicacion(castellon);

        //THEN
        assertNotNull(tiempoCastellon);
    }

    @Test
    public void informacionOpenweather_noValido() {
        //GIVEN
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        castellon.activarServicio("openweather", false);

        //WHEN
        HashMap<String, String> tiempoCastellon = gestor.getTiempoPorUbicacion(castellon);

        //THEN
        assertNull(tiempoCastellon);
    }

}
