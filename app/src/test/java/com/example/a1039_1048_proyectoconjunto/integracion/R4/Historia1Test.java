package com.example.a1039_1048_proyectoconjunto.integracion.R4;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.HashMap;

public class Historia1Test {

    private Gestor gestor;
    private InOrder inOrder;
    private ConexionFirebase mockConexionFirebase;

    @BeforeEach
    public void setUp(){
        Gestor.getInstance().borrarTodaLaInformacionDeLaAplicacion();

        gestor = Gestor.getInstance();
        mockConexionFirebase = mock(ConexionFirebase.class);
        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebase);
        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebase);

        when(mockConexionFirebase.getCollection(anyString(),anyObject())).thenReturn(new HashMap<>());
        when(mockConexionFirebase.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        when(mockConexionFirebase.createDocument(anyString(), anyObject(), anyString())).thenReturn("PRUEBA");
        when(mockConexionFirebase.removeDocument(anyString(), anyString())).thenReturn(true);
        inOrder = Mockito.inOrder(mockConexionFirebase);
    }

    @Test
    public void recuperarEstadoAnterior_valido(){
        //GIVEN -> cargar estado (no habrá nada)
        gestor = Gestor.getInstance();



        //WHEN
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //THEN
        inOrder.verify(mockConexionFirebase, times(2)).getDocument(anyString(), anyString(), anyObject());
        inOrder.verify(mockConexionFirebase).getCollection(anyString(), anyObject());
    }

    @Test
    public void rcuperarEstadoAnterior_noValido(){
        //GIVEN -> cargar estado (no habrá nada)
        gestor = Gestor.getInstance();

        //WHEN -> vacío por que no carga nada

        //THEN
        inOrder.verify(mockConexionFirebase, times(0)).getDocument(anyString(), anyString(), anyObject());
        inOrder.verify(mockConexionFirebase, times(0)).getCollection(anyString(), anyObject());
    }
}
