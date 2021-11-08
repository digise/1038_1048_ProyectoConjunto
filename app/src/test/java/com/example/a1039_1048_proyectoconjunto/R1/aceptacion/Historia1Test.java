package com.example.a1039_1048_proyectoconjunto.R1.aceptacion;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.example.a1039_1048_proyectoconjunto.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.ServicioGeocoding;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.Test;
import org.mockito.Mockito;

public class Historia1Test {

   @Test
   public void altaUbicacion_toponimoExistente_anyadir(){
      //Given
      ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
      GestorServicios gestorServicios = new GestorServicios(servicioGeocoding);
      String toponimo = "Castelló";
      GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

      //When
      servicioGeocoding = gestorServicios.getServicioGeocoding();
      Ubicacion ubicacion = servicioGeocoding.getUbicacionPorToponimo(toponimo);
      gestorUbicaciones.addUbicacion(ubicacion);


      //Then
      assertEquals(1, gestorUbicaciones.getListadoUbicaciones().size());
   }

   @Test
   public void altaUbicacion_toponimoNoExistente_anyadir(){
      //Given
      ServicioGeocoding servicioGeocoding = new ServicioGeocoding();
      GestorServicios gestorServicios = new GestorServicios(servicioGeocoding);
      GestorUbicaciones gestorUbicaciones = new GestorUbicaciones();

      //When
      servicioGeocoding = gestorServicios.getServicioGeocoding();
      Ubicacion ubicacionReal = servicioGeocoding.getUbicacionPorToponimo("NoExiste");
      gestorUbicaciones.addUbicacion(ubicacionReal);


      //Then
      assertEquals(0, gestorUbicaciones.getListadoUbicaciones().size());
   }
}
