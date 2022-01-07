package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Historia9Test {

    private Gestor gestor;

    @BeforeEach
    public void anadirUbicacionParaHacerPrueba(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        gestor.darAltaUbicacion(castello);
    }

    @Test
    public void desactivarUbicacionActivada_valido(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("currents", true);
        castellon.activar();


        //WHEN
        castellon.desactivar();

        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }

    @Test
    public void desactivarUbicacionDesactivada_noValido(){
        //GIVEN
        Ubicacion castellon = gestor.getUbicacionGuardada("castello");
        castellon.activarServicio("currents", true);


        //WHEN
        castellon.desactivar();

        //THEN
        assertNull(gestor.getNoticiasPorUbicacion(castellon));
        assertFalse(castellon.isActivada());
    }

}
