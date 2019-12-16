package com.e.examen_candidato.Collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.e.examen_candidato.Collection.DetalleLugar.DetalleActivity;
import com.e.examen_candidato.R;
import com.e.webapirequest.webApi.dto.CollectionDATA;
import com.e.webapirequest.webApi.dto.CollectionDTO;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity implements CollectionView, CollectionAdapter.OnClickCollectionListener{

    private CollectionPresenter mPresenter;
    private RecyclerView mRecycler;
    private LoaderDialogFragment loader;

    private CollectionAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.rv_collection);
        mPresenter = new CollectionPresenter(this, new CollectionInteractor());
        mPresenter.getCollection();
        inicializarListado();
    }

    private void inicializarListado() {
        mAdapter = new CollectionAdapter();
        gridLayoutManager = new GridLayoutManager(this, 2);
        mAdapter.setListener(this);
        mRecycler.setLayoutManager(gridLayoutManager);
        mRecycler.setAdapter(mAdapter);
    }


    @Override
    public void CollectionSuccess(ArrayList<CollectionDATA> data) {
        mAdapter.addItems(data);

    }

    @Override
    public void onShowLoader() {
        loader = new LoaderDialogFragment();
        loader.show(getSupportFragmentManager(),"loader");

    }

    @Override
    public void onHideLoader() {
        if(loader != null){
            loader.dismiss();
        }

    }

    @Override
    public void onClickCollection(CollectionDTO data) {
        Intent intent = new Intent(this, DetalleActivity.class);
        intent.putExtra(DetalleActivity.BUNDLE_KEY,data.getCollectionId());
        intent.putExtra(DetalleActivity.BUNDLE_URLIMAGE_KEY, data.getImageURL());
        startActivity(intent);

    }

    @Override
    public void onClickBtnCollection(CollectionDTO data) {
        Intent intent  = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(data.getURL()));
        startActivity(intent);
    }
}
