package com.example.walktowalk.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.controladores.CiudadController;
import com.example.walktowalk.controladores.FotoCiudadController;
import com.example.walktowalk.recyclerview.ListaCiudadAdapter;
import com.example.walktowalk.clases.Fotos;

import java.util.ArrayList;
import java.util.List;

public class ListaCiudad extends AppCompatActivity {

    private RecyclerView rv_ciudad;
    private ListaCiudadAdapter mAdapter;
    private List<Ciudad> ciudad;
    private ArrayList<Fotos> fotosCiudades;
    private TextView txt_username;
    private ImageButton btn_menu;
    public String ciudad_elegida;
    public static final String EXTRA_REGION_ELEGIDA_A_BASEDEDATOS = "es.nacho.ciudad.mainToBasedeDatos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudad);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txt_username = findViewById(R.id.txt_username);
        btn_menu = findViewById(R.id.btn_menu);
        String username = getIntent().getStringExtra("username");
        txt_username.setText(username);
        rv_ciudad = findViewById(R.id.rv_ciudad);
        mAdapter = new ListaCiudadAdapter(this);
        ArrayList<Ciudad> ciudad = CiudadController.obtenerCiudad();
        fotosCiudades = FotoCiudadController.obtenerFotosCiudades();
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
        Intent intent = new Intent(this, ListaItinerarios.class);
        intent.putExtra(EXTRA_REGION_ELEGIDA_A_BASEDEDATOS, ciudad_elegida);
        startActivity(intent);
    }
}