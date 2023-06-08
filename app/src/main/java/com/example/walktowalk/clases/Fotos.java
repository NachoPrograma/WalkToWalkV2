package com.example.walktowalk.clases;

import android.graphics.Bitmap;

public class Fotos {
    private int idfoto;
    private Bitmap foto;
    private int idciudad;

    public Fotos(int idfoto, Bitmap foto, int idciudad) {
        this.idfoto = idfoto;
        this.foto = foto;
        this.idciudad = idciudad;
    }

    public int getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(int idfoto) {
        this.idfoto = idfoto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public int getIdciudad() {
        return idciudad;
    }

    public void setIdciudad(int idciudad) {
        this.idciudad = idciudad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fotos that = (Fotos) o;
        return idfoto == that.idfoto &&
                idciudad == that.idciudad &&
                foto.equals(that.foto);
    }

    @Override
    public String toString() {
        return "FotoCiudad{" +
                "idfoto=" + idfoto +
                ", idciudad=" + idciudad +
                '}';
    }
}
