package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Historia4_2Test {

    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));
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
        System.out.println(infoNoticias);


        //THEN
        assertNotNull(infoNoticias);
        for (String id : infoNoticias.keySet()) {
            assertNotNull(Objects.requireNonNull(infoNoticias.get(id)).get("titulo"));
            assertNotNull(Objects.requireNonNull(infoNoticias.get(id)).get("descripcion"));
            assertNotNull(Objects.requireNonNull(infoNoticias.get(id)).get("urlNew"));
        }
    }

    @Test
    public void consultar_informacionCurrentsDeUnaUbicacion_apiNoDisponible(){
        //GIVEN
        gestor.desactivarServicio("currents");
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");
        ubicacion.activar();


        //WHEN
        Map<String, HashMap<String, String>> infoNoticias = gestor.getNoticiasPorUbicacion(ubicacion);


        //THEN
        assertNull(infoNoticias);
    }
}
