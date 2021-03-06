package shashank.androidalarmclock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by shashank on 2/9/16.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity inst = MainActivity.instance();
        inst.setAlarmText("Alarm ! Wake up ! wake up!");

        //this will sound the alarm tone
        //this will sound the alarm once , if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

//        }
////        Ringtone ringtone = RingtoneManager.getRingtone(context,alarmUri);
////        ringtone.play();
//


            //this will send a notification message
            ComponentName componentName = new ComponentName(context.getPackageName(),
                    AlarmService.class.getName());
            startWakefulService(context, (intent.setComponent(componentName)));
            setResultCode(Activity.RESULT_OK);
        }
    }
}

