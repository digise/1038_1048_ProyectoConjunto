package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia10Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        ConexionFirebase conexionFirebase = new ConexionFirebase();
        conexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
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
