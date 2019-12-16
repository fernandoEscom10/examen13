package com.e.examen_candidato.Collection;

import com.e.webapirequest.webApi.dto.CollectionDATA;

import com.e.webapirequest.webApi.web.CollectionsRequest;

import java.util.ArrayList;

public class CollectionInteractor {

    private OnCollectionInteractorListener mListener;

    public interface  OnCollectionInteractorListener{

        void OnInteractorSuccessCollection(ArrayList<CollectionDATA> dto);

        void OnInteractorServerError();

        void OnInteractorRequestFailture();
    }

    public void getCollection(OnCollectionInteractorListener listener){
        mListener = listener;
        CollectionsRequest request = new CollectionsRequest();
        request.makeRequest(10, new CollectionsRequest.OnResponseCollection() {

            @Override
            public void onResponseCollection(ArrayList<CollectionDATA> listado) {
                mListener.OnInteractorSuccessCollection(listado);

            }

            @Override
            public void onResponseErrorServidor() {
                mListener.OnInteractorServerError();
            }


            @Override
            public void onResponseSinConexion() {
                mListener.OnInteractorRequestFailture();
            }
        });
    }
}
