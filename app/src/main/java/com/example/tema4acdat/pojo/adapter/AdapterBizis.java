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

    private ArrayList<Estacion> listaEstaciones;

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
        Estacion estacion = listaEstaciones.get(position);

        TextView txtV = holder.txtV_NombreEstacion;
        txtV.setText(estacion.getDescription());
    }

    @Override
    public int getItemCount() {
        return listaEstaciones.size();
    }

    public AdapterBizis() {
        this.listaEstaciones = new ArrayList<>();
    }

    public AdapterBizis(ArrayList<Estacion> listaEstaciones) {
        this.listaEstaciones.addAll(listaEstaciones);
    }

    public void clear() { this.listaEstaciones.clear(); }

    public void addAll(ArrayList<Estacion> listaEstaciones) {
        this.listaEstaciones.addAll(listaEstaciones);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtV_NombreEstacion;

        public ViewHolder(final View itemView) {
            super(itemView);

            txtV_NombreEstacion = (TextView) itemView.findViewById(R.id.txtV_NombreItem);
        }
    }
}
