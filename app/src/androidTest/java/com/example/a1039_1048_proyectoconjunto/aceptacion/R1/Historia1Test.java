package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;


import static org.junit.Assert.assertEquals;

import com.example.a1039_1048_proyectoconjunto.Ubicacion;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioGeocoding;


import org.junit.Test;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anadir(){
      // Given
      GestorServicios gestorServicios = new GestorServicios();
      gestorServicios.setServicioGeocoding(new ServicioGeocoding());
      String toponimo = "Castellon";


      // When
      Ubicacion ubicacionCastellon = gestorServicios.getServicioGeoCoding().getInformacion("toponimo", toponimo);

      System.out.println(ubicacionCastellon);
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
