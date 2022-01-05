package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import java.util.Map;

public class Historia3Test {


    private static Gestor gestor;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;

    @BeforeEach
    public void setGestor() {
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

        Ubicacion sagunto = new Ubicacion("sagunto", "spain", "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();
    }

    @Test
    public void getUbicacionesActivas_TresUbicacionesActivas_valido(){
        //GIVEN

        Ubicacion sagunto = gestor.getUbicacionGuardada("sagunto");
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        Ubicacion valencia = gestor.getUbicacionGuardada("valencia");

        sagunto.activar();
        castellon.activar();
        valencia.activar();
        //WHEN
        Map<String, Ubicacion> ubicacionesActivas = gestor.getUbicacionesActivas();

        //THEN
        assertEquals(3, ubicacionesActivas.size());
        assertTrue(ubicacionesActivas.containsValue(castellon));
        assertTrue(ubicacionesActivas.containsValue(sagunto));
        assertTrue(ubicacionesActivas.containsValue(valencia));
    }

    @Test
    public void getUbicacionesActivas_TresUbicacionesDesactivadas_noValido(){
        //GIVEN

        Ubicacion sagunto = gestor.getUbicacionGuardada("sagunto");
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        Ubicacion valencia = gestor.getUbicacionGuardada("valencia");

        sagunto.desactivar();
        castellon.desactivar();
        valencia.desactivar();
        //WHEN
        Map<String, Ubicacion> ubicacionesActivas = gestor.getUbicacionesActivas();

        //THEN
        assertEquals(0, ubicacionesActivas.size());
    }
}
