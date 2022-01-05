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

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia2Test {


    private static ServicioGeocoding mockServicioGeocoding;
    private static Gestor gestor;

    @BeforeAll
    public static void setUp(){
        mockServicioGeocoding = mock(ServicioGeocoding.class);

        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        GestorUbicaciones gestorUbicacionesSpy = spy(gestor.getGestorUbicaciones());
        doReturn(ubicacionesMentira).when(gestorUbicacionesSpy).getUbicacionesFirebase();
        doReturn("HLPO").when(gestorUbicacionesSpy).crearUbicacionFirebase(anyObject());
        doReturn(true).when(gestorUbicacionesSpy).updateUbicacionFirebase(anyObject(), eq("HLPO"));
        gestorUbicacionesSpy.generarUbicaciones();
        gestor.setGestorUbicaciones(gestorUbicacionesSpy);
    }

    @Test
    public void altaUbicacion_coordenadasExistentes_anadir(){
        // Given
        String latitud = "40.4619719";
        String longitud = "0.3548686";
        Ubicacion ubicacionMock = new Ubicacion("Calig", "Spain", latitud, longitud);

        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
        when(mockServicioGeocoding.getInformacion(latitud, longitud)).thenReturn(ubicacionMock);
        int nUbicacionesAntesDeInsertar = gestor.getAllUbicaciones().size();

        // When
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertTrue(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
        assertEquals(gestor.getUbicacionGuardada(ubicacionMock.getToponimo()), ubicacionMock);
    }


    @Test
    public void altaUbicacion_coordenadasNoExistentes_anyadir(){
        //Given
        String latitud = "-91";
        String longitud = "-100";

        gestor.getGestorServicios().setServicioGeocoding(mockServicioGeocoding);
        when(mockServicioGeocoding.getInformacion(latitud, longitud)).thenReturn(null);

        int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // When
        Ubicacion ubicacion = gestor.getUbicacionPorCoordenadas(latitud, longitud);
        boolean dadoAlta = gestor.darAltaUbicacion(ubicacion);
        int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getAllUbicaciones().size();


        // Then
        assertFalse(dadoAlta);
        assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);
    }
}