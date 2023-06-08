package com.example.walktowalk.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.controladores.CiudadController;
import com.example.walktowalk.controladores.FotoCiudadController;
import com.example.walktowalk.recyclerview.CiudadViewHolder;
import com.example.walktowalk.recyclerview.ListaItinerarioAdapter;
import com.example.walktowalk.clases.Fotos;

import java.util.ArrayList;
import java.util.List;

public class ListaItinerarios extends AppCompatActivity {

    private RecyclerView rv_itinerario;
    public static final String EXTRA_REGION = "es.nacho.ciudad.ListaItinerarios";
    private ListaItinerarioAdapter mAdapter;
    private List<Itinerario> itinerario;
    private int ciudad_elegida;
    private ArrayList<Fotos> fotosItinerario;
    private String itinerario_elegido;
    public static final String EXTRA_ITINERARIO_ELEGIDA_A_BASEDEDATOS = "es.nacho.region.mainToBasedeDatos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if(intent != null){
            Ciudad ciudad=(Ciudad) intent.getSerializableExtra(CiudadViewHolder.EXTRA_OBJETO_CIUDAD);
            ciudad_elegida= ciudad.getId();
            setContentView(R.layout.activity_lista_itinerarios);
            rv_itinerario = findViewById(R.id.rv_itinerario);
            mAdapter = new ListaItinerarioAdapter(this);
            ArrayList<Itinerario> itinerario= CiudadController.obtenerItinerarioDeCiudad(ciudad_elegida);
            fotosItinerario = FotoCiudadController.obtenerFotosItinerario();
            if(itinerario != null) {
                mAdapter.setListaItinerario(itinerario);
            }
            //------------------------------------------------------------

            rv_itinerario.setAdapter(mAdapter);
            rv_itinerario.setLayoutManager(new LinearLayoutManager(this));

            itinerario_elegido=String.valueOf(itinerario);


        }
    }
    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }
    public void BaseDeDatos(View view) {
        Intent intent = new Intent(this, Mapas.class);
        intent.putExtra(EXTRA_ITINERARIO_ELEGIDA_A_BASEDEDATOS, itinerario_elegido);
        startActivity(intent);
    }



}