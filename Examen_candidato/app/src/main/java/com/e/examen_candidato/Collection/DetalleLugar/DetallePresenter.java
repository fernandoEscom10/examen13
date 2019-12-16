package com.e.examen_candidato.Collection.DetalleLugar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.e.webapirequest.webApi.dto.RestaurantDTO;

public class DetallePresenter implements DetalleInteractor.OnRestaurantInteractorListener{

    private DetalleView mView;
    private DetalleInteractor mInteractor;

    public DetallePresenter(DetalleView view, DetalleInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }

    public void getRestaurant(int res_id){
        if(mView != null){
            mView.onShowLoader();
        }
        mInteractor.getRestaurant(res_id, this);
    }

    @Override
    public void OnInteractorSuccessRestaurant(RestaurantDTO data) {
        mView.RestaurantSuccess(data);
    }

    @Override
    public void OnInteractorServerError() {
        Log.d("ErrorCollection", "error");

    }

    @Override
    public void OnInteractorRequestFailture() {
        Log.d("ErrorCollection", "error");
    }

    @Override
    public void OnInteractorSinDatos() {
        mView.onHideLoader();
        mView.mostrarMensaje("Sin datos en el servidor");
    }
}
