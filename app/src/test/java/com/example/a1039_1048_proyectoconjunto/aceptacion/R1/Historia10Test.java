package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Historia10Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();

        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
    }

    @Test
    public void darBajaUbicacionExistente_ubicacion_valido(){
        //GIVEN
        String albacete = "albacete";
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo(albacete));


        //WHEN
        int numUbicacionesAntesBorrado = gestor.getAllUbicaciones().size();
        gestor.darBajaUbicacion(gestor.getUbicacionGuardada(albacete));


        //THEN
        int numUbicacionesDespuesBorrado = gestor.getAllUbicaciones().size();
        assertEquals(numUbicacionesAntesBorrado, numUbicacionesDespuesBorrado + 1);
        assertNull(gestor.getUbicacionGuardada(albacete));
    }

    @Test
    public void darBajaUbicacionNoExistente_ubicacion_noValido(){
        //GIVEN
        //WHEN
        //THEN
    }
}
