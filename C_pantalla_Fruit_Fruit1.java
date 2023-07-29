package com.example.juego_fruit_version_01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class C_pantalla_Fruit_Fruit1 {

    //Creamos los patos
    int CANTIDADFRUIT = 8;
    Bitmap fruit[] = new Bitmap[CANTIDADFRUIT];

    int posicionX;//Variable que cambia según avanza los patos de derecha a izquierda
    int posicionY;//variable de arriba - abajo que mantiene el mismo valor 100
    int velocidad;//velocidad a la que va el pato
    int punteroFruit;
    Random random;

    public C_pantalla_Fruit_Fruit1(Context context) {
        //para dar efecto optico debemos poner 4 de la misma imagen y 4 de la otra
        fruit[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        fruit[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.fruitbasket_apple);
        random = new Random();
        punteroFruit = 0;
        resetPosition();
    }

    public Bitmap getBitmap(){

        return fruit[punteroFruit];
    }

    public int getWidth(){

        return fruit[0].getWidth();
    }

    public int getHeight(){

        return fruit[0].getHeight();
    }

    public void resetPosition(){
        //La posiciónX que valdrá desde 0 hasta el ancho de la pantalla
        posicionX = random.nextInt(C_pantalla_Fruit_CanvasView.dWidth);
        System.out.println("Posicion en X " + posicionX);

        //Donde aparece la fruta por primera vez
        posicionY = 10;
        System.out.println("Posicion en Y " + posicionY);

        //Hacemos que avance de abajo arriba
        velocidad = 1 - random.nextInt(6);
        //velocidad = 1 + random.nextInt(17); la velocidad máxima va a ser 17 o valores inferiores
        System.out.println("Velocidad: " + velocidad);
    }

}
