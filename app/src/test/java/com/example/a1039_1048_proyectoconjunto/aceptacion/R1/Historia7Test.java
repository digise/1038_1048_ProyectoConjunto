package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class Historia7Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crear_gestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
    }

    @Test
    public void obtenerToponimo_coordenadasValidas(){
        //GIVEN
        String latitud = "39.69250";
        String longitud = "-0.28686";

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);


        //THEN
        assertEquals(ubicacion.getToponimo().toLowerCase(), "sagunto");

    }

    @Test
    public void obtenerToponimo_coordenadasNoValidas(){
        //GIVEN
        String latitud = "25.311261";
        String longitud = "-44.156168";

        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);

        //THEN
        assertNull(ubicacion);

    }
}