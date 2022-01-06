package com.example.a1039_1048_proyectoconjunto.integracion.R4;

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

public class Historia2_2_1Test {

    private static CurrentsAdapter mockCurrentsAdapter;

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

        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        castellon.activar();
        castellon.activarServicio("currents", false);
        valencia.activar();
        valencia.activarServicio("currents", false);

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);


        when(mockConexionFirebase.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);
        servicioCurrents.servicioActivo(false);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);

        mockConexionFirebaseServicios = mock(ConexionFirebase.class);
        when(mockConexionFirebaseServicios.createDocument(anyString(),anyObject(), anyString())).thenReturn("AÑADIDO");
    }

    @BeforeAll
    public static void setUp() {
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        mockConexionFirebase = mock(ConexionFirebase.class);
    }

    @Test
    public void crearAPI_actualizaEstado_valido() {
        // GIVEN -> como es el mismo en las dos historias, está en el beforeEach

        // When
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());

        // THEN
        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseServicios);
        inOrder.verify(mockConexionFirebaseServicios).createDocument(anyString(),anyObject(), anyString());
    }

    @Test
    public void crearAPI_actualizarEstado_novalido() {
        // GIVEN -> como es el mismo en las dos historias, está en el beforeEach

        //WHEN
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);

        // THEN
        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseServicios);
        inOrder.verify(mockConexionFirebaseServicios, times(0)).createDocument(anyString(),anyObject(), anyString());

    }
}
