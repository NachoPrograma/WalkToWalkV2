package com.example.walktowalk.tareas;

import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.modelo.CiudadDB;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerCiudad implements Callable<ArrayList<Ciudad>> {

    @Override
    public ArrayList<Ciudad> call() throws Exception {
        ArrayList<Ciudad> ciudad= CiudadDB.obtenerCiudad();
        return ciudad;
    }
}
