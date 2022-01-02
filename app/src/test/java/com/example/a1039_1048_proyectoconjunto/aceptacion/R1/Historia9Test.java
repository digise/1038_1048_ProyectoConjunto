package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Historia9Test {

    private static Gestor gestor;

    @BeforeAll
    public static void anadirUbicacionParaHacerPrueba(){
        gestor = Gestor.getInstance();

        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());

        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castellon"));
    }

    @Test
    public void desactivarUbicacionActivada_valido(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castello de la plana");
        castellon.activarServicio("currents", true);
        castellon.activar();


        //WHEN
        castellon.desactivar();

        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }

    @Test
    public void desactivarUbicacionDesactivada_noValido(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castello de la plana");
        castellon.activarServicio("currents", true);
        castellon.desactivar();


        //WHEN
        castellon.desactivar();

        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }

}
