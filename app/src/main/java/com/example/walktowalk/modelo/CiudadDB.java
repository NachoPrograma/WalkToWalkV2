package com.example.walktowalk.modelo;
import android.util.Log;

import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Ciudad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CiudadDB {
    public static ArrayList<Itinerario> obtenerItinerario() {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("conexion","no va");
            return null;
        }
        ArrayList<Itinerario> itinerarios = new ArrayList<Itinerario>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM itinerario ORDER BY iditinerario";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                int id = resultado.getInt("iditinerario");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                int plazas = resultado.getInt("plazas");
                int idciudad = resultado.getInt("ciudad_idciudad");
                Itinerario elItinerario = new Itinerario(id, nombre, descripcion, plazas,idciudad);
                itinerarios.add(elItinerario);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return itinerarios;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return itinerarios;
        }
    }

    public static ArrayList<Ciudad> obtenerCiudad() {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("conexion","no va");
            return null;
        }
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM ciudad ORDER BY idciudad";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                int id = resultado.getInt("idciudad");
                String nombre = resultado.getString("nombre_ciudad");
                String descripcion = resultado.getString("descripcion");
                Ciudad laCiudad = new Ciudad(id, nombre, descripcion);
                ciudades.add(laCiudad);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return ciudades;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return ciudades;
        }
    }
    //-------------------------------------------------------------------------------
    public static boolean guardarItinerario(Itinerario p) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "INSERT INTO itinerario (`iditinerario`,`nombre`,`nombre_ciudad`,`plazas`,`ciudad_idciudad`) VALUES (?,?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setInt(1, p.getId());
            sentencia.setString(2, p.getNombre());
            sentencia.setString(3, p.getDescripcion());
            sentencia.setInt(4, p.getPlazas());
            sentencia.setInt(5, p.getIdciudad());
            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    //------------------------------------------------------------
    public static boolean borrarItinerario(int id) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {

            String ordensql = "DELETE FROM `itinerario` WHERE (`iditinerario` = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordensql);
            sentencia.setInt(1, id);
            int filasafectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasafectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }

        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean actualizarItinerario(Itinerario p, String itinerarioAntiguo) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordensql = "UPDATE itinerario SET iditinerario = ?, nombre = ?,nombre_ciudad = ?,plazas = ?,ciudad_idciudad = ? WHERE iditinerario = ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, p.getId());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getDescripcion());
            pst.setInt(4, p.getPlazas());
            pst.setString(5, itinerarioAntiguo);
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static ArrayList<Itinerario> obtenerPorCiudad(String ciudad) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("conexion","no va");
            return null;
        }
        ArrayList<Itinerario> itinerarios = new ArrayList<Itinerario>();
        try {
            String ordenSQL = "SELECT * FROM itinerario WHERE nombre_ciudad like ? ORDER BY iditinerario";

            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, ciudad);

            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next())
            {
                int id = resultado.getInt("iditinerario");
                String nombre = resultado.getString("nombre");
                ciudad= resultado.getString("nombre_ciudad");
                int plazas = resultado.getInt("plazas");
                int id_ciudad = resultado.getInt("ciudad_idciudad");
                Itinerario elItinerario = new Itinerario(id, nombre, ciudad, plazas, id_ciudad);
                itinerarios.add(elItinerario);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return itinerarios;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return itinerarios;
        }
    }
}
