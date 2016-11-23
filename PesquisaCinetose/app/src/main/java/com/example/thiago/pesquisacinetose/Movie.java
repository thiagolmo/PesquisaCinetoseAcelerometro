package com.example.thiago.pesquisacinetose;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by Thiago on 12/11/2016.
 */
//https://www.youtube.com/watch?v=6pkeDrNR1Hk
public class Movie extends Activity implements SensorEventListener{
    Bitmap image;
    SensorManager sm;
    Draw ourView;
    float x, y, sensorX, sensorY;

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    public class Draw extends SurfaceView implements Runnable{

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = true;

        public Draw(Context context){
            super(context);
            ourHolder = getHolder();
        }

        public void pause(){
            isRunning = false;
            while(true){
                try {
                    ourThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume(){
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run(){
            while(isRunning) {
                //see if surface is valid
                if (!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                float startX = -200;
                float startY = -250;
                canvas.drawBitmap(image, startX+sensorX* 15, startY+sensorY * 15, null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = getApplicationContext();

        final Toast tag = Toast.makeText(getBaseContext(), "Como dizia o poeta\n" +
                "\n" +
                "Quem já passou\n" +
                "Por esta vida e não viveu\n" +
                "Pode ser mais, mas sabe menos do que eu\n" +
                "Porque a vida só se dá\n" +
                "Pra quem se deu\n" +
                "Pra quem amou, pra quem chorou\n" +
                "Pra quem sofreu, ai\n" +
                "Quem nunca curtiu uma paixão\n" +
                "Nunca vai ter nada, não\n" +
                "Não há mal pior\n" +
                "Do que a descrença\n" +
                "Mesmo o amor que não compensa\n" +
                "É melhor que a solidão\n" +
                "Abre os teus braços, meu irmão, deixa cair\n" +
                "Pra que somar se a gente pode dividir?\n" +
                "Eu francamente já não quero nem saber\n" +
                "De quem não vai porque tem medo de sofrer\n" +
                "Ai de quem não rasga o coração\n" +
                "Esse não vai ter perdão",Toast.LENGTH_SHORT);

        tag.show();

        new CountDownTimer(900000, 10)
        {

            public void onTick(long millisUntilFinished) {tag.show();}
            public void onFinish() {tag.show();}

        }.start();
        // see if device has an accelerometer
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() !=0){
            //Setup a sensor
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);

        }
        image= BitmapFactory.decodeResource(getResources(),R.drawable.bettyskateboard);
        x = y = sensorX = sensorY = 0;
        ourView = new Draw(this);
        ourView.resume();
        setContentView(ourView);
    }
}
