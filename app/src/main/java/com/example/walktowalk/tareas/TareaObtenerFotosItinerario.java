package com.example.walktowalk.tareas;

import com.example.walktowalk.modelo.CiudadDB;
import com.example.walktowalk.clases.Fotos;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class TareaObtenerFotosItinerario implements Callable<ArrayList<Fotos>> {

    private int width;
    private int height;

    public TareaObtenerFotosItinerario(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public ArrayList<Fotos> call() throws Exception {
        ArrayList<Fotos> fotosItinerario = CiudadDB.obtenerFotosCiudades(this.width, this.height);
        return fotosItinerario;
    }
}