package com.example.a1039_1048_proyectoconjunto;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

public class Ubicacion implements Comparable<Ubicacion> {

    private String toponimo;
    private String latitud; //-90, 90
    private String longitud; //-180, 180
    private boolean activada = false;
    private String pais;
    private String alias;
    private boolean servicioOpenWeatherActivo;
    private boolean servicioCurrentsActivo;
    private String idDocumento = "";
    private boolean enListaTresUbicaciones;
    private int numCreacion;

    public Ubicacion() {
        this.toponimo = null;
        this.latitud = null;
        this.longitud = null;
        this.pais = null;
        this.alias = "";
        this.servicioOpenWeatherActivo = false;
        this.servicioCurrentsActivo = false;
        this.enListaTresUbicaciones = false;
        this.numCreacion = 0;
    }

    public Ubicacion(String toponimo) {
        this.toponimo = toponimo;
        this.latitud = null;
        this.longitud = null;
        this.pais = null;
        this.alias = "";
        this.enListaTresUbicaciones = false;
        this.numCreacion = 0;
    }

    public Ubicacion(String latitud, String longitud) {
        this.toponimo = null;
        this.pais = null;
        this.latitud = latitud;
        this.longitud = longitud;
        this.alias = "";
        this.enListaTresUbicaciones = false;
        this.numCreacion = 0;
    }

    public Ubicacion(String toponimo, String pais, String latitud, String longitud) {
        this.toponimo = toponimo;
        this.pais = pais;
        this.latitud = latitud;
        this.longitud = longitud;
        this.alias = "";
        this.enListaTresUbicaciones = false;
        this.numCreacion = 0;
    }

    public String getToponimo() {
        return this.toponimo;
    }

    public void setToponimo(String toponimo) {
        this.toponimo = toponimo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }


    public boolean activar() {
        activada = true;
        boolean s = updateUbicacionFirebase(this, this.idDocumento);
        if (!s)
            activada = false;

        return activada;
    }

    public boolean desactivar() {
        activada = false;
        boolean s = updateUbicacionFirebase(this, this.idDocumento);
        if (!s)
            activada = true;

        return activada;
    }

    public boolean isActivada() {
        return activada;
    }

    public boolean issetCoord() {
        return this.latitud == null && this.longitud == null;
    }

    public boolean issetToponimo() {
        return this.toponimo == null;
    }

    public String getAlias() {
        return alias;
    }

    public boolean setAlias(String alias) {
        if (alias.length() > 0 && alias.length() <= 20) {
            boolean s = updateUbicacionFirebase(this, this.idDocumento);
            if (s)
                this.alias = alias;
            return s;
        }
        return false;
    }

    public boolean isServicioActivo(String nombreServicio) {
        String servicioMinusculas = nombreServicio.toLowerCase();

        switch (servicioMinusculas) {
            case "openweather":
                return this.servicioOpenWeatherActivo;
            case "currents":
                return this.servicioCurrentsActivo;
            default:
                return false;
        }
    }

    public void activarServicio(String nombreServicio, boolean activar) {
        String servicioMinusculas = nombreServicio.toLowerCase();
        switch (servicioMinusculas) {
            case "openweather":
                if (Gestor.getInstance().getGestorServicios().getServicioOpenWeather() != null) {
                    this.servicioOpenWeatherActivo = activar;
                    updateUbicacionFirebase(this, this.idDocumento);
                }
                break;
            case "currents":
                if (Gestor.getInstance().getGestorServicios().getServicioCurrents() != null) {
                    this.servicioCurrentsActivo = activar;
                    updateUbicacionFirebase(this, this.idDocumento);
                }
                break;
            default:
                break;
        }
    }

    public void ponerEnListaTresUbicaciones(boolean activar) {
        this.enListaTresUbicaciones = activar;
    }

    public boolean isEnListaTresUbicaciones() {
        return enListaTresUbicaciones;
    }

    public int getNumCreacion() {
        return numCreacion;
    }

    public void setNumCreacion(int numCreacion) {
        this.numCreacion = numCreacion;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "toponimo='" + toponimo + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                ", activada=" + activada +
                ", pais='" + pais + '\'' +
                '}';
    }

    public boolean equals(Ubicacion ubicacion) {
        return ubicacion.longitud.equals(this.longitud) && ubicacion.latitud.equals(this.latitud);
    }

    @Override
    public int compareTo(Ubicacion o) {
        if (o.longitud.equals(this.longitud) && o.latitud.equals(this.latitud))
            return 0;
        return -1;
    }

    public boolean updateUbicacionFirebase(Ubicacion ubicacion, String idDocumento) {
        return Gestor.getInstance().getGestorUbicaciones().updateUbicacionFirebase(ubicacion, idDocumento);
    }

}
