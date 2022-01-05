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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Historia1_2Test {

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

    }

    @Test
    public void informacionTresUbicaciones_ordenReciente_noValido() {
        //GIVEN
        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>());

        gestor.getGestorUbicaciones().generarUbicaciones();
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasRecientes();

        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        Set<Ubicacion> todasUbicaciones = new HashSet<>(gestor.getAllUbicaciones().values());
        todasUbicaciones.forEach(ubicacion -> {
            ubicacion.activar();
            ubicacion.activarServicio("openweather", true);
        });

        //WHEN
        List<Ubicacion> todasUbicacionesRecientemente = gestor.getUbicacionesOrdenadas("recientemente");

        //THEN
        assertEquals(todasUbicacionesRecientemente.size(), 0);

    }
    @Test
    public void informacionTresUbicaciones_ordenReciente_valido(){
        //GIVEN
        HashMap<String, String> infoOpenWeatherMentira = new HashMap<>();

        infoOpenWeatherMentira.put("sensacionTermica", "20");
        infoOpenWeatherMentira.put("temperaturaMedia", "21");
        infoOpenWeatherMentira.put("humedad", "22");
        infoOpenWeatherMentira.put("presion", "23");

        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(infoOpenWeatherMentira);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);
        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);

        Ubicacion sagunto = new Ubicacion("sagunto", "spain", "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain", "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain", "39.50337", "-0.40466");

        castellon.setNumCreacion(0);
        sagunto.setNumCreacion(1);
        valencia.setNumCreacion(2);

        sagunto.activar();
        sagunto.activarServicio("openweather", true);
        castellon.activar();
        castellon.activarServicio("openweather", true);
        valencia.activar();
        valencia.activarServicio("openweather", true);

        Map<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv", castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS", valencia);

        when(mockConexionFirebaseUbicaciones.getCollection(anyString(), anyObject())).thenReturn(new HashMap<>(ubicacionesMentira));

        gestor.getGestorUbicaciones().generarUbicaciones();
        gestor.getGestorUbicaciones().generarUbicacionesOrdenadasRecientes();


        //WHEN
        List<Ubicacion> todasUbicacionesRecientemente = gestor.getUbicacionesOrdenadas("recientemente");

        //THEN
        assertEquals(todasUbicacionesRecientemente.size(), 3);
        assertEquals(todasUbicacionesRecientemente.get(0).getNumCreacion(), 0);
        assertEquals(todasUbicacionesRecientemente.get(1).getNumCreacion(), 1);
        assertEquals(todasUbicacionesRecientemente.get(2).getNumCreacion(), 2);

        assertEquals(todasUbicacionesRecientemente.get(0), castellon);
        assertEquals(todasUbicacionesRecientemente.get(1), sagunto);
        assertEquals(todasUbicacionesRecientemente.get(2), valencia);

        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesRecientemente.get(0)));
        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesRecientemente.get(1)));
        assertNotNull(gestor.getTiempoPorUbicacion(todasUbicacionesRecientemente.get(2)));
    }

}
