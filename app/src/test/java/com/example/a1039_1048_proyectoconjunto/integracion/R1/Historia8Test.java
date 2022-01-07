package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.util.HashMap;

public class Historia8Test {

    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static Gestor gestor;

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
        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));
    }

    @Test
    public void asignarAlias_valido() {
        //GIVEN
        String alias = "Saguntum";
        Ubicacion ubicacion = gestor.getUbicacionGuardada("sagunto");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);


        //WHEN
        boolean aliasCambiado = ubicacion.setAlias(alias);


        //THEN
        assertTrue(aliasCambiado);
        assertEquals(ubicacion.getAlias(), alias);
    }

    @Test
    public void asignarAlias_Novalido() {
        //GIVEN
        String alias = "este alias tiene mas de 20 caracteres";
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        Ubicacion ubicacion = gestor.getUbicacionGuardada("sagunto");
        ubicacion.setAlias("Saguntum");


        //WHEN
        boolean aliasCambiado = ubicacion.setAlias(alias);


        //THEN
        assertFalse(aliasCambiado);
        assertEquals("Saguntum", ubicacion.getAlias());
    }
}
