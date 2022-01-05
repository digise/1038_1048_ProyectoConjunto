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
import java.util.List;
import java.util.Map;

public class Historia6Test {

    private static Gestor gestor;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;

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
    }

    @Test
    public void anadirFavoritos_valido(){
        //GIVEN
        Ubicacion sagunto = new Ubicacion("sagunto", "spain", "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");
        Ubicacion alicante = new Ubicacion("alicante", "spain", "38.3451700", " -0.4814900");

        castellon.activar();
        valencia.activar();
        sagunto.desactivar();
        alicante.desactivar();

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);
        ubicacionesMentira.put("-MsT0srQkDSR9yU3zdsx", alicante);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));

        gestor.getGestorUbicaciones().generarUbicaciones();


        //WHEN
        boolean marcada = gestor.marcarComoFavorita(valencia, true);


        //THEN
        List<Ubicacion> favs = gestor.getUbicacionesFavoritas();
        assertTrue(marcada);
        assertEquals(1, favs.size());
        assertEquals(valencia, favs.get(0));
    }

    @Test
    public void anadirFavoritos_noValido(){
        //GIVEN
        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");

        castellon.activar();

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>());

        gestor.getGestorUbicaciones().generarUbicaciones();


        //WHEN
        boolean marcada = gestor.marcarComoFavorita(castellon, true);


        //THEN
        List<Ubicacion> favs = gestor.getUbicacionesFavoritas();
        assertTrue(marcada);
        assertEquals(0, favs.size());
    }
}
