package com.example.juego_fruit_version_01;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.View;

import java.util.Random;

public class F_pantalla_BuildDuck_Duck1_Tablet {

    //Creamos los patos

    int CANTIDADDUCK = 8;
    Bitmap duck[] = new Bitmap[CANTIDADDUCK];

    int posicionX;//Variable que cambia según avanza los patos de derecha a izquierda
    int posicionY;//variable de arriba - abajo que mantiene el mismo valor 100
    int velocidad;//velocidad a la que va el pato
    int punteroPato;
    Random random;


    static int dWidth, dHeight;


    public F_pantalla_BuildDuck_Duck1_Tablet(Context context) {
        //para dar efecto optico debemos poner 4 de la misma imagen y 4 de la otra
        duck[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        duck[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        duck[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        duck[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela1);
        duck[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        duck[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        duck[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        duck[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.buildaduck_patovuela2);
        random = new Random();
        punteroPato = 0;
        resetPosition();
    }

    public Bitmap getBitmap(){

        return duck[punteroPato];
    }

    public int getWidth(){

        return duck[0].getWidth();
    }

    public int getHeight(){

        return duck[0].getHeight();
    }

    public void resetPosition(){
        //Donde aparece el pato por primera vez
        posicionX = F_pantalla_buildaduck_CanvasView.dWidth;
        System.out.println("Posicion en X " + posicionX);

        //La posicion máxima que puede aparecer el pato es 150
        posicionY = random.nextInt(150);
        System.out.println("Posicion en Y " + posicionY);

        //Hacemos que avance
        velocidad = 1 + random.nextInt(17);
        //velocidad = 1 + random.nextInt(17); la velocidad máxima va a ser 17 o valores inferiores
        System.out.println("Velocidad: " + velocidad);
    }

}
