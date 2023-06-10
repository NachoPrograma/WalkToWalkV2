package com.example.walktowalk.modelo;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Ciudad;
import com.example.walktowalk.clases.Mapa;
import com.example.walktowalk.clases.Fotos;
import com.example.walktowalk.utilidades.ImagenesBlobBitmap;

import java.sql.Blob;
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
                String id = resultado.getString("iditinerario");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                String  plazas = resultado.getString ("plazas");
                String idciudad = resultado.getString("ciudad_idciudad");
                int imagenResId= resultado.getInt("foto");
                Itinerario elItinerario = new Itinerario(id, nombre, descripcion, plazas,idciudad,imagenResId);
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
    public static ArrayList<Fotos> obtenerFotosCiudades(int width, int height) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<Fotos> fotosCiudadesDevueltas = new ArrayList<Fotos>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from fotos_ciudades";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                String idfoto = resultado.getString("idfoto");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, width, height);
                String idciudad = resultado.getString("idciudad");
                Fotos fc = new Fotos(idfoto, bm_foto, idciudad);
                fotosCiudadesDevueltas.add(fc);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return fotosCiudadesDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
    public static boolean validarCredenciales(String email, String clave) {
        try {
            Connection connection = ConfiguracionDB.conectarConBaseDeDatos();
            String query = "SELECT * FROM usuarios WHERE email = ? AND clave = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, clave);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Si hay un resultado, las credenciales son válidas
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getUsername(String email, String clave) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConfiguracionDB.conectarConBaseDeDatos();
            String query = "SELECT nombre FROM usuarios WHERE email = ? AND clave = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, clave);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
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
                String id = resultado.getString("idciudad");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                String imagenResId= resultado.getString("foto");
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
            sentencia.setString(1, p.getId());
            sentencia.setString(2, p.getNombre());
            sentencia.setString(3, p.getDescripcion());
            sentencia.setString (4, p.getPlazas());
            sentencia.setString(5, p.getIdciudad());
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
            pst.setString(1, p.getId());
            pst.setString(2, p.getNombre());
            pst.setString(3, p.getDescripcion());
            pst.setString (4, p.getPlazas());
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
            String ordenSQL = "SELECT * FROM itinerario WHERE ciudad_idciudad like ? ORDER BY iditinerario";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, ciudad);

            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next())
            {
                String id = resultado.getString("iditinerario");
                String nombre = resultado.getString("nombre");
                String descripcion=resultado.getString("descripcion");
                String  plazas = resultado.getString ("plazas");
                String id_ciudad = resultado.getString("ciudad_idciudad");
                int imagenResId= resultado.getInt("foto");

                Itinerario elItinerario = new Itinerario(id, nombre, descripcion, plazas, id_ciudad, imagenResId);
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
    public static ArrayList<Mapa> obtenerPorItinerario(String itinerario) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("conexion","no va");
            return null;
        }
        ArrayList<Mapa> mapas = new ArrayList<Mapa>();
        try {
            String ordenSQL = "SELECT * FROM mapa WHERE idItinerario like ? ORDER BY idmapa";

            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, itinerario);

            ResultSet resultado = sentencia.executeQuery();
            while(resultado.next())
            {
                String id = resultado.getString("idmapa");
                String localizacion = resultado.getString("nombre");
                int x = resultado.getInt("longitud");
                int y = resultado.getInt("latitud");
                String id_itinerario = resultado.getString("itinerario_iditinerario");
                String audio=resultado.getString("audio");
                Mapa elMapa = new Mapa(id, localizacion, x, y, id_itinerario,audio);
                mapas.add(elMapa);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return mapas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return mapas;
        }
    }
}
