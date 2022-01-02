package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia7Test {
    // TODO Ejemplo con sagunto

    private Gestor gestor;

    @Mock
    private ServicioGeocoding mockServicioGeocoding;

    
    @BeforeEach
    public void crear_gestor(){
        MockitoAnnotations.initMocks(this);
        gestor = Gestor.getInstance();
        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
    }

    
    @Test
    public void obtenerToponimo_coordenadasValidas(){
        //GIVEN
        String latitud = "39.69250";
        String longitud = "-0.28686";
        Ubicacion mockUbicacion = new Ubicacion("sagunto", "Spain", latitud, longitud);
        
        when(mockServicioGeocoding.getInformacion(latitud, longitud)).thenReturn(mockUbicacion);


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
        
        when(mockServicioGeocoding.getInformacion(latitud, longitud)).thenReturn(null);


        //WHEN
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);


        //THEN
        assertNull(ubicacion);
    }
}
