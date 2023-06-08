package com.example.walktowalk.controladores;

import com.example.walktowalk.tareas.TareaObtenerFotosCiudades;
import com.example.walktowalk.tareas.TareaObtenerFotosItinerario;
import com.example.walktowalk.clases.Fotos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FotoCiudadController {
    public static ArrayList<Fotos> obtenerFotosCiudades() {

        ArrayList<Fotos> fotosCiudades = null;
        FutureTask t = new FutureTask (new TareaObtenerFotosCiudades(100,100));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            fotosCiudades= (ArrayList<Fotos>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fotosCiudades;
    }
    public static ArrayList<Fotos> obtenerFotosItinerario() {

        ArrayList<Fotos> fotosItinerario = null;
        FutureTask t = new FutureTask (new TareaObtenerFotosItinerario(100,100));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            fotosItinerario= (ArrayList<Fotos>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fotosItinerario;
    }
}
