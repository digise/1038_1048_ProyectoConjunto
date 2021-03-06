package com.example.a1039_1048_proyectoconjunto.aceptacion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Historia1Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
    }

    @Test
    public void consultar_listaServiciosDisponibles(){
        //GIVEN
        Map<String, Servicio> servicios = gestor.getAllServicios();
        servicios.get("openweather").servicioActivo(false);
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
    }

    @Test
    public void consultar_listaServiciosNoDisponibles(){
        //GIVEN
        Map<String, Servicio> servicios = gestor.getAllServicios();
        gestor.getGestorServicios().setServicioCurrents(null);
        gestor.getGestorServicios().setServicioOpenWeather(null);

        //WHEN
        int nServiciosActuales = servicios.size();

        //THEN
        assertEquals(nServiciosActuales, 0);
    }

}
