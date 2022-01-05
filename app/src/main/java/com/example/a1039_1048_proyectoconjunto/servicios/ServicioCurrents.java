package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;
import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;

import java.util.HashMap;

public class ServicioCurrents implements Servicio{
    private final String url = "https://api.currentsapi.services/v1/search?";
    private final String apiKey = "O87PinCGKuQ3pk2qL9533nThHwM3dC67aLwNZOGoik5eUqF-";
    private boolean activo;
    private final String descripcion = "En funcionamiento desde 2018, tenemos un índice de más de 24 millones de noticias, artículos y contenido de foros.\n" +
            "\n" +
            "Actualmente, nuestro servicio ayuda a las empresas a impulsar su motor de servicio de análisis interno, enriquecer la participación de la comunidad y proporcionar información sobre las tendencias financieras y de marketing. " +
            "\n" +
            "Características de la api gratuita (la que hemos utilizado):\n" +
            "- 600 solicitudes diarias\n" +
            "- Acceso a 6 meses de archivos históricos";
    private CurrentsAdapter currentsAdapter;

    //https://api.currentsapi.services/v1/search?keywords=castellon&language=ES&apiKey=O87PinCGKuQ3pk2qL9533nThHwM3dC67aLwNZOGoik5eUqF-


    public ServicioCurrents(){
        activo = true;
        currentsAdapter = new CurrentsAdapter();
    }

    public CurrentsAdapter getCurrentsAdapter() {
        return currentsAdapter;
    }

    public void setCurrentsAdapter(CurrentsAdapter currentsAdapter) {
        this.currentsAdapter = currentsAdapter;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public HashMap<String, HashMap<String, String>> getInformacion(String toponimo) {
        if (activo) {
            String tempUrl = url + "language=es&keywords=" + toponimo + "&apiKey=" + apiKey;
            return currentsAdapter.doRequest(tempUrl);
        }
        return null;
    }

    public void servicioActivo(boolean activar){
        activo = activar;
        boolean s = updateServicioFirebase(this, "currents");
        if (!s)
            activo = !activar;

    }

    public boolean isActivo() {
        return activo;
    }

    @Override
    public String toString() {
        return "ServicioCurrents{" +
                "activo=" + activo +
                '}';
    }

    //Firebase
    public boolean updateServicioFirebase(ServicioCurrents servicio, String idDocumento){
        return Gestor.getInstance().getGestorServicios().updateServicioFirebase(servicio, idDocumento);
    }
}
