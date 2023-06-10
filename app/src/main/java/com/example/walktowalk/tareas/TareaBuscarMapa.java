package com.example.walktowalk.tareas;

import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Mapa;
import com.example.walktowalk.modelo.CiudadDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaBuscarMapa implements Callable<ArrayList<Mapa>> {
    String itinerario;
        public TareaBuscarMapa(String itinerario_elegida) {itinerario=itinerario_elegida;}
        @Override
        public ArrayList<Mapa> call() throws Exception {
            ArrayList<Mapa> mapa= CiudadDB.obtenerPorItinerario(itinerario);
            return mapa;
        }
}

