package com.e.webapirequest.webApi.parser;

import android.os.AsyncTask;

import com.e.webapirequest.webApi.dto.RestaurantDTO;
import com.e.webapirequest.webApi.web.RestaurantRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RestaurantParser extends AsyncTask<String, Void, RestaurantDTO> {



    private OnResponseParser listener;


    public void setListener(RestaurantRequest restaurantRequest) {
        this.listener = restaurantRequest;
    }

    @Override
    protected RestaurantDTO doInBackground(String... strings) {
        JSONObject nodoPrincipal;
        try {
            nodoPrincipal = new JSONObject(strings[0]);
        }catch (JSONException e){
            nodoPrincipal = new JSONObject();
        }

        RestaurantDTO retaurant = null;
        retaurant = new RestaurantDTO(nodoPrincipal);
        return retaurant;
    }

    @Override
    protected void onPostExecute(RestaurantDTO restaurantDTO) {
        super.onPostExecute(restaurantDTO);
        listener.onResponseComplete(restaurantDTO);
    }

    public interface OnResponseParser {
        void onResponseComplete(RestaurantDTO restaurant);
    }


}
