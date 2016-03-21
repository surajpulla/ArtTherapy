package com.example.suraj.arttherapy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private ShakeEventListener mSensorListener;
    BackgroundSound mBackgroundSound = new BackgroundSound();
    Thread mythread;
    Intent intent;
    NotificationCompat.Builder notification;
    private static final int uniqueID=45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final TouchEventView t=new TouchEventView(getApplicationContext(),null);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //sending broadcast
        Intent intent1 = new Intent();
        intent1.setAction("com.myaction");
        sendBroadcast(intent1);

        //notificaiton
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

 intent=new Intent(this,MyIntentService.class);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeEventListener();
        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

            public void onShake() {

                startService(intent);
                Toast.makeText(MainActivity.this, "Shake!", Toast.LENGTH_SHORT).show();
                TouchEventView touchView = (TouchEventView) findViewById(R.id.view);
                touchView.clear();



            }
        });



    }
    public void broadcastIntent(View view){
        //Building the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("New Notification From Art Therapy");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Art Therapy");
        notification.setContentText("Click this to go to the app");

        //calling mainacticity on click of intent
        Intent in=new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Building notifiation issues or sending
        NotificationManager nm=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());

    }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);


    }

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
        //mBackgroundSound.cancel(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class BackgroundSound extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.eraser);
          //  player.setLooping(true); // Set looping
            player.setVolume(100,100);
            player.start();
            return null;
        }
    }
    public void send(View view){
        Intent intent1 = new Intent();
        intent1.setAction("com.myaction");
        sendBroadcast(intent1);
    }

}

