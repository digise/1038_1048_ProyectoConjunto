package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Historia6Test {

    private Gestor gestor;

    @BeforeEach
    public void crear_gestor(){
        ConexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
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
