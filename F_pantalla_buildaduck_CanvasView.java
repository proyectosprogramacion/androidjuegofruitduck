package com.example.juego_fruit_version_01;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

//Extendemos la clase View
public class F_pantalla_buildaduck_CanvasView extends View {

    //Variable para crear un fondo de pantalla
    // Bitmap background;
    Rect rect;
    static int dWidth, dHeight;

    //Dibujamos las vidas
    // int vidas = 10;
    // Paint textPaintVidas = new Paint();
    // float TEXT_SIZE_VIDAS = 40;

    //Dibujamos los puntos
    int puntos = 0;
    // Paint textPaintPuntos = new Paint();
    // float TEXT_SIZE_PUNTOS = 40;

    //Dibujamos el nombre
    String nombre_jugador;
    Paint textNombreJugador = new Paint();
    float TEXT_SIZE_NOMBRE = 40;

    //Creamdos el hilo que se ejecuta en segundo plano
    //Hay que importar este import android.os.Handler;
    //El hilo permitira que se DIBUJE cada 30 milisegundos en la pantalla haciendo el efecto del movimiento
    Handler handler;
    Runnable runnable;
     final long UPDATE_MILLIS = 30;

    //Dibujamos los patos
    private static final int CANTIDADDUCK = 8;
    ArrayList<F_pantalla_BuildDuck_Duck1> duck1; //Contendrá los patos de la primera clase Duck
    // ArrayList<J_pantalla_builduck_Duck2> duck2; //Contendrá los patos de la segunda clase Duck
    //ArrayList<J_pantalla_builduck_Duck3> duck3; //Contendrá los patos de la segunda clase Duck

    //Incorporamos mecanismo de disparo
    // Bitmap pelota;
    // Bitmap raqueta;
    // float pelotaX; //posicion en X
    // float pelotaY; //posicion en Y

    //Variables recoger el movimiento del usuario
    // float sX, sY; //Recoge la posición del dedo cuando arrastra hacia abajo
    // float fX, fY; //Recoge la posición del dedo cuando arrastra lo mueve
    // float dX, dY;
    // float tempX, tempY;

    //Definimos una línea de tiro
    // Paint lineaDeTiro;

    //Para cambiar de pantalla según los puntos y la vida
    Context context;

    //Incorporamos la música
    // MediaPlayer acierto;
    //  MediaPlayer error;

    //Constructor principal
    public F_pantalla_buildaduck_CanvasView(Context context) {
        super(context);

        //Le indico donde esta la imagen que debe utilizar
        //background = BitmapFactory.decodeResource(getResources(), R.drawable.buildaduck_degradadoazulblanco);
        //Obtenemos el tamaño de la pantalla
        Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        //Creo un rectangulo con el tamaño de la pantalla QUE SERA EL TAMAÑO QUE OCUPE LA IMAGEN BACKGROUND
        rect = new Rect(0, 0, dWidth, dHeight);

        //Defino las características del texto de las vidas
        //textPaintVidas.setColor(Color.BLACK);
        //textPaintVidas.setFakeBoldText(true);
        // textPaintVidas.setTextSize(TEXT_SIZE_VIDAS);
        // textPaintVidas.setTextAlign(Paint.Align.LEFT);

        //Defino las características del texto de los puntos
        // textPaintPuntos.setColor(Color.BLACK);
        // textPaintPuntos.setFakeBoldText(true);//Conseguir textura bold
        // textPaintPuntos.setTextSize(TEXT_SIZE_PUNTOS);
        // textPaintPuntos.setTextAlign(Paint.Align.LEFT);

        //Defino las caracteristicas del texto del nombre
        textNombreJugador.setColor(Color.BLACK);
        textNombreJugador.setFakeBoldText(true);//Conseguir textura bold
        textNombreJugador.setTextSize(TEXT_SIZE_NOMBRE);
        textNombreJugador.setTextAlign(Paint.Align.LEFT);

        //Creamdos el hilo que se ejecuta en segundo plano
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };

        //Instanciamos en el constructor
        duck1 = new ArrayList<>();
        //duck2 = new ArrayList<>();
        //duck3 = new ArrayList<>();
        for (int i=0; i<1; i++){//i = 1 porque creo 1 pato
            F_pantalla_BuildDuck_Duck1 duck_1= new F_pantalla_BuildDuck_Duck1 (context);
            duck1.add(duck_1);

            //J_pantalla_builduck_Duck2 duck_2 = new J_pantalla_builduck_Duck2 (context);
            //duck2.add(duck_2);
            //J_pantalla_builduck_Duck3 duck_3 = new J_pantalla_builduck_Duck3 (context);
            //duck3.add(duck_3);
        }

        //Instanciamos la pelota y la raqueta
        //pelota = BitmapFactory.decodeResource(getResources(), R.drawable.pingpong_pelota);
        //raqueta = BitmapFactory.decodeResource(getResources(), R.drawable.buildaduck_diana);
        //pelotaX = 0;
        //pelotaY = 0;


        //Variables recoger el movimiento del usuario
        //sX= sY=0;
        //fX= fY=0;
        //dX = dY =0;
        //tempX= tempY=0;

        //instanciamos la linea de tiro
        //lineaDeTiro = new Paint();
        //lineaDeTiro.setColor(Color.BLACK);
        //lineaDeTiro.setStrokeWidth(15);

        //Instanciamos el contexto
        this.context = context;

        //Instanciamos en el constructor la musica
        //acierto = MediaPlayer.create(context, R.raw.inicio_acierto);
        //error = MediaPlayer.create(context, R.raw.inicio_error);

    }


    //Sobreescrimos el método onDraw al cual le pasamos un objeto canvas

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Obtengo el nombre del jugador
        nombre_jugador = ((Activity)context).getIntent().getStringExtra("nombre_jugador");//La key estará en la clase C_pantalla_matematicas_nombre

        /*
        if(vidas < 1){//Si hemos perdido las vidas, nos vamos a un nuevo Activity al que enviamos el nombre y los puntos
            Intent intent = new Intent(context, J_pantalla_buildaduck_GameOver.class);
            intent.putExtra("nombre_jugador", nombre_jugador);
            intent.putExtra("score", puntos);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
        */

        //Dibujo el fondo de la pantalla
        canvas.drawColor(Color.TRANSPARENT);
        //canvas.drawBitmap(background, null, rect, null);

        //Dibujo los puntos y las vidas
        //canvas.drawText("Puntuacion: "+ puntos, 20,40,textPaintPuntos);
        //canvas.drawText("Vidas: "+ vidas, 20,100,textPaintVidas);
        canvas.drawText("Jugador: "+ nombre_jugador, 20,160,textNombreJugador);

        //Dibujamos el pato de la clase Duck1
        for (int i=0; i<duck1.size(); i++){
            canvas.drawBitmap(duck1.get(i).getBitmap(), duck1.get(i).posicionX, duck1.get(i).posicionY, null);
            duck1.get(i).punteroPato++;
            //Cuando el punteroPato llegue a cero
            if(duck1.get(i).punteroPato > (CANTIDADDUCK -1 )){
                duck1.get(i).punteroPato = 0;
            }
            //La velocidad
            duck1.get(i).posicionX -= duck1.get(i).velocidad;

            if(duck1.get(i).posicionX < -duck1.get(i).getWidth()){
                duck1.get(i).resetPosition();//Metodo para resetear la posición cuando llegue al borde de la pantalla
                puntos ++;
                BaseDeDatos();

                //Si llega al borde de la pantalla quitamos una vida
                //vidas--;
                //Se ejecuta la música de error
                //if(error != null)
                 //   error.start();
                //}
            }


            //Colisiones pelota y pato 1
            /*
            if(pelotaX <= (duck1.get(i).duckX + duck1.get(i).getWidth())
                    && pelotaX + pelota.getWidth() >= duck1.get(i).duckX
                    && pelotaY <= (duck1.get(i).duckY + duck1.get(i).getHeight())
                    && pelotaY >= duck1.get(i).duckY){
                duck1.get(i).resetPosition();
                //Si colisiona le sumo un punto
                puntos++;
                //Se ejecuta la música de acierto
                if(acierto != null)
                    acierto.start();
            }
            */


        }



        //Creamdos el hilo que se ejecuta en segundo plano cada 30 milisegundos y que dibuja lo que hay en onDraw
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    public void BaseDeDatos(){
        System.out.println("Entrando en el método BaseDeDatos Duck");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();//apertura de la base de datos en modo lectura y escritura

        //Creamos una consulta  a la base de datos del nombre del usuario
        Cursor consulta = BD.rawQuery(
                "select * from duck where score  = (select max (score) from duck)", null );
        System.out.println("Muestra el resultado del score" + consulta);

        //Condición para mostrar el resultado de la consulta
        if (consulta.moveToFirst()) {//si hay datos
            String temp_nombre = consulta.getString(0);//La columna del nombre es 0
            System.out.println("Muestra el nombre" + temp_nombre);

            String temp_score = consulta.getString(1);//la columna del score es la 1
            System.out.println("Muestra el score" + temp_score);

            //Para hacer comparaciones tenemos que pasar de un tipo String a un un tipo Int
            int bestScore = Integer.parseInt(temp_score);
            //Verificamos que el score actual es mayor que un dato registrado
            if (puntos > bestScore) {
                ContentValues modificacion = new ContentValues();
                modificacion.put("nombre", nombre_jugador);
                modificacion.put("score", puntos);
                //Insertamos los datos en la base de datos
                BD.update("duck", modificacion, "score =" + bestScore, null);
            }
            //cerramos la base de datos
            BD.close();
        }else{ //Si no encontramos ningún registro
            ContentValues insertar = new ContentValues();
            insertar.put("nombre", nombre_jugador);
            insertar.put("score", puntos);

            //Insertamos los datos en la base de datos
           BD.insert("duck", null, insertar);
            //cerramos la base de datos
            BD.close();
        }
    }
}
