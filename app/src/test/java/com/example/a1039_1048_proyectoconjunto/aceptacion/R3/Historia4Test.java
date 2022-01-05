package com.example.a1039_1048_proyectoconjunto.aceptacion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class Historia4Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        ConexionFirebase.removeDocument("", "");
        gestor = Gestor.getInstance();
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("castello"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("valencia"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("alicante"));
    }

    @Test
    public void servicioApi_desactivar(){
        //GIVEN
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());


        //WHEN
        gestor.desactivarServicio("openweather");

        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(gestor.getUbicacionPorToponimo("castello")));
        assertNull(gestor.getNoticiasPorUbicacion(gestor.getUbicacionPorToponimo("valencia")));
        assertNull(gestor.getNoticiasPorUbicacion(gestor.getUbicacionPorToponimo("alicante")));
    }

    @Test
    public void serviciosApiNoDisponibles_desactivar(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(null);
        gestor.getGestorServicios().setServicioOpenWeather(null);
        Ubicacion ubicacion = gestor.getUbicacionGuardada("castello");

        //WHEN
        Map<String, Servicio> servicios = gestor.getAllServicios();
        int nServicios = servicios.size();

        //THEN
        assertEquals(nServicios, 0);
        assertNull(gestor.getNoticiasPorUbicacion(ubicacion));
    }
}
