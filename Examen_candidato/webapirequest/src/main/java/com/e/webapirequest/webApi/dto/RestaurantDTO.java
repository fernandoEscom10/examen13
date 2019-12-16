package com.e.webapirequest.webApi.dto;

import org.json.JSONObject;

import java.io.Serializable;

public class RestaurantDTO implements Serializable {

    private static final String NOMBRE_KEY = "name";
    private static final String LOCATION_KEY = "location";
    private static final String COCINA_KEY = "cuisines";
    private static final String TIMING = "timings";


    private String nombre;
    private LugarDTO lugar;
    private String cocina;
    private String horario;

    public RestaurantDTO(JSONObject nodo){
        nombre = nodo.optString(NOMBRE_KEY);
        lugar = new LugarDTO(nodo.optJSONObject(LOCATION_KEY));
        cocina = nodo.optString(COCINA_KEY);
        horario = nodo.optString(TIMING);

    }

    public String getNombre() {
        return nombre;
    }

    public LugarDTO getLugar() {
        return lugar;
    }

    public String getCocina() {
        return cocina;
    }

    public String getHorario() {
        return horario;
    }



}
