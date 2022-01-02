package com.example.a1039_1048_proyectoconjunto.servicios;

import com.example.a1039_1048_proyectoconjunto.adapter.CurrentsAdapter;
import com.example.a1039_1048_proyectoconjunto.adapter.OpenWeatherAdapter;

import java.util.HashMap;

public class ServicioCurrents implements Servicio{
    private final String url = "https://api.currentsapi.services/v1/search?";
    private final String apiKey = "O87PinCGKuQ3pk2qL9533nThHwM3dC67aLwNZOGoik5eUqF-";
    private boolean activo;

    //https://api.currentsapi.services/v1/search?keywords=castellon&language=ES&apiKey=O87PinCGKuQ3pk2qL9533nThHwM3dC67aLwNZOGoik5eUqF-


    public ServicioCurrents(){
        activo = true;
    }

    public HashMap<String, HashMap<String, String>> getInformacion(String toponimo) {
        if (activo) {
            String tempUrl = url + "keywords=" + toponimo + "&language=ES&apiKey=" + apiKey;
            CurrentsAdapter currentsAdapter = new CurrentsAdapter();
            return currentsAdapter.doRequest(tempUrl);
        }
        return null;
    }

    public void servicioActivo(boolean activar){
        activo = activar;
    }

    public boolean isActivo() {
        return activo;
    }
}
