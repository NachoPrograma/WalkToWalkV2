package com.example.walktowalk.clases;
import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.Objects;
public class Ciudad implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;


    public Ciudad(int id, String nombre, String descripcion) {
        this.id=id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Ciudad() {
        this.id=(2)+this.id;
        this.nombre = "sin nombre";
        this.descripcion = "sin descripcion";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ciudad)) return false;
        Ciudad ciudades = (Ciudad) o;
        return nombre.equals(ciudades.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
