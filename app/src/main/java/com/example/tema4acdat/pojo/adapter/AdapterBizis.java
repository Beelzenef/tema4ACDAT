package com.example.tema4acdat.pojo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tema4acdat.R;
import com.example.tema4acdat.pojo.Estacion;

import java.util.ArrayList;

/**
 * Adapter para bibliotecas
 */

public class AdapterBizis extends RecyclerView.Adapter<AdapterBizis.ViewHolder> {

    private ArrayList<Estacion> listaE;
    private Context context;

    // Define listener member variable
    private OnItemClickListener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bibliotecaView = inflater.inflate(R.layout.item_pojo, parent, false);

        ViewHolder viewHolder = new ViewHolder(bibliotecaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Estacion estacion = listaE.get(position);

        TextView txtV = holder.txtV_NombreEstacion;
        txtV.setText(estacion.getDireccion());
    }

    @Override
    public int getItemCount() {
        return listaE.size();
    }

    public AdapterBizis(Context c, ArrayList<Estacion> listaE) {
        this.listaE = listaE;
        this.context = c;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtV_NombreEstacion;

        public ViewHolder(final View itemView) {
            super(itemView);

            txtV_NombreEstacion = (TextView) itemView.findViewById(R.id.txtV_NombreItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
