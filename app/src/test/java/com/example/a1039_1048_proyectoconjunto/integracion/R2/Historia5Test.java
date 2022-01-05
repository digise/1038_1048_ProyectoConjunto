package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Historia5Test {
    private static Gestor gestor;

    private static ConexionFirebase mockConexionFirebaseUbicaciones;

    private static Ubicacion castellon;
    private static Ubicacion alicante;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();
    }

    @BeforeAll
    public static void setUp(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);

        Ubicacion sagunto = new Ubicacion("sagunto", "spain", "39.69250", "-0.28686");
        castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");
        alicante = new Ubicacion("alicante", "spain", "38.3451700", " -0.4814900");

        sagunto.desactivar();
        castellon.activar();
        valencia.activar();

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
    }

    @Test
    public void consultarHistoricoUbicaciones_valido(){
        //GIVEN

        //WHEN
        Set<Ubicacion> historicoUbicaciones = new HashSet<>(gestor.getAllUbicaciones().values());

        //THEN
        assertNotNull(historicoUbicaciones);
        assertEquals(3, historicoUbicaciones.size());
        assertTrue(historicoUbicaciones.contains(castellon));
    }

    @Test
    public void consultarHistoricoUbicaciones_noValido(){
        //GIVEN

        //WHEN
        Set<Ubicacion> historicoUbicaciones = new HashSet<>(gestor.getAllUbicaciones().values());

        //THEN
        assertNotNull(historicoUbicaciones);
        assertEquals(3, historicoUbicaciones.size());
        assertFalse(historicoUbicaciones.contains(alicante));
    }
}
