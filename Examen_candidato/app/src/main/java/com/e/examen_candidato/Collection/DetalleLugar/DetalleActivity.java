package com.e.examen_candidato.Collection.DetalleLugar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.examen_candidato.Collection.LoaderDialogFragment;
import com.e.examen_candidato.R;
import com.e.webapirequest.webApi.dto.RestaurantDTO;
import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity implements DetalleView {

    public static final String BUNDLE_KEY = "idRestaurand";
    public static final String BUNDLE_URLIMAGE_KEY= "URDIMAGEN";
    private int mIdRestaurant;
    private String mURLImage;
    private DetallePresenter mPresenter;
    private LoaderDialogFragment loader;
    //elemtos de vista
    private ImageView mImageViewPortada;
    private TextView mNombreLugar, mCocina, mHorario, mDireccion;
    private ImageButton mImageBtnUbicar;
    private Double longitud, latitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        obtenerInformacionBundle();
        bindViews();
        bindListeners();
        mPresenter = new DetallePresenter(this,new DetalleInteractor());
        mPresenter.getRestaurant(mIdRestaurant);

    }

    private void bindListeners() {
        Picasso.get().load(mURLImage).into(mImageViewPortada);
        mImageBtnUbicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?q="+latitud+","
                +longitud));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }

    private void bindViews() {
        mImageViewPortada = findViewById(R.id.imageView_portada);
        mNombreLugar = findViewById(R.id.Tv_Titulo);
        mCocina = findViewById(R.id.Tv_cocina);
        mDireccion = findViewById(R.id.tv_Direccion);
        mHorario = findViewById(R.id.tv_horario);
        mImageBtnUbicar = findViewById(R.id.button_ubicacion);
    }

    private void obtenerInformacionBundle() {
        if(getIntent().getExtras() != null){
            mIdRestaurant = getIntent().getIntExtra(BUNDLE_KEY, 0 );
            mURLImage = getIntent().getStringExtra(BUNDLE_URLIMAGE_KEY);
        }
    }

    @Override
    public void RestaurantSuccess(RestaurantDTO data) {
        mNombreLugar.setText(getString(R.string.lugar, data.getNombre()));
        mCocina.setText(getString(R.string.conina,data.getCocina()));
        mDireccion.setText(getString(R.string.lugar,data.getLugar().getDireccion()));
        mHorario.setText(getString(R.string.horario, data.getHorario()));
        latitud = Double.valueOf(data.getLugar().getLatitud());
        longitud = Double.valueOf(data.getLugar().getLongitud());
        onHideLoader();
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
    public void mostrarMensaje(String mensaje) {
        loader.dismiss();
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
