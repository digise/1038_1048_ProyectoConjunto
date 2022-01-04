package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.firebase.ConexionFirebase;

import java.util.HashMap;

public class ServicioCurrents implements Servicio{
    private final String url = "https://api.currentsapi.services/v1/search?";
    private final String apiKey = "O87PinCGKuQ3pk2qL9533nThHwM3dC67aLwNZOGoik5eUqF-";
    private boolean activo;
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
        return ConexionFirebase.updateDocument("servicios", servicio, idDocumento);
    }
}
