package com.example.walktowalk.recyclerview;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.walktowalk.R;
import com.example.walktowalk.activities.ListaCiudad;
import com.example.walktowalk.clases.Ciudad;
import java.util.ArrayList;

public class CiudadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_CIUDAD =  "nacho.ciudadViewHolder";
    public TextView txt_rv_ciudad_id = null;
    public TextView txt_rv_ciudad_nombre = null;
    ListaCiudadAdapter lrAdapter;

    public CiudadViewHolder(@NonNull View itemView, ListaCiudadAdapter lrAdapter) {
        super(itemView);
        txt_rv_ciudad_id = (TextView)  itemView.findViewById(R.id.txt_rv_ciudad_id);
        txt_rv_ciudad_nombre = (TextView)  itemView.findViewById(R.id.txt_item_nombre);
        this.lrAdapter = lrAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int mPosition = getAdapterPosition();

        ArrayList<Ciudad> ciudades = this.lrAdapter.getListaCiudad();
        Ciudad ciudad = ciudades.get(mPosition);
        // lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lrAdapter.getR(), ListaCiudad.class);
        intent.putExtra(EXTRA_OBJETO_CIUDAD, ciudad);
        lrAdapter.getR().startActivity(intent);
    }
}
