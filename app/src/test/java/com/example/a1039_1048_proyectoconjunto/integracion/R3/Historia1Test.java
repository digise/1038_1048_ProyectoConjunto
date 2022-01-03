package com.example.a1039_1048_proyectoconjunto.integracion.R3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia1Test {
    private Gestor gestor;

    @BeforeEach
    public void setGestor() {
        gestor = Gestor.getInstance();
    }

    @Test
    public void activarServicio_servicioOpenWeather_valido(){
        //GIVEN
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());

        gestor.desactivarServicio("openweather");
        gestor.desactivarServicio("currents");

        //WHEN
        gestor.activarServicio("openweather");

        //THEN
        assertTrue(gestor.getGestorServicios().getServicioOpenWeather().isActivo());
        assertFalse(gestor.getGestorServicios().getServicioCurrents().isActivo());

        assertNotNull(gestor.getGestorServicios().getServicioOpenWeather());
        assertNotNull(gestor.getGestorServicios().getServicioCurrents());
    }

    @Test
    public void activarServicio_noServiciosActivos_noValido(){
        //GIVEN
        gestor.getGestorServicios().setServicioOpenWeather(null);
        gestor.getGestorServicios().setServicioCurrents(null);
        ServicioOpenWeather servicioOpenWeather = gestor.getGestorServicios().getServicioOpenWeather();
        ServicioCurrents servicioCurrents = gestor.getGestorServicios().getServicioCurrents();

        //WHEN
        gestor.activarServicio("openweather");

        //THEN
        assertNull(servicioCurrents);
        assertNull(servicioOpenWeather);
    }
}
