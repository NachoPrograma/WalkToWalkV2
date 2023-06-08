package com.example.walktowalk.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Fotos;
import com.example.walktowalk.clases.Itinerario;

import java.util.ArrayList;

public class ListaItinerarioAdapter extends RecyclerView.Adapter<ItinerarioViewHolder>{
    private Context p;
    private ArrayList<Itinerario> listaItinerario;
    ArrayList<Fotos> listaFotosItinerario;
    public ImageView img_itinerario = null;
    private LayoutInflater mInflater;

    public void setP(Context p) {
        this.p = p;
        this.listaItinerario = new ArrayList<Itinerario>();
    }
    public ListaItinerarioAdapter(Context p, ArrayList<Itinerario> listaItinerario, ArrayList<Fotos> fotosItinerario) {
        this.p = p;
        this.listaItinerario = listaItinerario;
        this.listaFotosItinerario = fotosItinerario;
        mInflater = LayoutInflater.from(p);
    }

    public Context getP() {
        return p;
    }
    public ArrayList<Itinerario> getListaItinerario() {
        return listaItinerario;
    }
    public void setListaItinerario(ArrayList<Itinerario> listaItinerario) {
        this.listaItinerario = listaItinerario;

        notifyDataSetChanged();
    }
    public ListaItinerarioAdapter(Context p) {
        this.p = p;
        mInflater = LayoutInflater.from(p);
    }
    @NonNull
    @Override
    public ItinerarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_itinerario, parent, false);
        return new ItinerarioViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItinerarioViewHolder holder, int position) {
        if(listaItinerario!=null) {
            Itinerario itinerario_actual = listaItinerario.get(position);
            holder.txt_rv_itinerario_id.setText("Itinerario: " + itinerario_actual.getId());
            holder.txt_rv_itinerario_nombre.setText(("Nombre: " + itinerario_actual.getNombre()));
            holder.txt_rv_itinerario_descripcion.setText(("Descripcion: " + itinerario_actual.getDescripcion()));
            holder.txt_rv_itinerario_plazas.setText(String.valueOf("Id_Ciudad: " + itinerario_actual.getIdciudad()));
            holder.txt_rv_itinerario_plazas.setText(String.valueOf("Plazas: " + itinerario_actual.getPlazas()));
            if(this.listaFotosItinerario != null)
            {
                for(Fotos fc: this.listaFotosItinerario)
                {
                    if(fc.getIdfoto()==itinerario_actual.getIdciudad())
                    {
                        holder.img_itinerario.setImageBitmap(fc.getFoto());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (listaItinerario != null)
            return listaItinerario.size();
        else return 0;
    }
}
