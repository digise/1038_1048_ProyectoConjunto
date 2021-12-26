package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia2Test {
    /*

    @Mock private ServicioGeocoding servicioGeocoding;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

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