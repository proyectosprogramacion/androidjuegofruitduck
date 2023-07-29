package com.example.juego_fruit_version_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pantallaBienvenido(View view) {
        Intent intent = new Intent(this, A_pantalla_eleccion_juego.class);
        startActivity(intent);
    }
}
