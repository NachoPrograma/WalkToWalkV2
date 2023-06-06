package com.example.walktowalk.recyclerview;

import android.content.Context;
//import android.graphics.Ciudad;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Ciudad;

import java.util.ArrayList;

public class ListaCiudadAdapter extends RecyclerView.Adapter<CiudadViewHolder>{
    private Context r;
    private ArrayList<Ciudad> listaCiudad;
    private LayoutInflater mInflater;

    public void setR(Context r) {
        this.r = r;
        this.listaCiudad = new ArrayList<Ciudad>();
    }
    public ListaCiudadAdapter(Context r, ArrayList<Ciudad> listaCiudad) {
        this.r = r;
        this.listaCiudad = listaCiudad;
        mInflater = LayoutInflater.from(r);
    }

    public Context getR() {
        return r;
    }



    public ArrayList<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    public void setListaCiudad(ArrayList<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;

        notifyDataSetChanged();
    }

    public ListaCiudadAdapter(Context r) {
        this.r = r;
        mInflater = LayoutInflater.from(r);
    }

    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_ciudad, parent, false);
        return new CiudadViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder holder, int position) {
        if(listaCiudad!=null) {
            Ciudad ciudad_actual = listaCiudad.get(position);
            holder.txt_rv_ciudad_id.setText("Id: " + ciudad_actual.getId());
            holder.txt_rv_ciudad_nombre.setText(("Nombre: " + ciudad_actual.getNombre()));


        }
    }

    @Override
    public int getItemCount() {
        if (listaCiudad != null)
            return listaCiudad.size();
        else return 0;
    }
}

