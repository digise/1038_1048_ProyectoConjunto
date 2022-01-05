package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia6Test {

    private static GeocodingAdapter mockGeocodingAdapter;
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
    }

    @Test
    public void obtenerCoordenadas_toponimoValido(){
        //GIVEN
        String toponimo = "castellon";
        Ubicacion mockUbicacion = new Ubicacion(toponimo, "Spain", "39.97990", "-0.03304");
        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(mockUbicacion);
        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);

        //THEN
        assertEquals(ubicacion.getLatitud(), "39.97990");
        assertEquals(ubicacion.getLongitud(), "-0.03304");
    }
    @Test
    public void obtenerCoordenadas_toponimoNoValido(){
        //GIVEN
        String toponimo = "noExiste";
        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(null);
        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);

        //THEN
        assertNull(ubicacion);
    }
}
