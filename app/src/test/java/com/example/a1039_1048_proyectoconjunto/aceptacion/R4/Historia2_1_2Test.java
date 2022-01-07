package com.example.a1039_1048_proyectoconjunto.aceptacion.R4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Historia2_1_2Test {
    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.activarUbicacion("castello");
        gestor.activarUbicacion("valencia");

    }

    @Test
    public void actualizarUbicacion_guardarEstado(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castellon");

        //WHEN
        castellon.desactivar();
        castellon.setAlias("provincia valencia");

        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //THEN
        assertEquals(gestor.getUbicacionGuardada("castello").getAlias(), "provincia valencia");
        assertFalse(gestor.getUbicacionGuardada("castello").isActivada());
    }

    @Test
    public void actualizacionUbicacion_novalido_guardarEstado(){
        //GIVEN
        Ubicacion valencia = gestor.getUbicacionGuardada("valencia");
        valencia.setAlias("valenciaCity");

        //WHEN
        valencia.desactivar();
        valencia.setAlias("");

        gestor.borrarGestor();
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //THEN
        assertEquals(gestor.getUbicacionGuardada("valencia").getAlias(), "valenciaCity");
        assertFalse(gestor.getUbicacionGuardada("valencia").isActivada());
    }
}
