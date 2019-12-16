package com.e.examen_candidato.Collection;

import android.util.Log;

import com.e.webapirequest.webApi.dto.CollectionDATA;

import java.util.ArrayList;

public class CollectionPresenter implements CollectionInteractor.OnCollectionInteractorListener {

    private CollectionView mView;
    private CollectionInteractor mInteractor;

    public CollectionPresenter (CollectionView view, CollectionInteractor interactor){
        mView = view;
        mInteractor = interactor;
    }

    public void getCollection(){
        if (mView != null){
            mView.onShowLoader();
        }
        mInteractor.getCollection(this);
    }

    @Override
    public void OnInteractorSuccessCollection(ArrayList<CollectionDATA> dto) {
        mView.CollectionSuccess(dto);
        mView.onHideLoader();

    }

    @Override
    public void OnInteractorServerError() {
        Log.d("ErrorCollection", "error");

    }

    @Override
    public void OnInteractorRequestFailture() {
        Log.d("ErrorCollectionRequest", "error");
    }
}
