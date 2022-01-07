package com.example.a1039_1048_proyectoconjunto.aceptacion.R2;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

public class Historia7_1Test {
    private Gestor gestor;

    @BeforeEach
    public void crearGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarTodaLaInformacionDeLaAplicacion();
    }

    @Test
    public void listarUbicaciones_ordenAlfabetico_disponibles_conSuInformacion(){
        //GIVEN
        Ubicacion castello = gestor.getUbicacionPorToponimo("castello");
        Ubicacion valencia = gestor.getUbicacionPorToponimo("valencia");
        Ubicacion alicante = gestor.getUbicacionPorToponimo("alicante");
        gestor.darAltaUbicacion(castello);
        gestor.darAltaUbicacion(valencia);
        gestor.darAltaUbicacion(alicante);
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        alicante.activar();
        alicante.activarServicio("openweather", true);
        castello.activar();
        castello.activarServicio("openweather", true);
        valencia.activar();
        valencia.activarServicio("openweather", true);

        //WHEN
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasAlfabeticamente();
        List<Ubicacion> todasUbicacionesAlfabeticamente = gestor.getUbicacionesOrdenadasAlfabeticamente();

        //THEN
        assertEquals(todasUbicacionesAlfabeticamente.get(0), alicante);
        assertEquals(todasUbicacionesAlfabeticamente.get(1), castello);
        assertEquals(todasUbicacionesAlfabeticamente.get(2), valencia);

        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesAlfabeticamente.get(0)));
        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesAlfabeticamente.get(1)));
        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesAlfabeticamente.get(2)));
    }

    @Test
    public void listaUbicaciones_ordenAlfabetico_noDisponibles() {
        //GIVEN
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.recuperarTodaLaInformacionDeLaAplicacion();

        //WHEN
        List<Ubicacion> todasUbicacionesAlfabeticamente = gestor.getUbicacionesOrdenadasAlfabeticamente();

        //THEN
        assertEquals(todasUbicacionesAlfabeticamente.size(), 0);

    }
}
