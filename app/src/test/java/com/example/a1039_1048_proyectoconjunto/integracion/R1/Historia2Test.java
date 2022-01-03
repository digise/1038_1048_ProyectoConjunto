package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia2Test {


    @Mock private ServicioGeocoding mockServicioGeocoding;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void altaUbicacion_coordenadasExistentes_anadir(){
        // Given
        Gestor gestor = Gestor.getInstance();
        String latitud = "40.4619719";
        String longitud = "0.3548686";
        Ubicacion ubicacionMock = new Ubicacion("Calig", "Spain", latitud, longitud);

        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
        when(mockServicioGeocoding.getInformacion(latitud, longitud)).thenReturn(ubicacionMock);
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
        Gestor gestor = Gestor.getInstance();
        String latitud = "-91";
        String longitud = "-100";

        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
        when(mockServicioGeocoding.getInformacion(latitud, longitud)).thenReturn(null);

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