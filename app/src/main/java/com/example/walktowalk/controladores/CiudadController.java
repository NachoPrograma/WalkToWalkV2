package com.example.walktowalk.controladores;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.tareas.TareaBuscarItinerario;
import com.example.walktowalk.tareas.TareaObtenerCiudad;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CiudadController {
    public static ArrayList<Ciudad> obtenerCiudad() {
        ArrayList<Ciudad> ciudad = null;
        FutureTask t = new FutureTask(new TareaObtenerCiudad());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ciudad = (ArrayList<Ciudad>) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
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
        return ciudad;
    }

    public static ArrayList<Itinerario> obtenerItinerarioDeCiudad(String ciudad_elegida) {
        ArrayList<Itinerario> itinerario = null;
        FutureTask t = new FutureTask (new TareaBuscarItinerario(ciudad_elegida));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            itinerario= (ArrayList<Itinerario>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)) {
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
        return itinerario;

    }
}
