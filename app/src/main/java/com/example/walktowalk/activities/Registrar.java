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
import java.sql.SQLException;

public class Registrar extends AppCompatActivity {
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        edtName = findViewById(R.id.edt_login_nombre);
        edtEmail = findViewById(R.id.edt_registrar_email);
        edtPassword = findViewById(R.id.edt_registrar_password);
    }
    public void onRegisterClick(View view) {
        String usuario = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (usuario.isEmpty() || email.isEmpty() || password.isEmpty()) {
            // Validación de campos vacíos
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            // Registro del usuario en la base de datos
            if (registerUser(usuario, email, password)) {
                // Registro exitoso, realizar acción deseada
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ListaCiudad.class);
                intent.putExtra("username", usuario);
                startActivity(intent);
                // Continuar con la lógica deseada después del registro exitoso
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean registerUser(String name, String email, String password) {
        // Insertar el usuario en la base de datos
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConfiguracionDB.conectarConBaseDeDatos();
            String query = "INSERT INTO users ('email', 'clave', 'nombre') VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, name);
            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}