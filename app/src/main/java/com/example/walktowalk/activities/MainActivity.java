package com.example.walktowalk.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walktowalk.R;
import com.example.walktowalk.modelo.ConfiguracionDB;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void irLogin(View view) {
        Intent intent = new Intent(this, ListaCiudad.class);
        startActivity(intent);
    }
}