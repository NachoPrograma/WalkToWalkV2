package com.example.walktowalk.clases;

public class Mapa {
    private int id;
    private String localizacion;
    private int x;
    private int y;
    private int idItinerario;

    public Mapa(int id, String localizacion, int x, int y, int idItinerario) {
        this.id = id;
        this.localizacion = localizacion;
        this.x = x;
        this.y = y;
        this.idItinerario = idItinerario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIdItinerario() {
        return idItinerario;
    }

    public void setIdItinerario(int idItinerario) {
        this.idItinerario = idItinerario;
    }
}


