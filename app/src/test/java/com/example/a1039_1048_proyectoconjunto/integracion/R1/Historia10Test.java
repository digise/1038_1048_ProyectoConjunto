package com.example.a1039_1048_proyectoconjunto.integracion.R1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Historia10Test {
    private static Gestor gestor;

    @BeforeAll
    public static void setConfiguracion() {
        Ubicacion sagunto = new Ubicacion("sagunto", "spain" , "39.69250", "-0.28686");
        Ubicacion castellon = new Ubicacion("castello", "spain" , "40.67830", "0.28421");
        Ubicacion valencia = new Ubicacion("valencia", "spain" , "39.50337", "-0.40466");
        Ubicacion calig = new Ubicacion("calig", "spain" , "40.47058", "0.36725");

        HashMap<String, Ubicacion> ubicacionesMentira = new HashMap<>();

        ubicacionesMentira.put("-MsT0rTUlBSR9yU3zdsx", sagunto);
        ubicacionesMentira.put("-MsT0s-GrW9neZulj0Xv" ,castellon);
        ubicacionesMentira.put("-MsT0srQkDHs540AArXS" ,valencia);
        ubicacionesMentira.put("-MsUUdnewn0S9PKoS2DP", calig);

        gestor = Gestor.getInstance();

        GestorUbicaciones gestorUbicaciones = spy(Gestor.getInstance().getGestorUbicaciones());
        doReturn(ubicacionesMentira).when(gestorUbicaciones).getUbicacionesFirebase();
        doReturn(true).when(gestorUbicaciones).removeDocument("ubicaciones", eq(anyString()));

        GeocodingAdapter geocodingAdapterMock = mock(GeocodingAdapter.class);
        gestor.getGestorServicios().getServicioGeocoding().setGeocodingAdapter(geocodingAdapterMock);
        when(geocodingAdapterMock.doRequest(anyString())).thenReturn(castellon);

    }

    @Test
    public void darBajaUbicacionExistente_ubicacion_valido(){
        //GIVEN
        String toponimo = "castello";
        gestor.darAltaUbicacion(gestor.getUbicacionPorToponimo(toponimo));

        //WHEN
        int numUbicacionesAntesBorrado = gestor.getAllUbicaciones().size();
        gestor.darBajaUbicacion(gestor.getUbicacionGuardada(toponimo));

        //THEN
        int numUbicacionesDespuesBorrado = gestor.getAllUbicaciones().size();
        assertEquals(numUbicacionesAntesBorrado, numUbicacionesDespuesBorrado + 1);
        assertNull(gestor.getUbicacionGuardada(toponimo));
    }

    @Test
    public void darBajaUbicacionNoExistente_ubicacion_noValido(){
        //GIVEN
        String toponimo = "castello";


        //WHEN
        int numUbicacionesAntesBorrado = gestor.getAllUbicaciones().size();
        gestor.darBajaUbicacion(gestor.getUbicacionGuardada(toponimo));


        //THEN
        int numUbicacionesDespuesBorrado = gestor.getAllUbicaciones().size();
        assertEquals(numUbicacionesAntesBorrado, numUbicacionesDespuesBorrado);
        assertNull(gestor.getUbicacionGuardada(toponimo));
    }
}
