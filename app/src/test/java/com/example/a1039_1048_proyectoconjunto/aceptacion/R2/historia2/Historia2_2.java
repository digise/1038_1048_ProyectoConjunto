package com.example.a1039_1048_proyectoconjunto.aceptacion.R2.historia2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia2_2 {

    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        ConexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
    }

    @Test
    public void activar_ServiciosAPIIndependientes_disponible(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
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
    @Test
    public void darBajaUbicacionNoExistente_ubicacion_noValido(){
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        gestor.getGestorServicios().setServicioCurrents(null);
        gestor.getGestorServicios().setServicioOpenWeather(null);
        ubicacion.activar();
        Map<String, String> informacionTiempoSinTenerLaApi = gestor.getTiempoPorUbicacion(ubicacion);

        //WHEN
        ubicacion.activarServicio("openweather", false);
        Map<String, String> informacionTiempoDespuesDeActivarApis = gestor.getTiempoPorUbicacion(ubicacion);



        //THEN
        assertEquals(gestor.getAllServicios().size(), 0);
        assertNull(informacionTiempoSinTenerLaApi);
        assertNull(informacionTiempoDespuesDeActivarApis);
    }
}
