package com.example.walktowalk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.recyclerview.CiudadViewHolder;


public class DetallesCiudad extends AppCompatActivity {

    TextView txt_detalle_idp = null;
    TextView txt_detalle_nombrep = null;
    TextView txt_detalle_ciudadp = null;
    TextView txt_detalle_statsp = null;

    //private String ciudad_elegido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ciudad);

        txt_detalle_idp = findViewById(R.id.edt_detalles_nombre);
        txt_detalle_nombrep = findViewById(R.id.edt_detalles_descripcion);
        //txt_detalle_statsp = findViewById(R.id.txt_detalles_statsp);
        Intent intent = getIntent();
        if (intent != null) {
            Ciudad p = (Ciudad) intent.getSerializableExtra(CiudadViewHolder.EXTRA_OBJETO_CIUDAD);
            txt_detalle_idp.setText(p.getNombre());
            txt_detalle_idp.setText("Ciudad: " + (p.getId()));
            txt_detalle_nombrep.setText("Nombre: " + (p.getNombre()));
            txt_detalle_ciudadp.setText("Descripción: " + (p.getDescripcion()));
            //txt_detalle_statsp.setText("Stats: " + (p.getStats()));

        }
    }
}