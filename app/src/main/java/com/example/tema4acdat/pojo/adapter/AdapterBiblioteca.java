package com.example.tema4acdat.pojo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tema4acdat.R;
import com.example.tema4acdat.pojo.Biblioteca;

import java.util.ArrayList;

/**
 * Adapter para bibliotecas
 */

public class AdapterBiblioteca extends RecyclerView.Adapter<AdapterBiblioteca.ViewHolder> {

    private ArrayList<Biblioteca> listaB;
    private Context context;

    // Define listener member variable
    private OnItemClickListener listener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View bibliotecaView = inflater.inflate(R.layout.layout_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(bibliotecaView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Biblioteca b = listaB.get(position);

        TextView txtV = holder.txtV_NombreBiblio;
        txtV.setText(b.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return listaB.size();
    }

    public AdapterBiblioteca(Context c, ArrayList<Biblioteca> listaB) {
        this.listaB = listaB;
        this.context = c;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtV_NombreBiblio;

        public ViewHolder(final View itemView) {
            super(itemView);

            txtV_NombreBiblio = (TextView) itemView.findViewById(R.id.txtV_NombreItem);
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
