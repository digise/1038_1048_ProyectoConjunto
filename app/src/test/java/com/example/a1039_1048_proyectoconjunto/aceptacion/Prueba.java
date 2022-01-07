package com.example.a1039_1048_proyectoconjunto.aceptacion;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Prueba {
    private Gestor gestor;

    @BeforeEach
    public void crear_gestor() {
        gestor = Gestor.getInstance();
        gestor.recuperarTodaLaInformacionDeLaAplicacion();
    }

    @Test
    public void altaUbicacion_toponimoExistente_anadir() {
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Calig"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Valencia"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Castellon"));
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo("Alicante"));
    }

}