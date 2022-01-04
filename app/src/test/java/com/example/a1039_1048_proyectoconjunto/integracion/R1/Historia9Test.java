package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia9Test {

    private static Gestor gestor;

    @BeforeAll
    public static void setSpy() {
        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        gestor = Gestor.getInstance();

        GestorUbicaciones gestorUbicaciones = spy(Gestor.getInstance().getGestorUbicaciones());

        doReturn(ubicacionesMentira).when(gestorUbicaciones).getUbicacionesFirebase();
    }

    @Test
    public void desactivarUbicacionActivada_valido() {
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.activarServicio("currents");

        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("currents", true);


        //WHEN
        castellon.desactivar();


        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }

    @Test
    public void desactivarUbicacionDesactivada_noValido(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.activarServicio("currents");

        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("currents", true);
        castellon.desactivar();


        //WHEN
        castellon.desactivar();


        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }
}
