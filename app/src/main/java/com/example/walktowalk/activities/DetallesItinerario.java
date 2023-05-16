package com.example.walktowalk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.recyclerview.ItinerarioViewHolder;

public class DetallesItinerario extends AppCompatActivity {

    TextView txt_detalle_idp = null;
    TextView txt_detalle_nombrep = null;
    TextView txt_detalle_ciudadp = null;
    TextView txt_detalle_plazasp = null;
    //private String itinerario_elegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_itinerario);
        txt_detalle_idp = findViewById(R.id.txt_detalles_idp);
        txt_detalle_nombrep = findViewById(R.id.txt_detalles_nombrep);
        txt_detalle_ciudadp = findViewById(R.id.txt_detalles_ciudadp);
        txt_detalle_plazasp = findViewById(R.id.txt_detalles_plazasp);
        Intent intent = getIntent();
        if (intent != null) {
            Itinerario p = (Itinerario) intent.getSerializableExtra(ItinerarioViewHolder.EXTRA_OBJETO_ITINERARIO);
            txt_detalle_idp.setText(p.getNombre());
            txt_detalle_idp.setText("Itinerario: " + (p.getId()));
            txt_detalle_nombrep.setText("Descripcion: " + (p.getNombre()));
            txt_detalle_ciudadp.setText("Region: " + (p.getIdciudad()));
            txt_detalle_plazasp.setText("Stats: " + (p.getPlazas()));

        }
    }
}