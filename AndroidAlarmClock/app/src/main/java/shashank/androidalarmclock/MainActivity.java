package shashank.androidalarmclock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends Activity {

    AlarmManager alarmManager ;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static MainActivity inst;
    private TextView alarmTextView ;


    public static MainActivity instance(){
        return  inst;

    }

    @Override
    protected void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTimePicker = (TimePicker) findViewById(R.id.alarm_time_picker);
        alarmTextView =(TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarm_toggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    public  void onToggleClicked(View view){
        if (((ToggleButton) view).isChecked()){
            Log.d("MyActivity","Alarm On");
           // Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY,alarmTimePicker.getCurrentHour());
//            calendar.set(Calendar.MINUTE,alarmTimePicker.getCurrentMinute());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            // Set the alarm's trigger time to 8:30 a.m.
            calendar.set(Calendar.HOUR_OF_DAY, 4);
            calendar.set(Calendar.MINUTE, 3);
            Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0 ,intent,0);
            alarmManager.set(AlarmManager.RTC,calendar.getTimeInMillis(),pendingIntent);
        }
        else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("Main Activity", "Alarm off");

        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }
}
