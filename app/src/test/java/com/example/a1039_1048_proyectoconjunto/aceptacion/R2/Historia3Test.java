package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

public class Historia3Test {
    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Sagunto"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Valencia"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Castello"));


    }

    @Test
    public void listaUbicaciones_activas_encontradas(){
        //GIVEN

        gestor.activarUbicacion("Sagunto");
        gestor.activarUbicacion("Valencia");
        gestor.activarUbicacion("Castello");


        //WHEN
        Map<String, Ubicacion> ubicacionesActivas = gestor.getUbicacionesActivas();
        int nubicacionesActivas = gestor.getUbicacionesActivas().size();


        //THEN
        assertEquals(nubicacionesActivas, 3);
    }

    @Test
    public void listaUbicaciones_activas_noEncontradas(){
        //GIVEN
        gestor.desactivarUbicacion("Sagunto");
        gestor.desactivarUbicacion("Valencia");
        gestor.desactivarUbicacion("Castello");

        //WHEN
        Map<String, Ubicacion> ubicacionesActivas = gestor.getUbicacionesActivas();
        int nubicacionesActivas = gestor.getUbicacionesActivas().size();


        //THEN
        assertEquals(nubicacionesActivas, 0);
    }
}
