package com.example.a1039_1048_proyectoconjunto.aceptacion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia2_2_2Test {

    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");
        gestor.activarServicio("openweather");
        gestor.activarServicio("currents");
        castello.activarServicio("openweather", true);
        valencia.activarServicio("currents", true);
    }

    @Test
    public void desactivarApi_guardarEstado(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionGuardada("castello");
        Map<String, String> recibirInformacionAntesDeActualizar = gestor.getTiempoPorUbicacion(castello);

        //WHEN
        gestor.desactivarServicio("openweather");
        castello.activarServicio("openweather", true);
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, String> recibirInformacionDespuesDeActualizar = gestor.getTiempoPorUbicacion(castello);


        //THEN
        assertNotNull(recibirInformacionAntesDeActualizar);
        assertNull(recibirInformacionDespuesDeActualizar);
    }

    @Test
    public void noDesactivarApi_guardarEstado(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionGuardada("castello");
        Map<String, String> recibirInformacionAntesDeActualizar = gestor.getTiempoPorUbicacion(castello);

        //WHEN
        castello.activarServicio("openweather", true);
        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
        Map<String, String> recibirInformacionDespuesDeActualizar = gestor.getTiempoPorUbicacion(castello);

        //THEN
        assertNotNull(recibirInformacionAntesDeActualizar);
        assertNotNull(recibirInformacionDespuesDeActualizar);
    }
}
