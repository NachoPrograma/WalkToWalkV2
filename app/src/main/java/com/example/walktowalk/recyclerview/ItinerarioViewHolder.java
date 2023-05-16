package com.example.walktowalk.recyclerview;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walktowalk.R;
import com.example.walktowalk.activities.DetallesItinerario;
import com.example.walktowalk.clases.Itinerario;

import java.util.ArrayList;

public class ItinerarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_ITINERARIO =  "nacho.itinerarioViewHolder";
    public TextView txt_rv_itinerario_id = null;
    public TextView txt_rv_itinerario_nombre = null;
    public TextView txt_rv_itinerario_plazas = null;
    ListaItinerarioAdapter lpAdapter;

    public ItinerarioViewHolder(@NonNull View itemView, ListaItinerarioAdapter lpAdapter) {
        super(itemView);
        txt_rv_itinerario_id = (TextView)  itemView.findViewById(R.id.txt_rv_itinerario_id);
        txt_rv_itinerario_nombre = (TextView)  itemView.findViewById(R.id.txt_rv_itinerario_nombre);
        txt_rv_itinerario_plazas = (TextView)  itemView.findViewById(R.id.txt_rv_plazas);
        this.lpAdapter = lpAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int mPosition = getAdapterPosition();
        ArrayList<Itinerario> itinerarios = this.lpAdapter.getListaItinerario();
        Itinerario itinerario = itinerarios.get(mPosition);
        Intent intent = new Intent(lpAdapter.getP(), DetallesItinerario.class);
        intent.putExtra(EXTRA_OBJETO_ITINERARIO, itinerario);
        lpAdapter.getP().startActivity(intent);
    }
}
