package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 

public class Historia4Test {

    private Gestor gestor;

    @BeforeEach
    public void setServicioOpenWeather(){
        ConexionFirebase conexionFirebase = new ConexionFirebase();
        conexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
    }

    @Test
    public void validarCoordenadas_APIOpenWeather_coordenadasValidas(){
        //Given
        String latitud = "39.987570";
        String longitud = "-0.054678";


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


        //When
        boolean valido = gestor.validarCoordenadas("OPENWEATHER", latitud, longitud);


        //Then
        //TODO: COMPROBAR QUE LA UBICACIÓN DE ESA LATITUD Y LONGITUD ES LA QUE SE ESPERA
        assertFalse(valido);
    }
 
}
