package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;


import org.junit.jupiter.api.Test;

import java.util.Locale;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anadir(){
      // Given
      Gestor gestor = Gestor.getInstance();
      String toponimo = "castellon";
      ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
      gestor.getGestorServicios().setServicioGeocoding(servicioGeocoding);


      // When
      Ubicacion ubicacion = gestor.getGestorServicios().darAltaUbicacionPorToponimo(toponimo);


      // Then
      //int nUbicaciones = gestor.getGestorUbicaciones().getListadoUbicaciones();
      //assertEquals(1, nUbicaciones);
      String nombre = ubicacion.getToponimo();
      assertEquals("castello de la plana", nombre.toLowerCase());
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
