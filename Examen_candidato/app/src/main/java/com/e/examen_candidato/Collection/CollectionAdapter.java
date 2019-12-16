package com.e.examen_candidato.Collection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e.examen_candidato.R;
import com.e.webapirequest.webApi.dto.CollectionDATA;
import com.e.webapirequest.webApi.dto.CollectionDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    public static final int TIPO_ELEMENTO_LOADER = 1;

    private ArrayList<CollectionDATA> mList = new ArrayList<>();

    private OnClickCollectionListener mListener;



    public void setListener(OnClickCollectionListener listener) {
        mListener = listener;
    }

    public interface OnClickCollectionListener {
        void onClickCollection(CollectionDTO data);
        void onClickBtnCollection(CollectionDTO data);
    }



    public void addItems(ArrayList<CollectionDATA> data) {
        mList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionAdapter.CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcollection,parent,false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.CollectionViewHolder holder, final int position) {
        final CollectionDTO datos = mList.get(position).getmListadoCollection().get(0);
        holder.mTVTitulo.setText(datos.getTitle());
        Picasso.get().load(datos.getImageURL()).into(holder.mImagen);
        holder.mTVDescripcion.setText(datos.getDescription());
        holder.mImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickCollection(datos);
            }
        });
        holder.mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickBtnCollection(datos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder{
        private ImageView mImagen;
        private Button mbutton;
        private TextView mTVDescripcion;
        private TextView mTVTitulo;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            mImagen = itemView.findViewById(R.id.imageView_url);
            mbutton = itemView.findViewById(R.id.button_enlace);
            mTVTitulo = itemView.findViewById(R.id.tv_title);
            mTVDescripcion = itemView.findViewById(R.id.tv_descripcion);
        }
    }
}
