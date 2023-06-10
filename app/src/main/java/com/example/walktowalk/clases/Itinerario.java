package com.example.walktowalk.clases;
import java.io.Serializable;
import java.util.Objects;

public class Itinerario implements Serializable{
    private String id;
    private String nombre;
    private String descripcion;
    private String  plazas;
    private String idciudad;
    private int imagenResId;

    public Itinerario(String id, String nombre, String descripcion, String  plazas, String idciudad, int imagenResId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.plazas = plazas;
        this.idciudad = idciudad;
        this.imagenResId = imagenResId; // Inicializa la propiedad

    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String  getPlazas() {
        return plazas;
    }
    public void setPlazas(String  plazas) {
        this.plazas = plazas;
    }
    public String getIdciudad() {
        return idciudad;
    }
    public void setIdciudad(String idciudad) {
        this.idciudad = idciudad;
    }
    public int getImagenResId() {
        return imagenResId;
    }
    public void setImagenResId(int imagenResId) {
        this.imagenResId = imagenResId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Itinerario pokemon = (Itinerario) o;
        return Objects.equals(id, pokemon.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return  getNombre() ;
    }
}
