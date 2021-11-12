package com.example.a1039_1048_proyectoconjunto.R1.integracion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Gestor;
import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class Historia1Test {

    @Mock private ServicioGeocoding mockServicioGeocoding;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void altaUbicacion_toponimoExistente_anadir(){
        // Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioGeocoding(mockServicioGeocoding);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";
        when(mockServicioGeocoding.getUbicacionByToponimo(toponimo)).thenReturn(new Ubicacion(toponimo));

        // When
        gestor.darAltaToponimo(toponimo);


        // Then
        int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
        String toponimoUbicacion = gestorUbicaciones.getUbicacion(toponimo).getToponimo();
        assertEquals(1, nUbicaciones);
        assertEquals("Castello", toponimoUbicacion);
    }

    @Test
    public void altaUbicacion_toponimoNoExistente_anadir(){
        // Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioGeocoding(mockServicioGeocoding);

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();
        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "NoExiste";
        when(mockServicioGeocoding.getUbicacionByToponimo(toponimo)).thenReturn(null);

        // When
        gestor.darAltaToponimo(toponimo);


        // Then
        int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
        assertEquals(0, nUbicaciones);
    }
}
