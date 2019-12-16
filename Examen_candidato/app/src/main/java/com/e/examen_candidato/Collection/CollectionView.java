package com.e.examen_candidato.Collection;

import com.e.webapirequest.webApi.dto.CollectionDATA;

import java.util.ArrayList;

public interface CollectionView {

    void CollectionSuccess(ArrayList<CollectionDATA> data);

    void onShowLoader();

    void onHideLoader();
}
