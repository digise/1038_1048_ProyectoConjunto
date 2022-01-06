package com.example.a1039_1048_proyectoconjunto.integracion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
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

public class Historia2_1_1Test {
    private static GeocodingAdapter mockGeocodingAdapter;
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

        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);

        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
    }

    @BeforeAll
    public static void setUp() {
        mockGeocodingAdapter = mock(GeocodingAdapter.class);
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        mockConexionFirebase = mock(ConexionFirebase.class);
    }

    @Test
    public void altaUbicacion_toponimoExistente_guardarEstado() {
        // Given
        String toponimo = "sagunto";

        Ubicacion ubicacionMock = new Ubicacion(toponimo, "Spain", "39.6833", "-0.2667");

        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(ubicacionMock);

        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(), anyString())).thenReturn("CREADO");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();

        // When
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getAllUbicaciones().size();

        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
        assertEquals(nUbicacionesAntesDeInsertar + 1, 3);
        assertEquals(gestor.getUbicacionGuardada(toponimo), ubicacionMock);

        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseUbicaciones);
        inOrder.verify(mockConexionFirebaseUbicaciones).createDocument(anyString(), anyObject(), anyString());
        inOrder.verify(mockConexionFirebaseUbicaciones).updateDocument(anyString(), anyObject(), anyString());
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anadir() {
        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        // Given
        String toponimo = "noExiste";

        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(null);
        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(), anyString())).thenReturn("NOCREADO");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(false);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When --> Cuando se va a dar de alta ubicacion inexistente devuelve null
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
        assertNull(gestor.getUbicacionGuardada(toponimo));

        InOrder inOrder = Mockito.inOrder(mockConexionFirebaseUbicaciones);
        inOrder.verify(mockConexionFirebaseUbicaciones, times(0)).createDocument(anyString(), anyObject(), anyString());
        inOrder.verify(mockConexionFirebaseUbicaciones, times(0)).updateDocument(anyString(), anyObject(), anyString());
    }
}
