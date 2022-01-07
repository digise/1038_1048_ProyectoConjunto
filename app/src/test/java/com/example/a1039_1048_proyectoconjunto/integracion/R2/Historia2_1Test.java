package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia2_1Test {
    private static Gestor gestor;

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static CurrentsAdapter mockCurrentsAdapter;

    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static ConexionFirebase mockConexionFirebaseServicios;

    @BeforeEach
    public void setGestor() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
    }

    @BeforeAll
    public static void setUp() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(), anyString())).thenReturn("creado");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);

        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        castellon.activar();
        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));

        mockConexionFirebaseServicios = mock(ConexionFirebase.class);
        when(mockConexionFirebaseServicios.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(false);
        when(mockConexionFirebaseServicios.createDocument(anyString(), anyObject(), eq("HOLO"))).thenReturn("HOLO");

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.servicioActivo(true);
        ServicioCurrents servicioCurrents = new ServicioCurrents();
        servicioCurrents.servicioActivo(true);

        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);

        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);
    }

    @Test
    public void activarAPIs_valido(){
        //GIVEN
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        when(mockCurrentsAdapter.doRequest(anyString())).thenReturn(new HashMap<>());


        //when(mockConexionFirebaseUbicaciones.removeDocument())

        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("openweather", false);
        castellon.activarServicio("currents", false);


        //WHEN
        castellon.activarServicio("openweather", true);

        //THEN
        assertNotNull(castellon);
        assertNotNull(gestor.getTiempoPorUbicacion(castellon));
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
    }

    @Test
    public void activarAPIs_noValido(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("openweather", false);
        castellon.activarServicio("currents", false);

        gestor.desactivarServicio("openweather");
        gestor.desactivarServicio("currents");

        //WHEN
        castellon.activarServicio("openweather", true);

        //THEN
        assertNull(gestor.getTiempoPorUbicacion(castellon));
        assertFalse(gestor.getGestorServicios().getServicioCurrents().isActivo());
        assertFalse(gestor.getGestorServicios().getServicioOpenWeather().isActivo());
    }
}
