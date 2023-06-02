package com.example.walktowalk.utilidades;

import com.example.walktowalk.clases.Mapa;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapaUtils {

    public static void addMarkes(GoogleMap googleMap, ArrayList<Mapa> mapa)
    {
        for(Mapa m: mapa)
        {
            addMarker(googleMap,m);
        }
    }

    public static void addMarker(GoogleMap googleMap, Mapa mapa)
    {
        LatLng sitio1 = new LatLng(mapa.getX(),mapa.getY());
        googleMap.addMarker(new MarkerOptions().position(sitio1).title(mapa.getLocalizacion()));
        //  googleMap.moveCamera(CameraUpdateFactory.newLatLng(sitio1));
    }
}