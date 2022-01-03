package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;

public class Historia8Test {

    private static Gestor gestor;
    private static GestorUbicaciones gestorUbicacionesSpy;
    private static HashMap<String, Ubicacion> ubicacionesMentira;

    @BeforeAll
    public static void setUbicaciones() {
        gestor = Gestor.getInstance();
        gestorUbicacionesSpy = spy(Gestor.getInstance().getGestorUbicaciones());
    }

    @Test
    public void ordenarUbicaciones_listaUbicacionesVacia_noValido(){
        //GIVEN
        ubicacionesMentira = new HashMap<>();
        doReturn(ubicacionesMentira).when(gestorUbicacionesSpy).getCollectionFirebase();

        //WHEN
        //THEN
    }

    @Test
    public void ordenarUbicaciones_listaUbicacionesNoVacia_valido(){
        //GIVEN
        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        doReturn(ubicacionesMentira).when(gestorUbicacionesSpy).getCollectionFirebase();
        //WHEN

        //boolean reordenado = gestor.reordenarUbicacion(ubicacion, 2);

        //THEN
        //assertFalse(reordenado);
        assertNull(gestor.getAllUbicaciones());
    }
}
