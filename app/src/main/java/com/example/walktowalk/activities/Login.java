package com.example.walktowalk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walktowalk.R;
import com.example.walktowalk.modelo.CiudadDB;
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
        edt_login_email = (EditText) findViewById(R.id.edt_registrar_email);
        edt_login_clave = (EditText) findViewById(R.id.edt_login_clave);
    }
    public void registrarUsuario(View view) {
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }
    public void onClickLogin(View view) {
        String email = edt_login_email.getText().toString();
        String clave = edt_login_clave.getText().toString();
        if (CiudadDB.validarCredenciales(email, clave)) {
            String usuario = CiudadDB.getUsername(email, clave);
            // Las credenciales son válidas, realizar acción de inicio de sesión exitoso
            Intent intent = new Intent(this, ListaCiudad.class);
            intent.putExtra("nombre", usuario);
            startActivity(intent);
        } else {
            // Las credenciales son inválidas, mostrar mensaje de error
            Toast.makeText(this,"No tiene cuenta, tiene que registrarse", Toast.LENGTH_SHORT).show();
        }
    }
}
