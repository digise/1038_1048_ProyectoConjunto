package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
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

          Gestor gestor = Gestor.getInstance();
          String toponimo = "sagunto";
          gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
          Ubicacion ubicacionMock = new Ubicacion(toponimo, "Spain", "39.6833", "-0.2667");

          when(mockServicioGeocoding.getInformacion(toponimo)).thenReturn(ubicacionMock);

          int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


          // When
          Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
          boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
          int nUbicacionesAlInsertar = gestor.getAllUbicaciones().size();

          // Then
          assertTrue(dadoAlta);
          assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
          assertEquals(gestor.getUbicacionGuardada(toponimo), ubicacionMock);
       }

       @Test
       public void altaUbicacion_toponimoNoExistente_anadir(){
          // Given
          Gestor gestor = Gestor.getInstance();
          String toponimo = "noExiste";
          gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);

          when(mockServicioGeocoding.getInformacion(toponimo)).thenReturn(null);

          int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


          // When --> Cuando se va a dar de alta ubicacion inexistente devuelve null
          Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
          boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
          int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getUbicaciones().size();


          // Then
          assertFalse(dadoAlta);
          assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
       }
}
