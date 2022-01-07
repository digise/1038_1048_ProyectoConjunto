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

import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class Historia1Test {

    private static GeocodingAdapter mockGeocodingAdapter;
    private static ConexionFirebase mockConexionFirebase;
    private static Gestor gestor;

    @BeforeEach
    public void reiniciarGestor() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
    }

    @BeforeAll
    public static void setUp() {
        mockGeocodingAdapter = mock(GeocodingAdapter.class);
        mockConexionFirebase = mock(ConexionFirebase.class);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        Ubicacion sagunto = new Ubicacion("sagunto", "Spain", "39.6833", "-0.2667");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", sagunto);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);

        when(mockConexionFirebase.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
    }

    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        // Given
        String toponimo = "castello";
        Ubicacion castellon = new Ubicacion(toponimo, "spain", "40.67830", "0.28421");


        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(castellon);
        when(mockConexionFirebase.createDocument(anyString(), anyObject(), anyString())).thenReturn("CREADO");
        when(mockConexionFirebase.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebase);
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
        assertEquals(gestor.getUbicacionGuardada(toponimo), castellon);
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anadir() {
        // Given
        String toponimo = "noExiste";

        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(null);
        when(mockConexionFirebase.createDocument(anyString(), anyObject(), anyString())).thenReturn("NOCREADO");
        when(mockConexionFirebase.updateDocument(anyString(), anyObject(), anyString())).thenReturn(false);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebase);
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
    }
}
