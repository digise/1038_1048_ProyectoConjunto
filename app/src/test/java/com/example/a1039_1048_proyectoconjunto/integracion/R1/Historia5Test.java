package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia5Test {

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static ConexionFirebase mockConexionFirebaseServicios;
    private static Gestor gestor;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();
    }

    @BeforeAll
    public static void setUp(){
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        mockConexionFirebaseServicios = mock(ConexionFirebase.class);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
        when(mockConexionFirebaseUbicaciones.removeDocument(anyString(), anyString())).thenReturn(true);

        when(mockConexionFirebaseServicios.getCollection(anyString(), anyObject())).thenReturn(null);
        when(mockConexionFirebaseServicios.getDocument(anyString(), anyString(),anyObject())).thenReturn(null);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(true);

    }


    @Test
    public void activarUbicacion_servicioDisponible_activar(){
        //Given
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
        gestor.getGestorServicios().setAllServiciosFromFirebase();

        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(),anyString())).thenReturn("true");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(),anyString())).thenReturn(true);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        gestor.activarUbicacion("sagunto");

        //When
        gestor.activarUbicacion("sagunto");


        //Then
        Ubicacion sagunto = gestor.getUbicacionGuardada("sagunto");
        assertTrue(sagunto.isActivada());
        //assertEquals(gestor.getAllUbicaciones().size(), 3);
    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar(){
        //Given
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
        gestor.getGestorServicios().setAllServiciosFromFirebase();

        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(),anyString())).thenReturn(false);

        gestor.desactivarUbicacion("alicante");

        gestor.desactivarServicio("OPENWEATHER");
        gestor.desactivarServicio("GEOCODING");

        //When
        boolean alicanteActiva = gestor.activarUbicacion("alicante");


        //Then
        Ubicacion alicante = gestor.getUbicacionGuardada("alicante");
        assertNull(alicante);
        assertFalse(alicanteActiva);
        assertNull(gestor.getTiempoPorUbicacion(alicante));
        assertNull(gestor.getNoticiasPorUbicacion(alicante));
    }
}
