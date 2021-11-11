package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;

public class Historia3Test {

    @Test
    public void validarToponimoExistente_APIOpenWeather_ubicacionValida(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        String toponimo = "Castello";
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
        Ubicacion ubicacion = gestorServicios.getUbicacionByToponimo(toponimo);
        gestorUbicaciones.addUbicacion(ubicacion);

        //When
        Ubicacion ubicacionCastellon = gestorUbicaciones.getUbicacion("Castello");
        String info = gestorServicios.getInfoOpenWeather(ubicacionCastellon);

        //Then
        assertFalse(info.isEmpty() || info == null);
    }

    @Test
    public void validarToponimoNoExistente_APIOpenWeather_ubicacionNoValida(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        String toponimo = "NoExiste";
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
        Ubicacion ubicacion = gestorServicios.getUbicacionByToponimo(toponimo);
        gestorUbicaciones.addUbicacion(ubicacion);

        //When
        Ubicacion ubicacionCastellon = gestorUbicaciones.getUbicacion("Castello");
        String info = gestorServicios.getInfoOpenWeather(ubicacionCastellon);

        //Then
        assertTrue(info.isEmpty());
    }
}
