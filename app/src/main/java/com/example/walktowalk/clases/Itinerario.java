package com.example.walktowalk.clases;
import java.io.Serializable;
import java.util.Objects;

public class Itinerario implements Serializable{
    private int id;
    private String nombre;
    private String descripcion;
    private int plazas;
    private int idciudad;

    public Itinerario(int id, String nombre, String descripcion, int plazas, int idciudad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.plazas = plazas;
        this.idciudad = idciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
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
