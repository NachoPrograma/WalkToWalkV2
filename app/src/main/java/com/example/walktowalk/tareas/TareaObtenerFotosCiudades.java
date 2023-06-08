package com.example.walktowalk.tareas;

import com.example.walktowalk.modelo.CiudadDB;
import com.example.walktowalk.clases.Fotos;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class TareaObtenerFotosCiudades implements Callable<ArrayList<Fotos>> {

    private int width;
    private int height;

    public TareaObtenerFotosCiudades(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public ArrayList<Fotos> call() throws Exception {
        ArrayList<Fotos> fotosCiudades = CiudadDB.obtenerFotosCiudades(this.width, this.height);
        return fotosCiudades;
    }
}