package com.example.a1039_1048_proyectoconjunto.R1.integracion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;
import org.mockito.Mockito;

public class Historia2Test {


    @Test
    public void altaUbicacion_coordenadasExistentes_anyadir(){
        //Given
        ServicioGeocoding mockServicioGeocoding = Mockito.mock(ServicioGeocoding.class);
        GestorServicios gestorServicios = new GestorServicios(mockServicioGeocoding);
        double latitud = 39.987889;
        double longitud = 0.055778;
        Ubicacion ubicacionMock = new Ubicacion(latitud, longitud);
        when(mockServicioGeocoding.getUbicacionPorCoordenadas(latitud, longitud)).thenReturn(ubicacionMock);
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

        //When
        ServicioGeocoding servicioGeocoding = gestorServicios.getServicioGeocoding();
        Ubicacion ubicacionReal = servicioGeocoding.getUbicacionPorCoordenadas(latitud, longitud);
        gestorUbicaciones.addUbicacion(ubicacionReal);


        //Then
        assertEquals(1, gestorUbicaciones.getListadoUbicaciones().size());
    }

    @Test
    public void altaUbicacion_coordenadasNoExistentes_anyadir(){
        //Given
        ServicioGeocoding mockServicioGeocoding = Mockito.mock(ServicioGeocoding.class);
        GestorServicios gestorServicios = new GestorServicios(mockServicioGeocoding);
        double latitud = -91;
        double longitud = 165;
        when(mockServicioGeocoding.getUbicacionPorCoordenadas(latitud, longitud)).thenReturn(null);
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

        //When
        ServicioGeocoding servicioGeocoding = gestorServicios.getServicioGeocoding();
        Ubicacion ubicacionReal = servicioGeocoding.getUbicacionPorCoordenadas(latitud, longitud);
        gestorUbicaciones.addUbicacion(ubicacionReal);


        //Then
        assertEquals(0, gestorUbicaciones.getListadoUbicaciones().size());
    }


}
