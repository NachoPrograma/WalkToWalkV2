package com.example.walktowalk.activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
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
    private TextView txt_username;
    private ImageButton btn_menu;
    public String ciudad_elegida;



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

        /*btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });*/

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



    /*public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_option1:
                        // Acción para la opción 1
                        Toast.makeText(ListaCiudad.this, "Opción 1 seleccionada", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_option2:
                        // Acción para la opción 2
                        Toast.makeText(ListaCiudad.this, "Opción 2 seleccionada", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_option3:
                        // Acción para la opción 3
                        Toast.makeText(ListaCiudad.this, "Opción 3 seleccionada", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }*/
}