package com.e.examen_candidato.Collection.DetalleLugar;

import com.e.webapirequest.webApi.dto.RestaurantDTO;
import com.e.webapirequest.webApi.web.RestaurantRequest;

public class DetalleInteractor {

    private OnRestaurantInteractorListener mListener;

    public interface  OnRestaurantInteractorListener{

        void OnInteractorSuccessRestaurant(RestaurantDTO data);

        void OnInteractorServerError();

        void OnInteractorRequestFailture();

        void OnInteractorSinDatos();
    }

    public void getRestaurant (int id_restaurant,OnRestaurantInteractorListener listener){
        mListener = listener;
        RestaurantRequest request = new RestaurantRequest();
        request.makeRequest(id_restaurant, new RestaurantRequest.OnResponseRestaurant() {
            @Override
            public void OnResponseRestaurant(RestaurantDTO data) {
                mListener.OnInteractorSuccessRestaurant(data);
            }

            @Override
            public void onResponseErrorServidor() {
                mListener.OnInteractorServerError();
            }

            @Override
            public void onResponseSinConexion() {
                mListener.OnInteractorRequestFailture();
            }

            @Override
            public void onResponseSinDatos() {
                mListener.OnInteractorSinDatos();
            }
        });
    }

}
