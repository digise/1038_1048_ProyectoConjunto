package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia4_1Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));
    }

    @Test
    public void consultar_informacionApiOpenWeatherDeUnaUbicacion_todoDisponible(){
        //GIVEN
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        gestor.activarServicio("openweather");
        ubicacion.activar();
        ubicacion.activarServicio("openweather", true);


        //WHEN
        Map<String, String> infoOpenWeather = gestor.getTiempoPorUbicacion(ubicacion);
        System.out.println(infoOpenWeather);


        //THEN
        assertNotNull(infoOpenWeather);
        assertNotNull(infoOpenWeather.get("sensacionTermica"));
        assertNotNull(infoOpenWeather.get("temperaturaMedia"));
        assertNotNull(infoOpenWeather.get("humedad"));
        assertNotNull(infoOpenWeather.get("presion"));
    }

    @Test
    public void consultar_informacionApiOpenWeatherDeUnaUbicacion_apiNoDisponible(){
        //GIVEN
        gestor.desactivarServicio("openweather");
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        ubicacion.activar();


        //WHEN
        Map<String, String> infoOpenWeather = gestor.getTiempoPorUbicacion(ubicacion);


        //THEN
        assertNull(infoOpenWeather);
    }
}
