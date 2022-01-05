package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public class Historia1Test {

    private static ServicioGeocoding mockServicioGeocoding;
    private static Gestor gestor;

    @BeforeAll
    public static void setUp() {
        mockServicioGeocoding = mock(ServicioGeocoding.class);

        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        GestorUbicaciones gestorUbicacionesSpy = spy(gestor.getGestorUbicaciones());
        doReturn(ubicacionesMentira).when(gestorUbicacionesSpy).getUbicacionesFirebase();
        doReturn("HLPO").when(gestorUbicacionesSpy).crearUbicacionFirebase(anyObject());
        doReturn(true).when(gestorUbicacionesSpy).updateUbicacionFirebase(anyObject(), eq("HLPO"));

        gestor.setGestorUbicaciones(gestorUbicacionesSpy);
        gestorUbicacionesSpy.generarUbicaciones();
    }

    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        // Given
        String toponimo = "sagunto";
        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);

        Ubicacion ubicacionMock = new Ubicacion(toponimo, "Spain", "39.6833", "-0.2667");
        when(mockServicioGeocoding.getInformacion(toponimo)).thenReturn(ubicacionMock);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


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
    public void altaUbicacion_toponimoNoExistente_anadir() {
        // Given
        Gestor gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        String toponimo = "noExiste";
        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);

        when(mockServicioGeocoding.getInformacion(toponimo)).thenReturn(null);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When --> Cuando se va a dar de alta ubicacion inexistente devuelve null
        Ubicacion ubicacion = gestor.getUbicacionPorToponimo(toponimo);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
    }
}
