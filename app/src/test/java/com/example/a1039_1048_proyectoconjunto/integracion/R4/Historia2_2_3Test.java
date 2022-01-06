package com.example.a1039_1048_proyectoconjunto.integracion.R4;

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

public class Historia2_2_3Test {

    private static ConexionFirebase mockConexionFirebase;
    private static ConexionFirebase mockConexionFirebaseServicios;
    private static Gestor gestor;

    @BeforeEach
    public void reiniciarGestor() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        mockConexionFirebase = mock(ConexionFirebase.class);
        when(mockConexionFirebase.createDocument(anyString(), anyString(), anyObject())).thenReturn("HOLA");
        when(mockConexionFirebase.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);
        servicioCurrents.servicioActivo(false);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.servicioActivo(true);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        castellon.activar();
        castellon.activarServicio("currents", true);
        castellon.activarServicio("openweather", true);
        valencia.activar();
        valencia.activarServicio("currents", true);
        valencia.activarServicio("openweather", true);

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);


        when(mockConexionFirebase.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));

        mockConexionFirebaseServicios = mock(ConexionFirebase.class);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(true);
    }

    @BeforeAll
    public static void setUp() {
        mockConexionFirebase = mock(ConexionFirebase.class);
    }

    @Test
    public void borrarAPI_actualizaEstado_valido() {
        // GIVEN -> como es el mismo en las dos historias, está en el beforeEach

        // WHEN
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
        gestor.borrarServicio("openweather");

        // THEN
        assertFalse(gestor.getAllServicios().containsKey("openweather"));
        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseServicios);
        inOrder.verify(mockConexionFirebaseServicios).removeDocument(anyString(), anyString());
    }

    @Test
    public void borrarAPI_actualizarEstado_novalido() {
        // GIVEN -> como es el mismo en las dos historias, está en el beforeEach

        // WHEN
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);

        // THEN
        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseServicios);
        inOrder.verify(mockConexionFirebaseServicios, times(0)).removeDocument(anyString(), anyString());
    }
}
