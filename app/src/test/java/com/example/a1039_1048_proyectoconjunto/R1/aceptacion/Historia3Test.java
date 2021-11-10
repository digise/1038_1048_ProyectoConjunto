package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;

public class Historia3Test {

    @Test
    public void validarToponimoExistente_APIOpenWeather_ubicacionValida(){
        //Given
        GestorServicios gestorServicios = new GestorServicios();
        String toponimo = "Castello";
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
        Ubicacion ubicacion = gestorServicios.getUbicacionByToponimo(toponimo);
        gestorUbicaciones.addUbicacion(ubicacion);

        //When
        Ubicacion ubicacionCastellon = gestorUbicaciones.getUbicacion("Castello");
        //@todo isValid() o getInfo()?
        String info = gestorServicios.getInfoOpenWeather();
        boolean valid = gestorServicios.isValidOpenWeather();

        //Then
        //@todo esta be validar la resposta dels metodes
        assertTrue(valid);
        assertFalse(info.isEmpty());
    }

    @Test
    public void validarToponimoNoExistente_APIOpenWeather_ubicacionNoValida(){
        //Given
        GestorServicios gestorServicios = new GestorServicios();
        String toponimo = "NoExiste";
        GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();
        Ubicacion ubicacion = gestorServicios.getUbicacionByToponimo(toponimo);
        gestorUbicaciones.addUbicacion(ubicacion);

        //When
        //@todo mock o algo para simular una ubicacion valida de geocoding pero no openweather
        Ubicacion ubicacionCastellon = gestorUbicaciones.getUbicacion("Castello");
        //@todo isValid() o getInfo()?
        String info = gestorServicios.getInfoOpenWeather();
        boolean valid = gestorServicios.isValidOpenWeather();

        //Then
        assertFalse(valid);
        assertTrue(info.isEmpty());
    }
}
