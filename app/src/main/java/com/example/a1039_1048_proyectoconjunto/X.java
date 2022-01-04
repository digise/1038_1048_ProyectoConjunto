package com.example.a1039_1048_proyectoconjunto;

import java.util.HashSet;
import java.util.Set;

public class X {
    public static void main(String[] args) {
        Set<Ubicacion> ubicaciones = new HashSet<>();

        Ubicacion ubicacion1 = new Ubicacion("castellon", "España", "13", "13");
        ubicaciones.add(ubicacion1);
        Ubicacion ubicacion2 = new Ubicacion("sddx", "España", "13", "13");
        System.out.println(ubicacion1.compareTo(ubicacion2));
        System.out.println(ubicaciones.contains(ubicacion2));
    }
}
