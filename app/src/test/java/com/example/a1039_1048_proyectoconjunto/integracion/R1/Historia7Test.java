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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia7Test {

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
    public void obtenerToponimo_coordenadasValidas(){
        //GIVEN
        String latitud = "39.69250";
        String longitud = "-0.28686";
        Ubicacion mockUbicacion = new Ubicacion("sagunto", "Spain", latitud, longitud);
        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(mockUbicacion);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);


        //THEN
        assertEquals(ubicacion.getToponimo().toLowerCase(), "sagunto");
    }

    
    @Test
    public void obtenerToponimo_coordenadasNoValidas(){
        //GIVEN
        String latitud = "25.311261";
        String longitud = "-44.156168";

        when(mockGeocodingAdapter.doRequest(anyString())).thenReturn(null);

        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(mockGeocodingAdapter);


        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);


        //THEN
        assertNull(ubicacion);
    }
}
