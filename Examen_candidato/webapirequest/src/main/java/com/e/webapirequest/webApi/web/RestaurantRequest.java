package com.e.webapirequest.webApi.web;

import android.util.Log;

import com.e.webapirequest.webApi.dto.RestaurantDTO;
import com.e.webapirequest.webApi.parser.CollectionParser;
import com.e.webapirequest.webApi.parser.RestaurantParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRequest implements Callback<ResponseBody>, RestaurantParser.OnResponseParser {

    private OnResponseRestaurant mListener;



    public interface OnResponseRestaurant{
        void OnResponseRestaurant(RestaurantDTO data);
        void onResponseErrorServidor();
        void onResponseSinConexion();

        void onResponseSinDatos();
    }

    public void makeRequest (int red_id, OnResponseRestaurant listener){
        mListener = listener;
        WebApi.GetMethods getMethods = WebApi.getCliente().create(WebApi.GetMethods.class);
        Call<ResponseBody> request = getMethods.requestObtenerRestaurant(red_id);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.d("respuestaRestaurant", getClass().getName() + "" + response.code());
        switch (response.code()) {
            case 200:
                RestaurantParser parser = new RestaurantParser();
                parser.setListener(this);
                try {
                    parser.execute(response.body().string());
                } catch (IOException e) {
                    mListener.onResponseErrorServidor();
                }
                break;
            case 404:
                mListener.onResponseSinDatos();
            default:
                mListener.onResponseErrorServidor();
                break;
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (t.getCause().equals("TIMEOUT")){
            mListener.onResponseSinConexion();
        }else{
            mListener.onResponseErrorServidor();
        }
    }

    @Override
    public void onResponseComplete(RestaurantDTO restaurant) {
        if(restaurant != null ){
            mListener.OnResponseRestaurant(restaurant);
        }else {
            mListener.onResponseErrorServidor();
        }

    }


}
