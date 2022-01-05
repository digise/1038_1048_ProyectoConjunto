package com.example.a1039_1048_proyectoconjunto.aceptacion.R2.historia2;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia2_1 {

    private static Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        ConexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));

    }

    @Test
    public void activar_ServiciosAPIIndependientes_disponible(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        ubicacion.activar();
        ubicacion.activarServicio("openweather", false);
        ubicacion.activarServicio("currents", false);
        Map<String, String> informacionTiempoAntesDeActivarApis = gestor.getTiempoPorUbicacion(ubicacion);

        //WHEN
        ubicacion.activarServicio("openweather", true);
        Map<String, String> informacionTiempoDespuesDeActivarApis = gestor.getTiempoPorUbicacion(ubicacion);
        System.out.println(informacionTiempoDespuesDeActivarApis);



        //THEN
        assertNull(informacionTiempoAntesDeActivarApis);
        assertNotNull(informacionTiempoDespuesDeActivarApis);
    }

    @Test
    public void activarApisEnUbicaciones_noDisponibles(){
        //GIVEN
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        gestor.getGestorServicios().setServicioCurrents(null);
        gestor.getGestorServicios().setServicioOpenWeather(null);
        ubicacion.activar();
        Map<String, String> informacionTiempoSinTenerLaApi = gestor.getTiempoPorUbicacion(ubicacion);

        //WHEN
        ubicacion.activarServicio("openweather", true);
        Map<String, String> informacionTiempoDespuesDeActivarApis = gestor.getTiempoPorUbicacion(ubicacion);



        //THEN
        assertNull(informacionTiempoSinTenerLaApi);
        assertNull(informacionTiempoDespuesDeActivarApis);
    }
}
