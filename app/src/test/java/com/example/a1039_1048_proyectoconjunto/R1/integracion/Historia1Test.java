package com.example.a1039_1048_proyectoconjunto.R1.integracion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;
import org.mockito.Mockito;

public class Historia1Test {


    @Test
    public void altaUbicacion_toponimoExistente_anyadir(){
        //Given
        ServicioGeocoding mockServicioGeocoding = Mockito.mock(ServicioGeocoding.class);
        GestorServicios gestorServicios = new GestorServicios(mockServicioGeocoding);
        String toponimo = "Castelló";
        Ubicacion ubicacionMock = new Ubicacion(toponimo);
        when(mockServicioGeocoding.getUbicacionPorToponimo(toponimo)).thenReturn(ubicacionMock);
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

        //When
        ServicioGeocoding servicioGeocoding = gestorServicios.getServicioGeocoding();
        Ubicacion ubicacionReal = servicioGeocoding.getUbicacionPorToponimo(toponimo);
        gestorUbicaciones.addUbicacion(ubicacionReal);


        //Then
        assertEquals(1, gestorUbicaciones.getListadoUbicaciones().size());
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anyadir(){
        //Given
        ServicioGeocoding mockServicioGeocoding = Mockito.mock(ServicioGeocoding.class);
        GestorServicios gestorServicios = new GestorServicios(mockServicioGeocoding);
        String toponimo = "bhjgj";
        when(mockServicioGeocoding.getUbicacionPorToponimo(toponimo)).thenReturn(null);
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

        //When
        ServicioGeocoding servicioGeocoding = gestorServicios.getServicioGeocoding();
        Ubicacion ubicacionReal = servicioGeocoding.getUbicacionPorToponimo(toponimo);
        gestorUbicaciones.addUbicacion(ubicacionReal);


        //Then
        assertEquals(0, gestorUbicaciones.getListadoUbicaciones().size());
    }


}
