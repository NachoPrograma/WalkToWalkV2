package com.example.walktowalk.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.recyclerview.CiudadViewHolder;


public class DetallesCiudad extends AppCompatActivity {


    TextView txt_detalle_nombrep = null;
    TextView txt_detalle_descripcion = null;
    TextView txt_detalle_statsp = null;
    public String ciudad_elegida;
    public static final String EXTRA_REGION_ELEGIDA_A_BASEDEDATOS = "es.nacho.ciudad.mainToBasedeDatos";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ciudad);

        txt_detalle_nombrep = findViewById(R.id.edt_detalles_nombre);
        txt_detalle_descripcion = findViewById(R.id.edt_detalles_descripcion);
        Intent intent = getIntent();
        if (intent != null) {
            Ciudad p = (Ciudad) intent.getSerializableExtra(CiudadViewHolder.EXTRA_OBJETO_CIUDAD);
            ciudad_elegida=p.getNombre();
            txt_detalle_nombrep.setText("Ciudad: " + (p.getNombre()));
            txt_detalle_descripcion.setText("Descripcion: " + (p.getDescripcion()));

        }
    }
    public void ListaItinerarios(View view) {
        Intent intent = new Intent(this, ListaItinerarios.class);
        intent.putExtra(EXTRA_REGION_ELEGIDA_A_BASEDEDATOS, ciudad_elegida);
        startActivity(intent);
    }
}