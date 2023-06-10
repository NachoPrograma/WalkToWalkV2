package com.example.walktowalk.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.recyclerview.CiudadViewHolder;
import com.example.walktowalk.recyclerview.ListaItinerarioAdapter;
import com.example.walktowalk.clases.Fotos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ListaItinerarios extends AppCompatActivity {
    private RecyclerView rv_itinerario;
    public static final String EXTRA_REGION = "es.nacho.ciudad.ListaItinerarios";
    private ListaItinerarioAdapter mAdapter;
    private List<Itinerario> itinerario;
    private String ciudad_elegida;
    private ArrayList<Fotos> fotosItinerario;
    public static String itinerario_elegido;
    public static final String EXTRA_ITINERARIO_ELEGIDA_A_BASEDEDATOS = "es.nacho.region.mainToBasedeDatos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itinerario = ItinerariosDesdeCSV();
        Intent intent = getIntent();
        if(intent != null){
            Ciudad ciudad=(Ciudad) intent.getSerializableExtra(CiudadViewHolder.EXTRA_OBJETO_CIUDAD);
            ciudad_elegida= ciudad.getId();
            setContentView(R.layout.activity_lista_itinerarios);
            rv_itinerario = findViewById(R.id.rv_itinerario);
            mAdapter = new ListaItinerarioAdapter(this);
            //ArrayList<Itinerario> itinerario= ItinerariosDesdeCSV();
            ArrayList<Itinerario> itinerario = ObtenerProvinciasDeComunidad(ciudad_elegida);
            //fotosItinerario = FotoCiudadController.obtenerFotosItinerario();
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
    private ArrayList<Itinerario> ItinerariosDesdeCSV() {
        InputStream is = getResources().openRawResource(R.raw.itinerarios);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";
        ArrayList<Itinerario> lositinerarios = new ArrayList<Itinerario>();
        try {
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                String id = datos[0];
                String nombre = datos[1];
                String  descripcion = datos[2];
                String plazas = datos[3];
                String id_ciudad = datos[4];
                String imagenId =datos[5];
                int imagenResId = getResources().getIdentifier(imagenId, "raw", getPackageName());
                Itinerario p = new Itinerario(id,nombre, descripcion, plazas,id_ciudad,imagenResId);
                lositinerarios.add(p);
            }
        } catch (IOException e1) {
            System.out.println("no pude abrir el fichero de provincias");
            Log.i("csv", "no pude abrir el fichero de provincias");
        }
        return lositinerarios;
    }
    private ArrayList<Itinerario> ObtenerProvinciasDeComunidad(String comunidadSeleccionada) {
        ArrayList<Itinerario> provinciasComunidad = new ArrayList<Itinerario>();
        String codigoComunidad="";
        for (Itinerario p: itinerario ){
            codigoComunidad = p.getIdciudad();
            if(comunidadSeleccionada.contains(codigoComunidad))
            {
                provinciasComunidad.add(p);
            }
        }
        return provinciasComunidad;
    }

}