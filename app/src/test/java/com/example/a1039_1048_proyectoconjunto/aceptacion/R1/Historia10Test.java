package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Historia10Test {
    private static Gestor gestor;

    @BeforeAll
    public static void anadirUbicacionParaHacerPrueba(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());

        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castellon de la plana"));
    }

    @Test
    public void asignarAlias_valido(){
        //GIVEN
        String alias = "Saguntum";
        Ubicacion ubicacion = gestor.getUbicacionGuardada("sagunto");


        //WHEN
        boolean aliasCambiado = ubicacion.setAlias(alias);


        //THEN
        assertTrue(aliasCambiado);
        assertEquals(ubicacion.getAlias(), alias);
    }

    @Test
    public void asignarAlias_Novalido(){
        //GIVEN
        String alias = "";
        Ubicacion ubicacion = gestor.getUbicacionGuardada("sagunto");
        ubicacion.setAlias("Saguntum");


        //WHEN
        boolean aliasCambiado = ubicacion.setAlias(alias);


        //THEN
        assertFalse(aliasCambiado);
        assertEquals(ubicacion.getAlias(), "Saguntum");
    }
}
