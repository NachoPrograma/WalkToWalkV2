package com.example.walktowalk.modelo;
import android.os.StrictMode;

import androidx.annotation.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionDB {
    public static final String HOSTDB = "10.0.2.2";
    public static final String NOMBREDB = "walktowalk";
    public static final String USUARIODB = "root";
    public static final String CLAVEDB = "1234";
    private static final String OPCIONESHORA = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String PUERTOMYSQL = "3306";
    public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB +":"+PUERTOMYSQL + "/" + NOMBREDB + OPCIONESHORA;

    //----------------------------------------------------------....
    @Nullable
    public static Connection conectarConBaseDeDatos() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection conexion = DriverManager.getConnection(URLMYSQL, USUARIODB, CLAVEDB);

            return conexion;
        } catch (SQLException e) {
            System.out.println("No se pudo establecer la conexion con la base de datos");
            e.printStackTrace();
            return null;
        }
    }

}
