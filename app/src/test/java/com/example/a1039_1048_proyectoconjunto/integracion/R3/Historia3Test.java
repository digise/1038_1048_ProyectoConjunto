package com.example.a1039_1048_proyectoconjunto.integracion.R3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.Servicio;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioCurrents;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Historia3Test {
    private static Gestor gestor;
    private static ConexionFirebase mockConexionFirebaseServicios;

    private static OpenWeatherAdapter mockOpenWeatherAdapter;
    private static CurrentsAdapter mockCurrentsAdapter;

    private static String descripcionCurrents;
    private static String descripcionOpenWeather;

    @BeforeEach
    public void setGestor() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        gestor.getGestorServicios().setConexionFirebase(mockConexionFirebaseServicios);

    }

    @BeforeAll
    public static void setUp() {
        gestor = Gestor.getInstance();
        gestor.borrarGestor();
        gestor = Gestor.getInstance();

        mockConexionFirebaseServicios = mock(ConexionFirebase.class);
        when(mockConexionFirebaseServicios.updateDocument(anyString(), anyObject(), anyString())).thenReturn(true);
        when(mockConexionFirebaseServicios.removeDocument(anyString(), anyString())).thenReturn(false);
        when(mockConexionFirebaseServicios.createDocument(anyString(), anyObject(), anyString())).thenReturn("HOLO");

        descripcionCurrents = "En funcionamiento desde 2018, tenemos un índice de más de 24 millones de noticias, artículos y contenido de foros.\n" +
                "\n" +
                "Actualmente, nuestro servicio ayuda a las empresas a impulsar su motor de servicio de análisis interno, enriquecer la participación de la comunidad y proporcionar información sobre las tendencias financieras y de marketing. " +
                "\n" +
                "Características de la api gratuita (la que hemos utilizado):\n" +
                "- 600 solicitudes diarias\n" +
                "- Acceso a 6 meses de archivos históricos";
        descripcionOpenWeather = "OpenWeather es un equipo de expertos en TI y científicos de datos que ha estado practicando la ciencia de datos meteorológicos profundos desde 2014. Para cada punto del mundo, OpenWeather proporciona datos meteorológicos históricos, actuales y pronosticados a través de API a la velocidad de la luz. Sede en Londres, Reino Unido.\n" +
                "Caracterisitcas de la api gratuita (la que hemos utilizado):\n" +
                "- 60 llamadas/minuto\n" +
                "- Clima actual, pronóstico en minutos para 1 hora, pronóstico por hora para 48 horas, pronóstico diario para 7 días, alertas meteorológicas globales y datos históricos\\n\" +\n" +
                "            \"para 5 días anteriores para cualquier ubicación";
    }

    @Test
    public void descripcionServiciosAPI_listadoConServicios_valido() {
        //GIVEN
        mockCurrentsAdapter = mock(CurrentsAdapter.class);
        mockOpenWeatherAdapter = mock(OpenWeatherAdapter.class);

        when(mockCurrentsAdapter.doRequest(anyString())).thenReturn(new HashMap<>());
        when(mockOpenWeatherAdapter.doRequest(anyString())).thenReturn(new HashMap<>());

        ServicioCurrents servicioCurrents = new ServicioCurrents();
        servicioCurrents.servicioActivo(true);
        servicioCurrents.setCurrentsAdapter(mockCurrentsAdapter);

        ServicioOpenWeather servicioOpenWeather = new ServicioOpenWeather();
        servicioOpenWeather.servicioActivo(true);
        servicioOpenWeather.setOpenWeatherAdapter(mockOpenWeatherAdapter);

        gestor.getGestorServicios().setServicioOpenWeather(servicioOpenWeather);
        gestor.getGestorServicios().setServicioCurrents(servicioCurrents);


        Map<String, Servicio> servicios = gestor.getAllServicios();

        //WHEN
        ServicioCurrents servicioCurrentsGestor = (ServicioCurrents) servicios.get("currents");

        //THEN
        assertNotNull(servicioCurrentsGestor);
        assertEquals(descripcionCurrents, servicioCurrentsGestor.getDescripcion());

    }
}
