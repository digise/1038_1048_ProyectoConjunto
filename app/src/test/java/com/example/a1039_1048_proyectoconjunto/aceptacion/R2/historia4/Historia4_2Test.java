package com.example.a1039_1048_proyectoconjunto.aceptacion.R2.historia4;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia4_2Test {

    private static Gestor gestor;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());
    }

    @Test
    public void consultar_informacionCurrentsDeUnaUbicacion_todoDisponible(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        ubicacion.activar();
        ubicacion.activarServicio("currents", true);


        //WHEN
        Map<String, HashMap<String, String>> infoNoticias = gestor.getNoticiasPorUbicacion(ubicacion);


        //THEN
        assertNotNull(infoNoticias);
    }

    @Test
    public void consultar_informacionCurrentsDeUnaUbicacion_apiNoDisponible(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(null);
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        ubicacion.activar();


        //WHEN
        Map<String, HashMap<String, String>> infoNoticias = gestor.getNoticiasPorUbicacion(ubicacion);


        //THEN
        assertNull(infoNoticias);
    }
}