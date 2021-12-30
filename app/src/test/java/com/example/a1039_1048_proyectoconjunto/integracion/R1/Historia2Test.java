package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
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
            Coordenadas coordenadas = new Coordenadas(latitud, longitud);

            gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);

            int nUbicacionesAntesDeInsertar = gestor.getAllUbicaciones().size();


            // When
            boolean dadoAlta = gestor.darAltaUbicacionPorCoordenadas(coordenadas);
            int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


            // Then
            assertTrue(dadoAlta);
            assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
        }

/*
    @Test
    public void altaUbicacion_coordenadasExistentes_anyadir(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioGeocoding(servicioGeocoding);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        double latitud = 39.987889;
        double longitud = 0.055778;
        when(servicioGeocoding.getUbicacionByCoordenadas(latitud, longitud)).thenReturn(new Ubicacion(latitud, longitud));


        //When
        boolean dadoAlta = gestor.darAltaCoordenadas(latitud, longitud);


        //Then
        int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
        assertEquals(1, nUbicaciones);
        assertTrue(dadoAlta);
    }

    @Test
    public void altaUbicacion_coordenadasNoExistentes_anyadir(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioGeocoding(servicioGeocoding);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        double latitud = -91;
        double longitud = 165;
        when(servicioGeocoding.getUbicacionByCoordenadas(latitud, longitud)).thenReturn(new Ubicacion(latitud, longitud));


        //When
        boolean dadoAlta = gestor.darAltaCoordenadas(latitud, longitud);


        //Then
        int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
        assertEquals(0, nUbicaciones);
        assertFalse(dadoAlta);
    }
}
*/
}