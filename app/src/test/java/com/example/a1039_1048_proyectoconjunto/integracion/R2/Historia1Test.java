package com.example.a1039_1048_proyectoconjunto.integracion.R2;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;

public class Historia1Test {

    private static Gestor gestor;
    private static HashMap<String, Ubicacion> ubicacionesMentira;

    @BeforeAll
    public static void setUbicaciones() {
        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");

        ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);

        gestor = Gestor.getInstance();

        GestorUbicaciones gestorUbicaciones = spy(Gestor.getInstance().getGestorUbicaciones());
        doReturn(ubicacionesMentira).when(gestorUbicaciones).getUbicacionesFirebase();
    }

    @BeforeAll
    public static void setInfoOpenWeatherMentira(){
        gestor.getGestorServicios().setServicioOpenWeather(new ServicioOpenWeather());

        OpenWeatherAdapter openWeatherAdapterMock = mock(OpenWeatherAdapter.class);
        gestor.getGestorServicios().getServicioOpenWeather().setOpenWeatherAdapter(openWeatherAdapterMock);

        HashMap<String, String> infoOpenWeatherMentira = new HashMap<>();

        infoOpenWeatherMentira.put("sensacionTermica", "20");
        infoOpenWeatherMentira.put("temperaturaMedia", "21");
        infoOpenWeatherMentira.put("humedad", "22");
        infoOpenWeatherMentira.put("presion", "23");

        when(openWeatherAdapterMock.doRequest(anyString())).thenReturn(infoOpenWeatherMentira);
    }

    @Test
    public void informacionTresUbicaciones_ubicacionesActivas_valido(){
        //GIVEN
        //HashMap<String, String> tresUbicaciones = gestor.getTresUbicaciones();


        //WHEN



        //THEN


    }

}
