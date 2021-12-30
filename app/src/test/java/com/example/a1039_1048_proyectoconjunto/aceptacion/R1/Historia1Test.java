package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import okhttp3.Response;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anadir(){
      // Given
      Gestor gestor = Gestor.getInstance();
      String toponimo = "sagunto";
      ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
      gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

      int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


      // When
      Ubicacion ubicacion = gestor.darAltaUbicacionPorToponimo(toponimo);
      boolean dadoAlta = gestor.getGestorUbicaciones().addUbicacion(ubicacion);
      int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


      // Then
      assertTrue(dadoAlta);
      assertEquals(nUbicacionesAntesDeInsertar + 1, nUbicacionesAlInsertar);
   }

   @Test
   public void altaUbicacion_toponimoNoExistente_anadir(){
      /*
      GeocodingAdapter geocodingAdapter = new GeocodingAdapter();
      geocodingAdapter.doRequest("http://geocode.xyz/no%20Existe?json=1&auth=57673066339488579050x115589", true);
      Gestor gestor = Gestor.getInstance();
      String toponimo = "no Existe";
      ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
      gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);

      int nUbicacionesAntesDeInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


      // When
      Ubicacion ubicacion = gestor.getGestorServicios().darAltaUbicacionPorToponimo(toponimo);
      boolean dadoAlta = gestor.getGestorUbicaciones().addUbicacion(ubicacion);
      int nUbicacionesAlInsertar = gestor.getGestorUbicaciones().getListadoUbicaciones().size();


      // Then
      assertFalse(dadoAlta);
      assertEquals(nUbicacionesAntesDeInsertar, nUbicacionesAlInsertar);*/


   }
}
