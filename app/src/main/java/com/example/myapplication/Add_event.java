package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Locale;

public class Add_event extends AppCompatActivity {
    private FirebaseAuth auth;
    private int notificationId=1;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        auth= FirebaseAuth.getInstance();
        ImageButton save=(ImageButton)findViewById(R.id.save_but);
        final EditText event_name=(EditText)findViewById(R.id.name);
        final EditText topic=(EditText)findViewById(R.id.topic);
        final EditText conductor=(EditText)findViewById(R.id.conduct);
        final EditText venue=(EditText)findViewById(R.id.venue);
        final EditText desc=(EditText)findViewById(R.id.desc);
        final TimePicker tt=(TimePicker)findViewById(R.id.time);
        final DatePicker date=(DatePicker)findViewById(R.id.date) ;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String eventt=event_name.getText().toString().trim();
                final String topict=topic.getText().toString().trim();
                String conductort=conductor.getText().toString().trim();
                String venuet=venue.getText().toString().trim();
                String desct=desc.getText().toString().trim();

                Integer hour= tt.getHour();
                Integer min=tt.getMinute();
                final String timet= hour.toString()+":"+min.toString();

                Integer day=date.getDayOfMonth();
                Integer month=date.getMonth();
                Integer month1=month+1;
                Integer year=date.getYear();
                String datet=day.toString()+"-"+month1.toString()+"-"+year.toString();

                final DocumentReference noteRef=db.collection("Event").document("topict");
                if(eventt.isEmpty())
                {
                    event_name.requestFocus();
                    Toast.makeText(Add_event.this, "Event Name Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(topict.isEmpty())
                {
                    topic.requestFocus();
                    Toast.makeText(Add_event.this, "Topic Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(conductort.isEmpty())
                {
                    conductor.requestFocus();
                    Toast.makeText(Add_event.this, "Conductor Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(venuet.isEmpty())
                {
                    venue.requestFocus();
                    Toast.makeText(Add_event.this, "Venue Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(datet.isEmpty())
                {
                    date.requestFocus();
                    Toast.makeText(Add_event.this, "Date Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(timet.isEmpty())
                {
                    tt.requestFocus();
                    Toast.makeText(Add_event.this, "Time Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Record2 record=new Record2( eventt,topict,conductort,venuet,datet,timet,desct);
                    //noteRef.set(record);

                    noteRef.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            //    Toast.makeText(Add_event.this, "Event Information is store Successfully", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener()
                    {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(Add_event.this, "Failed to store Event Information", Toast.LENGTH_SHORT).show();
                            Intent in =new Intent(Add_event.this,EventActivity.class);
                            startActivity(in);
                            overridePendingTransition(0, 0);

                        }
                    });
                    Toast.makeText(Add_event.this, "Registration Successful...", Toast.LENGTH_SHORT).show();

                    Intent i=new Intent(Add_event.this,AlarmReceiver.class);

                    i.putExtra("Topic",topict);
                    i.putExtra("Venue",venuet);
                    i.putExtra("name",eventt);
                    i.putExtra("organiser",conductort);
                    i.putExtra("time",timet);
                    i.putExtra("description",desct);


                    PendingIntent alarmintent=PendingIntent.getBroadcast(Add_event.this,1,i,0);

                    Integer cur_hour=tt.getCurrentHour();
                    Integer cur_min=tt.getCurrentMinute();
                    Integer cur_day=date.getDayOfMonth();
                    Integer cur_month=date.getMonth();
                    Integer cur_year=date.getYear();

                    Calendar startTime= Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY,cur_hour);
                    startTime.set(Calendar.MINUTE,cur_min);
                    startTime.set(Calendar.SECOND,0);

                    startTime.set(Calendar.DAY_OF_MONTH,cur_day);
                    startTime.set(Calendar.MONTH,cur_month);
                    startTime.set(Calendar.YEAR,cur_year);

                    AlarmManager alarm=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    long alarmStartTime=startTime.getTimeInMillis();
                    alarm.setExact(AlarmManager.RTC_WAKEUP,alarmStartTime,alarmintent);


                    Intent in =new Intent(Add_event.this,EventActivity.class);
                    startActivity(in);
                    overridePendingTransition(0, 0);


                }
            }
        });
    }
}
