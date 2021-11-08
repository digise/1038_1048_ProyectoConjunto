package com.example.a1039_1048_proyectoconjunto;

import java.util.HashSet;
import java.util.Set;

public class GestorServicios {

    private HashSet<Servicio> servicios;
    private ServicioGeocoding servicioGeocoding;

    public GestorServicios(ServicioGeocoding servicioGeocoding) {
        this.servicios = new HashSet<>();
        this.servicioGeocoding = new ServicioGeocoding();
    }

    public ServicioGeocoding getServicioGeocoding() {
        return servicioGeocoding;
    }

    public boolean addServicio(Servicio servicio){
        return false;
    }
}
