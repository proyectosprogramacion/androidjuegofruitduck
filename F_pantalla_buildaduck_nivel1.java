package com.example.juego_fruit_version_01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class F_pantalla_buildaduck_nivel1 extends AppCompatActivity {

    //Creamos un objeto de la clase CanvasView para mostrar  en esta pantalla
    F_pantalla_buildaduck_CanvasView canvasView;;

    //String nombre_jugador; //almacena el nombre que recibimos desde el activity de bienvenida

    @Override
    //poner @Nullable Bundle savedInstanceState sino da error
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //La parte gráfica que se mostrará es el elemento canvas
        canvasView = new F_pantalla_buildaduck_CanvasView(this);
        setContentView(canvasView);

        //Obtengo el nombre del jugador
        //nombre_jugador = getIntent().getStringExtra("nombre_jugador");//La key estará en la clase C_pantalla_BuildDuck_nombre


        //Mostramos al usuario en que nivel esta
        Toast.makeText(this, "Nivel 1 - Tienes que conseguir 100 puntos",  Toast.LENGTH_SHORT).show();


    }
}
