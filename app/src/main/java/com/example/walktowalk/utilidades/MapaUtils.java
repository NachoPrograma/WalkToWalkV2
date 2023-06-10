package com.example.walktowalk.utilidades;



import static com.example.walktowalk.activities.ListaItinerarios.itinerario_elegido;

import com.example.walktowalk.clases.Mapa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapaUtils {
    public static void addMarkes(GoogleMap googleMap, ArrayList<Mapa> mapa)
    {

        int initenrarioSeleccionado=0;
        String itinerario_elegido1=itinerario_elegido;

        if(itinerario_elegido1.contains("Madrid")){
            initenrarioSeleccionado=1;
        }
        else if(itinerario_elegido1.contains("Sesena")){
            initenrarioSeleccionado=2;
        }
        else if(itinerario_elegido1.contains("Cuenca")){
            initenrarioSeleccionado=3;
        }

        for(Mapa m: mapa)
        {
            if(initenrarioSeleccionado==1&&m.getLocalizacion().contains("Madrid")){
                addMarker(googleMap,m);
            }
            else if(initenrarioSeleccionado==2&&m.getLocalizacion().contains("Sesena")){
                addMarker(googleMap,m);
            }
            else if(initenrarioSeleccionado==3&&m.getLocalizacion().contains("Cuenca")){
                addMarker(googleMap,m);
            }

        }

    }
    public static void addMarker(GoogleMap googleMap, Mapa mapa)
    {
        LatLng sitio1 = new LatLng(mapa.getX(),mapa.getY());
        googleMap.addMarker(new MarkerOptions().position(sitio1).title(mapa.getLocalizacion()));
        //  googleMap.moveCamera(CameraUpdateFactory.newLatLng(sitio1));
    }
}