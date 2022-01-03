package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Historia1Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();

        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
    }

    @Test
    public void darBajaUbicacionExistente_ubicacion_valido(){
        //GIVEN
        String toponimo = "calig";
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo(toponimo));


        //WHEN
        int numUbicacionesAntesBorrado = gestor.getAllUbicaciones().size();
        gestor.darBajaUbicacion(gestor.getUbicacionGuardada(toponimo));


        //THEN
        int numUbicacionesDespuesBorrado = gestor.getAllUbicaciones().size();
        assertEquals(numUbicacionesAntesBorrado, numUbicacionesDespuesBorrado + 1);
        assertNull(gestor.getUbicacionGuardada(toponimo));
    }

    @Test
    public void darBajaUbicacionNoExistente_ubicacion_noValido(){
        //GIVEN
        String toponimo = "albacete";


        //WHEN
        int numUbicacionesAntesBorrado = gestor.getAllUbicaciones().size();
        gestor.darBajaUbicacion(gestor.getUbicacionGuardada(toponimo));


        //THEN
        int numUbicacionesDespuesBorrado = gestor.getAllUbicaciones().size();
        assertEquals(numUbicacionesAntesBorrado, numUbicacionesDespuesBorrado);
        assertNull(gestor.getUbicacionGuardada(toponimo));
    }
}
