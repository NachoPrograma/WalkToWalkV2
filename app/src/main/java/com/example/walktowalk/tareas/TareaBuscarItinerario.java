package com.example.walktowalk.tareas;


import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.modelo.CiudadDB;
import com.example.walktowalk.modelo.CiudadDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaBuscarItinerario implements Callable<ArrayList<Itinerario>> {
    String ciudad=null;
    public TareaBuscarItinerario(String ciudad_elegida) {
        ciudad=ciudad_elegida;
    }
    @Override
    public ArrayList<Itinerario> call() throws Exception {
        ArrayList<Itinerario> pokemon= CiudadDB.obtenerPorCiudad(ciudad);
        return pokemon;
    }
}
