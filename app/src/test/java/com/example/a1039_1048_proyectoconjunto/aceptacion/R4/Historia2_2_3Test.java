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

public class Historia2_2_3Test {

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
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.activarServicio("openweather");
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");
    }

    @Test
    public void eliminarApi_guardarEstado(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionGuardada("castello");
        castello.activarServicio("openweather", true);
        Map<String, String> recibirInformacionAntesDeEliminar = gestor.getTiempoPorUbicacion(castello);
        Map<String, Servicio> serviciosAntesDeEliminar = new HashMap<>(gestor.getAllServicios());

        //WHEN
        gestor.borrarServicio("openweather");
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, String> recibirInformacionDespuesDeEliminar = gestor.getTiempoPorUbicacion(castello);
        Map<String, Servicio> serviciosDespuesDeAnadir = gestor.getAllServicios();


        //THEN
        assertNotNull(recibirInformacionAntesDeEliminar);
        assertNull(recibirInformacionDespuesDeEliminar);
        assertEquals(serviciosAntesDeEliminar.size() - 1, serviciosDespuesDeAnadir.size());
    }

    @Test
    public void noEliminarApi_guardarEstado() {
        //GIVEN
        Ubicacion valencia = gestor.getUbicacionGuardada("valencia");
        valencia.activarServicio("openweather", true);
        Map<String, String> recibirInformacionAntesDeEliminar = gestor.getTiempoPorUbicacion(valencia);
        Map<String, Servicio> serviciosAntesDeEliminar = new HashMap<>(gestor.getAllServicios());

        //WHEN
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, String> recibirInformacionDespuesDeEliminar = gestor.getTiempoPorUbicacion(valencia);
        Map<String, Servicio> serviciosDespuesDeReiniciar = gestor.getAllServicios();


        //THEN
        assertNotNull(recibirInformacionAntesDeEliminar);
        assertNotNull(recibirInformacionDespuesDeEliminar);
        assertEquals(serviciosAntesDeEliminar.size(), serviciosDespuesDeReiniciar.size());
    }
}
