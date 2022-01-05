package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Historia8_2Test {
    @Test
    public void ordernarUbicaciones_ordenCreacion_noValido(){
        //GIVEN
        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        Gestor gestor = Gestor.getInstance();
        GestorUbicaciones gestorUbicacionesSpy = spy(gestor.getGestorUbicaciones());
        doReturn(ubicacionesMentira).when(gestorUbicacionesSpy).getUbicacionesFirebase();
        gestor.setGestorUbicaciones(gestorUbicacionesSpy);
        gestorUbicacionesSpy.generarUbicaciones();

        //WHEN
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasRecientes();
        List<Ubicacion> ubicacionesOrdenadasRecientes = gestor.getUbicacionesOrdenadas("recientemente");

        //THEN
        assertEquals(gestor.getAllUbicaciones(), ubicacionesMentira);
        assertEquals(ubicacionesOrdenadasRecientes, new ArrayList<>());
    }

    @Test
    public void ordernarUbicaciones_ordenCreacion_valido(){
        //GIVEN
        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");
        Ubicacion calig = new Ubicacion("calig", "spain" , "40.47058", "0.36725");

        sagunto.setNumCreacion(0);
        castellon.setNumCreacion(1);
        calig.setNumCreacion(2);
        valencia.setNumCreacion(3);

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);
        ubicacionesMentira.put("-MsUUdnewn0S9PKoS2DP", calig);

        Gestor gestor = Gestor.getInstance();
        GestorUbicaciones gestorUbicacionesSpy = spy(gestor.getGestorUbicaciones());
        doReturn(ubicacionesMentira).when(gestorUbicacionesSpy).getUbicacionesFirebase();
        gestor.setGestorUbicaciones(gestorUbicacionesSpy);
        gestorUbicacionesSpy.generarUbicaciones();

        //WHEN
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasRecientes();
        List<Ubicacion> ubicacionesOrdenadasRecientes = gestor.getUbicacionesOrdenadas("recientemente");

        //THEN
        assertEquals(gestor.getAllUbicaciones(), ubicacionesMentira);
        assertNotNull(ubicacionesOrdenadasRecientes);
        assertEquals(ubicacionesOrdenadasRecientes.get(0), sagunto);
        assertEquals(ubicacionesOrdenadasRecientes.get(1), castellon);
        assertEquals(ubicacionesOrdenadasRecientes.get(2), calig);
        assertEquals(ubicacionesOrdenadasRecientes.get(3), valencia);

    }
}
