package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Coordenadas;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class Historia2Test {

    @Test
    public void altaUbicacion_coordenadasExistentes_anadir(){
        // Given
        Gestor gestor = Gestor.getInstance();
        String latitud = "40.4619719";
        String longitud = "0.3548686";
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);

        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.darAltaUbicacionPorCoordenadas(coordenadas);
        boolean dadoAlta = gestor.getGestorUbicaciones().addUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
    }

    @Test
    public void altaUbicacion_coordenadasNoExistentes_anadir(){
        // Given
        Gestor gestor = Gestor.getInstance();
        String latitud = "40.4619719";
        String longitud = "0.3548686";
        Coordenadas coordenadas = new Coordenadas(latitud, longitud);

        ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
        gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.darAltaUbicacionPorCoordenadas(coordenadas);
        boolean dadoAlta = gestor.getGestorUbicaciones().addUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
    }


}
