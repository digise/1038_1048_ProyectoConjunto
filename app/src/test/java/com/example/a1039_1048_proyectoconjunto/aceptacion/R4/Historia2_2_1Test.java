package com.example.a1039_1048_proyectoconjunto.aceptacion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia2_2_1Test {

    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.desactivarServicio("currents");
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");
    }

    @Test
    public void anadirApi_guardarEstado(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionGuardada("castello");
        Map<String, Servicio> serviciosAntesDeAnadir = new HashMap<>(gestor.getAllServicios());

        //WHEN
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        castello.activarServicio("openweather", true);
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, Servicio> serviciosDespuesDeAnadirYReiniciarLaAplicacion = gestor.getAllServicios();

        //THEN
        assertNotNull(gestor.getServicio("openweather"));
        assertNotNull(gestor.getTiempoPorUbicacion(castello));
        assertEquals(serviciosAntesDeAnadir.size() + 1, serviciosDespuesDeAnadirYReiniciarLaAplicacion.size());
    }

    @Test
    public void noAnadirApi_guardarEstado(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionGuardada("castello");
        Map<String, Servicio> serviciosAntesDeAnadir = new HashMap<>(gestor.getAllServicios());

        //WHEN
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, Servicio> serviciosDespuesDeReiniciarLaAplicacion = gestor.getAllServicios();

        //THEN
        assertNull(gestor.getServicio("openweather"));
        assertNull(gestor.getTiempoPorUbicacion(castello));
        assertEquals(serviciosAntesDeAnadir.size(), serviciosDespuesDeReiniciarLaAplicacion.size());
    }
}
