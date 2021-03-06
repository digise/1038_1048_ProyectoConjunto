package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia5Test {

    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
    }

    @Test
    public void consultarHistoricoUbicaciones_valido(){
        //GIVEN
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("valencia"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("alicante"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("cinctorres"));
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        ubicacion.activar();

        //WHEN
        Map<String, Ubicacion> ubicaciones = gestor.getAllUbicaciones();


        //THEN
        assertEquals(ubicaciones.size(), 4);
        for (Ubicacion u : ubicaciones.values()){
            if (u.getToponimo().equalsIgnoreCase("castello"))
                assertTrue(u.isActivada());
            else
                assertFalse(u.isActivada());
        }
    }

    @Test
    public void consultarHistoricoUbicaciones_noValido(){
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("valencia"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("cinctorres"));
        Ubicacion ubicacion = gestor.getUbicacionGuardada("alicante");

        //WHEN
        Map<String, Ubicacion> ubicaciones = gestor.getAllUbicaciones();


        //THEN
        assertEquals(ubicaciones.size(), 3);
        assertNull(ubicacion);
    }
}
