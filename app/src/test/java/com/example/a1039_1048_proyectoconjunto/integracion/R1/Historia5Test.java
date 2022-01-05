package com.example.a1039_1048_proyectoconjunto.integracion.R1;

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
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class Historia5Test {

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static GeocodingAdapter mockGeocodingAdapter;
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
        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);

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

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
    }


    @Test
    public void activarUbicacion_servicioDisponible_activar(){
        //Given
        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(),anyString())).thenReturn("true");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(),anyString())).thenReturn(true);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("sagunto"));
        gestor.activarUbicacion("sagunto");

        //When
        gestor.activarUbicacion("sagunto");


        //Then
        Ubicacion sagunto = gestor.getUbicacionGuardada("sagunto");
        assertTrue(sagunto.isActivada());
    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar(){
        //Given
        gestor.desactivarUbicacion("alicante");

        gestor.desactivarServicio("OPENWEATHER");
        gestor.desactivarServicio("GEOCODING");

        //When
        boolean alicanteActiva = gestor.activarUbicacion("alicante");


        //Then
        Ubicacion alicante = gestor.getUbicacionGuardada("alicante");
        assertNull(alicante);
        assertFalse(alicanteActiva);
    }
}
