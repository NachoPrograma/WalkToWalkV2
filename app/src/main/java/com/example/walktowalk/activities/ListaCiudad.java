package com.example.walktowalk.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.controladores.CiudadController;
import com.example.walktowalk.modelo.CiudadDB;
import com.example.walktowalk.recyclerview.ListaCiudadAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListaCiudad extends AppCompatActivity {

    private RecyclerView rv_ciudad;
    private ListaCiudadAdapter mAdapter;
    private List<Ciudad> ciudad;
    private String ciudad_elegida;
    public static final String EXTRA_REGION_ELEGIDA_A_BASEDEDATOS = "es.nacho.ciudad.mainToBasedeDatos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudad);
        rv_ciudad = findViewById(R.id.rv_ciudad);
        mAdapter = new ListaCiudadAdapter(this);
        ArrayList<Ciudad> ciudad = CiudadController.obtenerCiudad();

        if(ciudad != null) {
            mAdapter.setListaCiudad(ciudad);
        }
        //------------------------------------------------------------
        rv_ciudad.setAdapter(mAdapter);
        rv_ciudad.setLayoutManager(new LinearLayoutManager(this));
        //------------------------------------------------------------
        ciudad_elegida=String.valueOf(ciudad);

    }

    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }

    public void BaseDeDatos(View view) {
        Intent intent = new Intent(this, ListaCiudad.class);
        intent.putExtra(EXTRA_REGION_ELEGIDA_A_BASEDEDATOS, ciudad_elegida);
        startActivity(intent);
    }


}