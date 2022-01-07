package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia9Test {

    private static CurrentsAdapter mockCurrentsAdapter;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static ConexionFirebase mockConexionFirebaseServicios;
    private static Gestor gestor;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
        gestor.getGestorServicios().recuperarInformacionServicios();
    }

    @BeforeAll
    public static void setUp(){
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        mockConexionFirebaseServicios = mock(ConexionFirebase.class);

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
        when(mockConexionFirebaseUbicaciones.removeDocument(anyString(), anyString())).thenReturn(true);

        when(mockConexionFirebaseServicios.getCollection(anyString(), anyObject())).thenReturn(null);
        when(mockConexionFirebaseServicios.getDocument(anyString(), anyString(),anyObject())).thenReturn(null);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(true);

    }

    @Test
    public void desactivarUbicacionActivada_valido() {
        //GIVEN
        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(),anyString())).thenReturn("true");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(),anyString())).thenReturn(true);

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        when(mockCurrentsAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);


        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("currents", true);


        //WHEN
        gestor.desactivarUbicacion("castello");


        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }

    @Test
    public void desactivarUbicacionDesactivada_noValido(){
        //GIVEN
        when(mockConexionFirebaseUbicaciones.createDocument(anyString(), anyObject(),anyString())).thenReturn("true");
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(),anyString())).thenReturn(true);

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        when(mockCurrentsAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);


        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("currents", true);
        castellon.desactivar();


        //WHEN
        gestor.desactivarUbicacion("castello");


        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }
}
