package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.example.a1039_1048_proyectoconjunto.Gestor;
import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;


import org.junit.jupiter.api.Test;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anadir(){
      // Given
      GestorServicios gestorServicios = GestorServicios.getInstance();
      gestorServicios.setServicioGeocoding(new ServicioGeocoding());

      GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

      Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

      String toponimo = "Castello";


      // When
      boolean dadoAlta = gestor.darAltaToponimo(toponimo);


      // Then
      assertTrue(dadoAlta);
      int nUbicaciones = gestorUbicaciones.getListadoUbicaciones().size();
      String toponimoUbicacion = gestorUbicaciones.getUbicacion(toponimo).getToponimo();
      assertEquals(1, nUbicaciones);
      assertEquals("Castello", toponimoUbicacion);
   }

   @Test
   public void altaUbicacion_toponimoNoExistente_anadir(){
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
   }
}
