package com.example.juego_fruit_version_01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper  {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    //Nombre de la base de datos BD
    //Nombre de la tabla duck
    //Nombre de la tabla fruit
    //Columnas nombre y score que almacenarán los datos
    //Primera columna nombre
    //segunda columna score
    public void onCreate(SQLiteDatabase BD) {
        BD.execSQL("create table duck (nombre text, score int)");
        BD.execSQL("create table fruit (nombre text, score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int i, int i1) {

    }
}
