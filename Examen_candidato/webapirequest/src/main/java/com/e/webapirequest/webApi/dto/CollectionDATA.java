package com.e.webapirequest.webApi.dto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class CollectionDATA implements Serializable {

    private static final String COLLECTION_KEY = "collection";

    private ArrayList<CollectionDTO> mListadoCollection;

    public CollectionDATA (JSONObject data){

        JSONObject nodointerno = data;

        mListadoCollection = new ArrayList<>();

        JSONObject arrayCollection = nodointerno.optJSONObject(COLLECTION_KEY);

        if(arrayCollection != null) {

                mListadoCollection.add(new CollectionDTO(arrayCollection));

        }
    }

    public ArrayList<CollectionDTO> getmListadoCollection(){return mListadoCollection;}
}
