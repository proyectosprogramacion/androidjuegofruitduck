package com.example.juego_fruit_version_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class C_pantalla_Fruit_nombre extends AppCompatActivity {
    //Variables para establecer la relacion parte lógica-gráfica
    private EditText editText;
    private TextView textView;

    //Mediaplayer
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_pantalla_fruit_nombre);

        //Establecemos la relacion parte lógica-gráfica
        editText = findViewById(R.id.etNombre);
        textView = findViewById(R.id.bestScore);

        //Nombre de la base de datos BD
        //Nombre de la tabla fruit
        //Columnas nombre y score que almacenarán los datos
        //Primera columna nombre
        //segunda columna score

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        //Objeto de la clase SQlite que escriba nuestra base de datos
        SQLiteDatabase BD = admin.getWritableDatabase();

        //Creamos una consulta  a la base de datos del nombre del usuario
        Cursor consulta = BD.rawQuery(
                "select * from fruit where score  = (select max (score) from fruit)", null );
        System.out.println("Muestra el resultado del score" + consulta);

        //Condición para mostrar el resultado de la consulta
        if (consulta.moveToFirst()){//si hay datos
            String temp_nombre = consulta.getString(0);//La columna del nombre es 0
            System.out.println("Muestra el nombre" + temp_nombre);

            String temp_score = consulta.getString(1);//la columna del score es la 1
            System.out.println("Muestra el score" + temp_score);
            //Mostramos los valores en el texView
            textView.setText("Record: " + temp_score + " de: " + temp_nombre);
            //cerramos la base de datos
            BD.close();
        }else{//Si no hay un valor en la base de datos
            System.out.println("No hay nada en la base de datos");
            BD.close();
        }





        //Incorporamos la música deseada a este  objeto
        mediaPlayer = MediaPlayer.create(this, R.raw.fruit_musicainicial);

        //La iniciamos
        mediaPlayer.start();
        //Reproducción en bucle
        mediaPlayer.setLooping(true);

    }
    //Metodo botón jugar
    public void Jugar(View view) {
        //Verificamos que el jugador ha escrito su nombre
        String nombre = editText.getText().toString();

        if (!nombre.equals("")) {//Si ha escrito algo en el nombre
            //paramos la música
            mediaPlayer.stop();
            //destruye el objeto de la clase mediaplayer para liberar recursos
            mediaPlayer.release();

            //Abrimos el núevo activity
            Intent intent = new Intent(this, C_pantalla_fruitbasket_nivel1.class);
            //Debemos enviar  el nombre del jugador al pasar al siguiente activity
            intent.putExtra("nombre_jugador", nombre);
            startActivity(intent);

            //Finalizamos la activity  actual
            finish();

        } else {//Si no ha escrito un nombre
            Toast.makeText(this, "Debes escribir tu nombre para iniciar el juego.", Toast.LENGTH_SHORT).show();

            //Abre el menú teclado para escribir
            editText.requestFocus(); //Será donde este el foco
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    //Metodo para controlar el botón back de la barra inferior de la pantalla
    @Override
    public void onBackPressed() {
        //paramos la música
        mediaPlayer.stop();
        //destruye el objeto de la clase mediaplayer para liberar recursos
        mediaPlayer.release();

        //Volvemos a la activity anterior
        Intent intent = new Intent(this, A_pantalla_eleccion_juego.class);
        startActivity(intent);
        //Finalizamos la activity  actual
        finish();
    }
}
