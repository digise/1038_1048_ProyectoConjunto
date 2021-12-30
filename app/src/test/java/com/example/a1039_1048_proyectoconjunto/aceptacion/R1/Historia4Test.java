package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

 

public class Historia4Test {

    private static Gestor  gestor;
    @BeforeAll
    public static void setServicioOpenWeather(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasValidas(){
        //Given
        String latitud = "39.987570";
        String longitud = "-0.054678";
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);


        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", coordenadas);


        //Then
        //TODO: COMPROBAR QUE LA UBICACIÓN DE ESA LATITUD Y LONGITUD ES LA QUE SE ESPERA
        assertTrue(valido);
        
    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasNoValidas(){
        //Given
        String latitud = "-100";
        String longitud = "-100";
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);


        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", coordenadas);


        //Then
        //TODO: COMPROBAR QUE LA UBICACIÓN DE ESA LATITUD Y LONGITUD ES LA QUE SE ESPERA
        assertFalse(valido);
    }
 
}
