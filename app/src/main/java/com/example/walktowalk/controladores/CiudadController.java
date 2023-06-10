package com.example.walktowalk.controladores;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.walktowalk.R;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Mapa;
import com.example.walktowalk.tareas.TareaBuscarItinerario;
import com.example.walktowalk.tareas.TareaBuscarMapa;
import com.example.walktowalk.tareas.TareaObtenerCiudad;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
    public static ArrayList<Itinerario> obtenerItinerarioDeCiudadCSV(int ciudad_elegida) {

        return null;
    }
    public static ArrayList<Mapa> obtenerMapaDeItinerario(String itinerario_elegida) {
        ArrayList<Mapa> mapa = null;
        FutureTask t = new FutureTask (new TareaBuscarMapa(itinerario_elegida));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            mapa= (ArrayList<Mapa>)t.get();
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
        return mapa;
    }
}
