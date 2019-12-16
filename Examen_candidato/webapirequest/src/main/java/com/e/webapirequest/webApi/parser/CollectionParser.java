package com.e.webapirequest.webApi.parser;

import android.os.AsyncTask;

import com.e.webapirequest.webApi.dto.CollectionDATA;
import com.e.webapirequest.webApi.dto.CollectionDTO;
import com.e.webapirequest.webApi.web.CollectionsRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CollectionParser extends AsyncTask<String, Void, ArrayList<CollectionDATA>> {
    private static final String ARRAY_COLLECTION_KEY = "collections";

    private OnResponseParser listener;

    public void setListener(CollectionsRequest collectionsRequest) {
        this.listener = collectionsRequest;
    }

    @Override
    protected ArrayList<CollectionDATA> doInBackground(String... json) {
        JSONObject nodoPrincipal;
        try {
            nodoPrincipal = new JSONObject(json[0]);
        } catch (JSONException e) {
            nodoPrincipal = new JSONObject();
        }
        ArrayList<CollectionDATA> collectionDetails = new ArrayList<>();
        JSONArray arrayJSON = nodoPrincipal.optJSONArray(ARRAY_COLLECTION_KEY);

        for (int i = 0; i < arrayJSON.length(); i++){
            //CollectionDTO collection = new CollectionDTO(arrayJSON.optJSONObject(i));
            CollectionDATA collection = new CollectionDATA(arrayJSON.optJSONObject(i));
            collectionDetails.add(collection);
        }
        return collectionDetails;
    }

    @Override
    protected void onPostExecute(ArrayList<CollectionDATA> listado){
        super.onPostExecute(listado);
            listener.onResponseComplete(listado);
    }

    public interface OnResponseParser {
        void onResponseComplete(ArrayList<CollectionDATA> listado);
    }
}
