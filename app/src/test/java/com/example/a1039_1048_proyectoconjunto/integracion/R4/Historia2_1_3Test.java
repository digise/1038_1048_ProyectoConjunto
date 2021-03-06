package com.example.a1039_1048_proyectoconjunto.integracion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.HashMap;

public class Historia2_1_3Test {
    private static CurrentsAdapter mockCurrentsAdapter;
    private static OpenWeatherAdapter mockOpenWeatherAdapter;

    private static ConexionFirebase mockConexionFirebase;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static Gestor gestor;

    @BeforeEach
    public void reiniciarGestor() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        when(mockConexionFirebase.getDocument(anyString(), anyString(), anyObject())).thenReturn("HECHO");
        when(mockConexionFirebase.createDocument(anyString(), anyObject(), anyString())).thenReturn("HECHO");

        when(mockCurrentsAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");
        Ubicacion sagunto = new Ubicacion("sagunto", "spain", "39.69250", "-0.28686");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);
        ubicacionesMentira.put("-MsT0srQqweHs540AArXS", sagunto);

        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        when(mockConexionFirebaseUbicaciones.removeDocument(anyString(), anyString())).thenReturn(true);
        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
    }

    @BeforeAll
    public static void setUp() {
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        mockConexionFirebase = mock(ConexionFirebase.class);
    }

    @Test
    public void borrarUbicacion_actualizaEstado_valido() {
        // Given
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        // When
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        gestor.darBajaUbicacion(castellon);
        // Then

        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseUbicaciones);
        inOrder.verify(mockConexionFirebaseUbicaciones).removeDocument(anyString(), anyString());
    }

    @Test
    public void noBorrarUbicacion_noActualizaEstado_novalido() {
        // Given
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        // When
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");

        // Then

        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseUbicaciones);
        inOrder.verify(mockConexionFirebaseUbicaciones, times(0)).removeDocument(anyString(), anyString());
    }
}
