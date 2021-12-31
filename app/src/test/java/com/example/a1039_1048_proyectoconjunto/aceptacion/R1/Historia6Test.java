package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class Historia6Test {

    private static Gestor gestor;

    @BeforeAll
    public static void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
    }

    @Test
    public void obtenerCoordenadas_toponimoValido(){
        //GIVEN
        String toponimo = "castellon";

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);

        //THEN
        assertEquals(ubicacion.getLatitud(), "39.97990");
        assertEquals(ubicacion.getLongitud(), "-0.03304");
    }
    @Test
    public void obtenerCoordenadas_toponimoNoValido(){
        //GIVEN
        String toponimo = "noExiste";

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);

        //THEN
        assertNull(ubicacion);
    }
}
