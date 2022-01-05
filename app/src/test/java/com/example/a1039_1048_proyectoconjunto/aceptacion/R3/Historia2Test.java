package com.example.a1039_1048_proyectoconjunto.aceptacion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia2Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        ConexionFirebase conexionFirebase = new ConexionFirebase();
        conexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
    }

    @Test
    public void activar_serviciosAPIDisponibles(){
        //GIVEN
        System.out.println(gestor.getAllServicios().toString());
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.desactivarServicio("openweather");


        //WHEN
        gestor.activarServicio("openweather");

        //THEN
        assertTrue(gestor.getAllServicios().containsKey("openweather"));
    }

    @Test
    public void consultar_listaServiciosNoDisponibles(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(null);
        gestor.getGestorServicios().setServicioOpenWeather(null);

        //WHEN
        Map<String, Servicio> servicios = gestor.getAllServicios();
        int nServicios = servicios.size();

        //THEN
        assertEquals(nServicios, 0);
    }
}
