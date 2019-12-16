package com.e.webapirequest.webApi.dto;

import org.json.JSONObject;

import java.io.Serializable;

public class LugarDTO implements Serializable {

    private static final String ADDRESS_KEY = "address";
    private static final String LOCALITY_KEY = "locality";
    private static final String CITY_KEY = "city";
    private static final String LATITUDE_KEY = "latitude";
    private static final String LONGITUDE_KEY = "longitude";
    private static final String LOCALITYVERBOSE_KEY = "locality_verbose";

    private String direccion;
    private String localidad;
    private String ciudad;
    private String latitud;
    private String longitud;
    private String localityVerbose;

    public  LugarDTO(JSONObject data){
        direccion = data.optString(ADDRESS_KEY);
        localidad = data.optString(LOCALITY_KEY);
        ciudad = data.optString(CITY_KEY);
        latitud = data.optString(LATITUDE_KEY);
        longitud = data.optString(LONGITUDE_KEY);
        localityVerbose = data.optString(LOCALITYVERBOSE_KEY);
    }


    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public String getLocalityVerbose() {
        return localityVerbose;
    }

}
