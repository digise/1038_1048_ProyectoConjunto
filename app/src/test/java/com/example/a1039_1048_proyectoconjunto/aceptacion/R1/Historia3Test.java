package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class Historia3Test {

    private static Gestor gestor;

    @BeforeAll
    public static void setServicioOpenWeather(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
    }



    @Test
    public void validarToponimo_APIOpenWeather_toponimoValido(){
        //Given
        String toponimo = "Castello";


        //When
        boolean valido = gestor.validarToponimo("OPENWEATHER", toponimo);


        //Then
        assertTrue(valido);
    }

    @Test
    public void validarToponimo_APIOpenWeather_toponimoNoValido(){
        //Given
        String toponimo = "NoExiste";


        //When
        boolean valido = gestor.validarToponimo("OPENWEATHER", toponimo);


        //Then
        assertFalse(valido);
    }


}
