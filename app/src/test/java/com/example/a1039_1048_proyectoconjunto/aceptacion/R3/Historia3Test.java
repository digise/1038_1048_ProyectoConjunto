package com.example.a1039_1048_proyectoconjunto.aceptacion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia3Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        ConexionFirebase conexionFirebase = new ConexionFirebase();
        conexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
    }

    @Test
    public void descripcionServiciosApi_disponibles(){
        //GIVEN
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());


        //WHEN
        ServicioCurrents servicioCurrents = (ServicioCurrents) gestor.getAllServicios().get("currents");

        //THEN
        assertNotNull(servicioCurrents.getDescripcion());
    }

    @Test
    public void descripcionServiciosApi_noDisponibles_sinDescripcion(){
        //GIVEN
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());


        //WHEN
        ServicioOpenWeather servicioOpenWeather = (ServicioOpenWeather) gestor.getAllServicios().get("openweather");

        //THEN
        assertEquals(servicioOpenWeather.getDescripcion(), "");
    }
}
