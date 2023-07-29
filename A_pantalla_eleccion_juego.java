package com.example.juego_fruit_version_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class A_pantalla_eleccion_juego extends AppCompatActivity implements View.OnClickListener {

    Button btnFruitBasket, btnBuildDuck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_apantalla_eleccion_juego);

        //Asociamos cada boton con su parte gráfica
        btnFruitBasket = (Button) findViewById(R.id.btnFruitBasket);
        btnFruitBasket.setOnClickListener(this);
        btnBuildDuck = (Button) findViewById(R.id.btnBuildDuck);
        btnBuildDuck.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFruitBasket:
                Intent intent = new Intent(this, C_pantalla_Fruit_nombre.class);
                startActivity(intent);
                break;
            case R.id.btnBuildDuck:
                Intent intent1 = new Intent(this, F_pantalla_BuildDuck_nombre.class);
                startActivity(intent1);
                break;
            default:
                break;
        }


    }

    //Metodo para controlar el botón back de la barra inferior de la pantalla
    @Override
    public void onBackPressed() {
        //Si lo dejamos sin nada, no funcionará evitando volver a la pantalla de Bienvenida
    }
}
