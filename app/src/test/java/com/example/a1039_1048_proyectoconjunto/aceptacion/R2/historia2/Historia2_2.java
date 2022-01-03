package com.example.a1039_1048_proyectoconjunto.aceptacion.R2.historia2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia2_2 {

    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
    }

    @Test
    public void activar_ServiciosAPIIndependientes_disponible(){
        //GIVEN
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello de la plana");
        ubicacion.activar();
        ubicacion.activarServicio("openweather", true);
        Map<String, String> informacionTiempoAntesDeDesactivarApis = gestor.getTiempoPorUbicacion(ubicacion);

        //WHEN
        ubicacion.activarServicio("openweather", false);
        Map<String, String> informacionTiempoDespuesDeDesactivarApis = gestor.getTiempoPorUbicacion(ubicacion);



        //THEN
        assertNotNull(informacionTiempoAntesDeDesactivarApis);
        assertNull(informacionTiempoDespuesDeDesactivarApis);
    }

    // TODO Es la misma historia que la 2_1 segundo escenario, ¿la ponemos o no?
    /*@Test
    public void darBajaUbicacionNoExistente_ubicacion_noValido(){
        //GIVEN
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello de la plana");
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
    }*/
}
