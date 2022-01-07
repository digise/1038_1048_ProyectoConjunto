package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Historia7_1Test {
    private static Gestor gestor;

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static ConexionFirebase mockConexionFirebaseUbicaciones;
    private static ConexionFirebase mockConexionFirebaseServicios;

    @BeforeEach
    public void setGestor(){
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorUbicaciones().setConexionFirebase(mockConexionFirebaseUbicaciones);
        gestor.getGestorUbicaciones().generarUbicaciones();

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);
        gestor.getGestorServicios().recuperarInformacionServicios();
    }

    @BeforeAll
    public static void setUbicaciones_setOpenWeatherMock() {
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);
        mockConexionFirebaseUbicaciones = mock(ConexionFirebase.class);
        mockConexionFirebaseServicios = mock(ConexionFirebase.class);

        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        when(mockConexionFirebaseUbicaciones.removeDocument(anyString(), anyString())).thenReturn(true);
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);

        HashMap<String, String> infoOpenWeatherMentira = new HashMap<>();

        infoOpenWeatherMentira.put("sensacionTermica", "20");
        infoOpenWeatherMentira.put("temperaturaMedia", "21");
        infoOpenWeatherMentira.put("humedad", "22");
        infoOpenWeatherMentira.put("presion", "23");

        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(infoOpenWeatherMentira);
        when(mockConexionFirebaseUbicaciones.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
    }

    @Test
    public void listarUbicaciones_ordenAlfabetico_disponibles_conSuInformacion(){
        //GIVEN
        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        Ubicacion alicante = new Ubicacion("alicante", "spain", "38.3451700", " -0.4814900");
        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        alicante.activar();
        alicante.activarServicio("openweather", true);
        castellon.activar();
        castellon.activarServicio("openweather", true);
        valencia.activar();
        valencia.activarServicio("openweather", true);

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", alicante);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));

        gestor.getGestorUbicaciones().generarUbicaciones();
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasAlfabeticamente();

        //WHEN
        List<Ubicacion> todasUbicacionesAlfabeticamente = gestor.getUbicacionesOrdenadasAlfabeticamente();

        //THEN
        assertEquals(todasUbicacionesAlfabeticamente.size(), 3);

        assertEquals(todasUbicacionesAlfabeticamente.get(0), alicante);
        assertEquals(todasUbicacionesAlfabeticamente.get(1), castellon);
        assertEquals(todasUbicacionesAlfabeticamente.get(2), valencia);

        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesAlfabeticamente.get(0)));
        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesAlfabeticamente.get(1)));
        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesAlfabeticamente.get(2)));
    }

    @Test
    public void listaUbicaciones_ordenAlfabetico_noDisponibles() {
        //GIVEN
        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>());

        gestor.getGestorUbicaciones().generarUbicaciones();
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasAlfabeticamente();
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);


        //WHEN
        List<Ubicacion> todasUbicacionesAlfabeticamente = gestor.getUbicacionesOrdenadasAlfabeticamente();

        //THEN
        assertEquals(0, todasUbicacionesAlfabeticamente.size());

    }

}
