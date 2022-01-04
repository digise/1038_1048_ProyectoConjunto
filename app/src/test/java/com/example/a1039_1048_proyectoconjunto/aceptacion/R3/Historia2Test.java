package com.example.a1039_1048_proyectoconjunto.aceptacion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia2Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
    }

    @Test
    public void activar_serviciosAPIDisponibles(){
        //GIVEN
        gestor.activarServicio("currents");
        gestor.desactivarServicio("openweather");

        Map<String, Servicio> servicios = gestor.getAllServicios();
        servicios.get("openweather").servicioActivo(false);
        servicios.get("currents").servicioActivo(false);
        ServicioOpenWeather servicioOpenWeather = (ServicioOpenWeather) servicios.get("openweather");
        Map<String, String> infoConLaApiDesactivada = servicioOpenWeather.getInformacion("castello");

        //WHEN
        servicios.get("openweather").servicioActivo(true);
        Map<String, String> infoConLaApiActivada = servicioOpenWeather.getInformacion("castello");


        //THEN
        assertNull(infoConLaApiDesactivada);
        assertNotNull(infoConLaApiActivada);
        assertNotNull(infoConLaApiActivada.get("sensacionTermica"));
        assertNotNull(infoConLaApiActivada.get("temperaturaMedia"));
        assertNotNull(infoConLaApiActivada.get("humedad"));
        assertNotNull(infoConLaApiActivada.get("presion"));
        assertEquals(servicios.keySet().size(), 2);
    }

    @Test
    public void consultar_listaServiciosNoDisponibles(){
        //GIVEN
        //GIVEN
        Map<String, Servicio> servicios = gestor.getAllServicios();
        int nServiciosAntesDeEliminarlos = servicios.size();
        gestor.getGestorServicios().setServicioCurrents(null);
        gestor.getGestorServicios().setServicioOpenWeather(null);
        ServicioOpenWeather servicioOpenWeather = (ServicioOpenWeather) servicios.get("openweather");

        //WHEN
        int nServiciosActuales = servicios.size();


        //THEN
        assertNotEquals(nServiciosAntesDeEliminarlos, nServiciosActuales);
        assertEquals(nServiciosAntesDeEliminarlos, 2);
        assertEquals(nServiciosActuales, 0);
    }
}
