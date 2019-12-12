package com.e.webapirequest.webApi.parser;

import android.os.AsyncTask;

import com.e.webapirequest.webApi.dto.CollectionDTO;
import com.e.webapirequest.webApi.web.CollectionsRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectionParser extends AsyncTask<String, Void, ArrayList<CollectionDTO>> {
    private static final String ARRAY_COLLECTION_KEY = "collections";

    private OnResponseParser listener;

    public void setListener(CollectionsRequest collectionsRequest) {
        this.listener = collectionsRequest;
    }

    @Override
    protected ArrayList<CollectionDTO> doInBackground(String... json) {
        JSONObject nodoPrincipal = null;
        try {
            nodoPrincipal = new JSONObject(json[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<CollectionDTO> collectionDetails = new ArrayList<>();

        JSONArray arrayJSON = nodoPrincipal.optJSONArray(ARRAY_COLLECTION_KEY);

        for (int i = 0; i < arrayJSON.length(); i++){
            CollectionDTO collection = new CollectionDTO(arrayJSON.optJSONObject(i));
            collectionDetails.add(collection);
        }
        return collectionDetails;
    }

    @Override
    protected void onPostExecute(ArrayList<CollectionDTO> listado){
        super.onPostExecute(listado);
        listener.onResponseComplete(listado);
    }

    public interface OnResponseParser {
        void onResponseComplete(ArrayList<CollectionDTO> listado);
    }
}
