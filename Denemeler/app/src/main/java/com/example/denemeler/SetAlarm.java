package com.example.denemeler;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SetAlarm extends AppCompatActivity {

    private Button alarm,cancel;
    private TextView time,alarmtime;
    Handler handler=null;
    Runnable runnable=null;
    String timeN;
    private TimePickerDialog timePickerDialog;
    final static int islem_kodu = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        alarm=(Button) findViewById(R.id.button1);
        time=(TextView)findViewById(R.id.textView);
        cancel=(Button)findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(SetAlarm.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(SetAlarm.this, 1, intent, 0);

                alarmManager.cancel(pendingIntent);
                Toast.makeText(getApplicationContext(),"Cancelled Alarm!",Toast.LENGTH_SHORT).show();
                alarmtime.setText("\n\n\nClear!");
            }
        });
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                timePickerDialog = new TimePickerDialog(SetAlarm.this, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.setTitle("Set Alarm");
                timePickerDialog.show();
            }
        });
        alarmtime=(TextView)findViewById(R.id.textView2);

        final SimpleDateFormat shape=new SimpleDateFormat("dd.MM.yyyy HH.mm.ss");
        timeN = shape.format(new Date());
        time.setText(timeN);

        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                timeN=shape.format(new Date());
                time.setText(timeN);
                handler.postDelayed(runnable,500);
            }
        };
        runnable.run();

    }
    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hour, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hour);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if(calSet.compareTo(calNow) <= 0){

                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }};

    private void setAlarm(Calendar alarmCalender){
        Toast.makeText(getApplicationContext(),"Alarm Activated!",Toast.LENGTH_SHORT).show();
        String timer="\n \n \nAlarm : ";
        timer +=DateFormat.getTimeInstance(DateFormat.SHORT).format(alarmCalender.getTime());
        alarmtime.setText(timer);
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), islem_kodu, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmCalender.getTimeInMillis(), pendingIntent);

    }
}
