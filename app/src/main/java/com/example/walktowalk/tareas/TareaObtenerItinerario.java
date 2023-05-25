package com.example.walktowalk.tareas;

import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.modelo.CiudadDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerItinerario implements Callable<ArrayList<Itinerario>> {

    @Override
    public ArrayList<Itinerario> call() throws Exception {
        ArrayList<Itinerario> itinerario= CiudadDB.obtenerItinerario();
        return itinerario;
    }
}
