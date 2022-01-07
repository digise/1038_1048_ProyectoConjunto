package com.example.a1039_1048_proyectoconjunto.integracion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia2Test {
    private static Gestor gestor;
    private static ConexionFirebase mockConexionFirebaseServicios;

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static CurrentsAdapter mockCurrentsAdapter;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
    }

    @BeforeAll
    public static void setUp(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        mockConexionFirebaseServicios = mock(ConexionFirebase.class);
        when(mockConexionFirebaseServicios.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(false);
        when(mockConexionFirebaseServicios.createDocument(anyString(), anyObject(), anyString())).thenReturn("HOLO");
    }

    @Test
    public void activarServiciosAPI_listadoConServicios_valido(){
        //GIVEN
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);

        when(mockCurrentsAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        servicioCurrents.servicioActivo(false);
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.servicioActivo(true);
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);


        Map<String, Servicio> servicios = gestor.getAllServicios();
        ServicioOpenWeather servicioOpenWeatherGestor = (ServicioOpenWeather) servicios.get("openweather");
        ServicioCurrents servicioCurrentsGestor = (ServicioCurrents) servicios.get("currents");

        //WHEN
        servicioOpenWeatherGestor.servicioActivo(true);

        //THEN
        assertNotNull(servicioOpenWeatherGestor.getInformacion("sagunto"));
        assertNull(servicioCurrentsGestor.getInformacion("sagunto"));
    }
    @Test
    public void activarServiciosAPI_listadoSinServicios_valido(){
        //GIVEN


        //WHEN
        Map<String, Servicio> servicios = gestor.getAllServicios();

        //THEN
        assertEquals(0, servicios.size());
    }
}
