package com.example.walktowalk.clases;

public class Mapa {
    private String id;
    private String localizacion;
    private double  x;
    private double  y;
    private String idItinerario;
    private String nombreAudio;
    public Mapa(String id, String localizacion, double  x, double  y, String idItinerario, String nombreAudio) {
        this.id = id;
        this.localizacion = localizacion;
        this.x = x;
        this.y = y;
        this.idItinerario = idItinerario;
        this.nombreAudio=nombreAudio;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLocalizacion() {
        return localizacion;
    }
    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String getIdItinerario() {
        return idItinerario;
    }
    public void setIdItinerario(String idItinerario) {
        this.idItinerario = idItinerario;
    }

    public String getNombreAudio() {
        return nombreAudio;
    }

    public void setNombreAudio(String nombreAudio) {
        this.nombreAudio = nombreAudio;
    }
}


