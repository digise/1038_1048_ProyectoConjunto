package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia4Test {

    private static Gestor gestor;
    private static ServicioOpenWeather mockServicioOpenWeather;

    @BeforeAll
    public static void setUp(){
        mockServicioOpenWeather = mock(ServicioOpenWeather.class);
        //para eliminar los spy y los mocks anteriores que hay dentro del gestor
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        GestorServicios gestorServiciosSpy = spy(gestor.getGestorServicios());
        doReturn("MhlOP").when(gestorServiciosSpy).crearServicioFirebase(anyObject());
        gestor.setGestorServicios(gestorServiciosSpy);
        gestor.getGestorServicios().setServicioOpenWeather(mockServicioOpenWeather);
    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasValidas(){
        //Given
        String latitud = "39.987570";
        String longitud = "-0.054678";
        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("sensacionTerminca", "9.509999999999991");
        mapa.put("temperaturaMedia", "11.410000000000025");
        mapa.put("humedad", "-238.0");
        mapa.put("presion", "751.0");
        when(mockServicioOpenWeather.getInformacion(latitud, longitud)).thenReturn(mapa);


        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", latitud, longitud);


        //Then
        //TODO: COMPROBAR QUE LA UBICACIÓN DE ESA LATITUD Y LONGITUD ES LA QUE SE ESPERA
        assertTrue(valido);

    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasNoValidas(){
        //Given
        String latitud = "-100";
        String longitud = "-100";
        when(mockServicioOpenWeather.getInformacion(latitud, longitud)).thenReturn(null);


        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", latitud, longitud);


        //Then
        //TODO: COMPROBAR QUE LA UBICACIÓN DE ESA LATITUD Y LONGITUD ES LA QUE SE ESPERA
        assertFalse(valido);
    }
}
