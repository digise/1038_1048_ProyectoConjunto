package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.activities.GeocodingActivity;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anadir(){
      // Given
      Gestor gestor = Gestor.getInstance();
      String toponimo = "Torreblanca";
      ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
      gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);


      // When
      Ubicacion ubicacion = gestor.getGestorServicios().darAltaUbicacionPorToponimo(toponimo);
      System.out.println(ubicacion.getToponimo());


      // Then
      //int nUbicaciones = gestor.getGestorUbicaciones().getListadoUbicaciones();
      Ubicacion ubicacion1 = gestor.getGestorUbicaciones().getUbicacionPorToponimo(toponimo);
      //assertEquals(1, nUbicaciones);
      assertEquals(ubicacion.getToponimo(), ubicacion1.getToponimo());
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
