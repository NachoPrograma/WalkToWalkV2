package com.example.walktowalk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walktowalk.R;
import com.example.walktowalk.modelo.ConfiguracionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {

    private EditText edt_login_email;
    private EditText edt_login_clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_clave = (EditText) findViewById(R.id.edt_login_clave);
    }

    private boolean validarCredenciales(String email, String clave) {
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

    public void onClickLogin(View view) {
        String email = edt_login_email.getText().toString();
        String clave = edt_login_clave.getText().toString();

        if (validarCredenciales(email, clave)) {
            String usuario = email;
            // Las credenciales son válidas, realizar acción de inicio de sesión exitoso
            Intent intent = new Intent(this, ListaCiudad.class);
            intent.putExtra("username", usuario);
            startActivity(intent);
        } else {
            // Las credenciales son inválidas, mostrar mensaje de error
            Toast.makeText(this,"No tiene cuenta, tiene que registrarse", Toast.LENGTH_SHORT).show();
        }
    }
}
