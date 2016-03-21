package com.example.suraj.arttherapy;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class UserPresentBroadcastReceiver extends BroadcastReceiver {
    NotificationCompat.Builder notification;
    private static final int uniqueID=45612;
;
    public UserPresentBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        /*Sent when the user is present after
         * device wakes up (e.g when the keyguard is gone)
         * */
        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.d("helloworld","recieving the broadcast ");       }
//        throw new UnsupportedOperationException("Not yet implemented");
        //notificaiton
        notification=new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);
        //Building the notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("New Notification From Art Therapy");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Art Therapy");
        notification.setContentText("Show Us Your Artistic Skills");

        //calling mainacticity on click of intent
        Intent in = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,in,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Building notifiation issues or sending
        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
        notification.setAutoCancel(true);


    }
}
