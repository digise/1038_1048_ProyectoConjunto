package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;

public class Historia2_2Test {

    private static Gestor gestor;
    private static OpenWeatherAdapter openWeatherAdapterMock;
    private static CurrentsAdapter currentsAdapterMock;

    @BeforeAll
    public static void crearGestor(){
        gestor = Gestor.getInstance();

        OpenWeatherAdapter openWeatherAdapterMock = mock(OpenWeatherAdapter.class);
        CurrentsAdapter currentsAdapterMock = mock(CurrentsAdapter.class);

        when(openWeatherAdapterMock.doRequest(anyString())).thenReturn(new HashMap<>());
        when(currentsAdapterMock.doRequest(anyString())).thenReturn(new HashMap<>());

    }

    @Test
    public void activarServiciosIndependientes_valido(){
        //GIVEN
        gestor.getGestorServicios().setServicioCurrents(new ServicioCurrents());
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());
        gestor.darAltaUbicacion(new Ubicacion("castello", "spain" , "40.67830", "0.28421"));

        Ubicacion castellon = gestor.getUbicacionGuardada("castello");

        castellon.activarServicio("openweather", false);
        castellon.activarServicio("currents", false);
        castellon.activar();

        //WHEN
        castellon.activarServicio("openweather", true);

        //THEN
        assertTrue(castellon.isServicioActivo("openweather"));
        assertFalse(castellon.isServicioActivo("currents"));

        assertNotNull(gestor.getTiempoPorUbicacion(castellon));
        assertNull(gestor.getNoticiasPorUbicacion(castellon));

    }
}
