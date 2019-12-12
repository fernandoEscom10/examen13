package com.e.webapirequest.webApi.web;


import android.util.Log;

import com.e.webapirequest.webApi.dto.CollectionDTO;
import com.e.webapirequest.webApi.parser.CollectionParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionsRequest implements Callback<ResponseBody>, CollectionParser.OnResponseParser {

    private OnResponseCollection mListener;


    public interface OnResponseCollection{
        void onResponseCollection(ArrayList<CollectionDTO> listado);
        void onResponseErrorServidor();
        void onResponseErrorConexion();
        void onResponseTiempoEsperaAgotado();
        void onResponseSinConexion();
    }

    public void makeRequest(Double lat, Double lon, int cuenta, OnResponseCollection listener){
        mListener = listener;
        WebApi.GetMethods getmethods = WebApi.getCliente().create(WebApi.GetMethods.class);
        Call<ResponseBody> request = getmethods.requestObtenerLugares(856, cuenta);
        Log.d("CollectionR", getClass().getName()+ "" +getmethods.requestObtenerLugares(856,cuenta).toString());
        request.enqueue(this);
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.d("respuesta1", getClass().getName()+""+response.code());
        switch (response.code()){
            case 200:
                CollectionParser parser = new CollectionParser();
                parser.setListener(this);
                try {
                    parser.execute(response.body().string());
                }catch (IOException e){
                    mListener.onResponseErrorServidor();
                }
                break;
                default:
                    mListener.onResponseErrorServidor();
                break;

        }

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        if (t.getCause().equals("TIMEOUT")){
            mListener.onResponseTiempoEsperaAgotado();
        }else{
            mListener.onResponseErrorConexion();
        }
    }


    @Override
    public void onResponseComplete(ArrayList<CollectionDTO> listado) {
        if(listado != null && !listado.isEmpty()){

        }

    }
}
