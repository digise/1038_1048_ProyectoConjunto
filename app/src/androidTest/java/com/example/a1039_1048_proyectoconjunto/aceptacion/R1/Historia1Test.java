package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import android.view.View;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.activities.GeocodingActivity;
import com.example.a1039_1048_proyectoconjunto.adapter.GeocodingAdapter;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;


import org.junit.Test;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anadir(){
      // Given
      String toponimo = "Castellon";

      GeocodingActivity geocodingActivity = new GeocodingActivity();
      geocodingActivity.getUbicacionPorNombre(toponimo);

      Gestor gestor = new Gestor();
      gestor.getGestorServicios().setServicioGeocoding(new ServicioGeocoding());


      // When
      ServicioGeocoding sgc = gestor.getGestorServicios().getServicioGeocoding();
      Ubicacion ubicacionCastellon = sgc.getInformacionPorToponimo(toponimo);

      // Then
      assertEquals(ubicacionCastellon.getToponimo(), toponimo);
   }

   @Test
   public void altaUbicacion_toponimoNoExistente_anadir(){
      /*
      // Given
      GestorServicios gestorServicios = GestorServicios.getInstance();
      gestorServicios.setServicioGeocoding(new ServicioGeocoding());

      GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

      Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

      String toponimo = "NoExiste";

      // When
      boolean dadoAlta = gestor.darAltaToponimo(toponimo);


      // Then
      assertFalse(dadoAlta);
      int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
      assertEquals(0, nUbicaciones);

       */
   }
}
