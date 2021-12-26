package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.activities.GeocodingActivity;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
      Assert.assertEquals(ubicacionCastellon.getToponimo(), toponimo);
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