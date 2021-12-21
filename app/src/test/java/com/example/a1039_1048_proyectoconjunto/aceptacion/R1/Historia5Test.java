package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.Test;

public class Historia5Test {

    @Test
    public void activarUbicacion_servicioDisponible_activar(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioOpenWeather(new ServicioOpenWeather());

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";

        boolean dadoAlta = gestor.darAltaToponimo(toponimo);


        //When
        Ubicacion castellon = gestor.getUbicacion(toponimo);
        castellon.activar();


        //Then
        assertTrue(castellon.isActivada());

    }

    @Test
    public void activarUbicacion_servicioNoDisponible_activar(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";

        boolean dadoAlta = gestor.darAltaToponimo(toponimo);


        //When
        Ubicacion castellon = gestor.getUbicacion(toponimo);
        castellon.activar();


        //Then
        assertFalse(castellon.isActivada());

    }

}
