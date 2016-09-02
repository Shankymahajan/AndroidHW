package shashank.androidalarmclock;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by shashank on 2/9/16.
 *  The receiver will start the following
 *  IntentService to send a standard notification to
 *  the user
 */
public class AlarmService extends IntentService {

    private  NotificationManager alarmNotificationManager ;

    public AlarmService() {
        super("AlarmSerice");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification("Wake up ! Wake Up!" );

    }

    private void sendNotification(String msg) {
        Log.d("AlarmService","Preparing to send notification... : " + msg);
        alarmNotificationManager = (NotificationManager)this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,new Intent(this, AlarmManager.class),0);

        NotificationCompat.Builder alarmNotificationBuilder=  new NotificationCompat.Builder(
           this).setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        alarmNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1,alarmNotificationBuilder.build());
        Log.d("AlarmService","Notification sent");


    }
}
