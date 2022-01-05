package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia2Test {


    private static GeocodingAdapter mockGeocodingAdapter;
    private static ConexionFirebase mockConexionFirebase;

    private static Gestor gestor;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
    }

    @BeforeAll
    public static void setUp(){
        mockGeocodingAdapter = mock(GeocodingAdapter.class);
        mockConexionFirebase = mock(ConexionFirebase.class);

        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        when(mockConexionFirebase.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
    }

    @Test
    public void altaUbicacion_coordenadasExistentes_anadir(){
        // Given
        String latitud = "40.4619719";
        String longitud = "0.3548686";
        Ubicacion ubicacionMock = new Ubicacion("Calig", "Spain", latitud, longitud);

        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(ubicacionMock);
        when(mockConexionFirebase.createDocument(anyString(), anyObject(), anyString())).thenReturn("CREADO");
        when(mockConexionFirebase.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorUbicaciones().generarUbicaciones();

        int nUbicacionesAntesDeInsertar = gestor.getAllUbicaciones().size();

        // When
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
        assertEquals(gestor.getUbicacionGuardada(ubicacionMock.getToponimo()), ubicacionMock);
    }


    @Test
    public void altaUbicacion_coordenadasNoExistentes_anyadir(){
        //Given
        String latitud = "-91";
        String longitud = "-100";

        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(null);
        when(mockConexionFirebase.createDocument(anyString(), anyObject(), anyString())).thenReturn("NOCREADO");
        when(mockConexionFirebase.updateDocument(anyString(), anyObject(), anyString())).thenReturn(false);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorUbicaciones().generarUbicaciones();

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();

        // When
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
    }
}