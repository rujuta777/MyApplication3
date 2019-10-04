package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Event_Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event__information);
        String topic,event,venue,conductor,time,desc;
        Intent i=getIntent();

        topic=i.getStringExtra("topic");
        venue=i.getStringExtra("venue");
        event=i.getStringExtra("name");
        conductor=i.getStringExtra("organiser");
        time=i.getStringExtra("time");
        desc=i.getStringExtra("description");

        TextView topic1,event1,venue1,conductor1,time1,desc1;
        topic1=(TextView)findViewById(R.id.value_topic);
        venue1=(TextView)findViewById(R.id.value_venue);
        event1=(TextView)findViewById(R.id.value_event);
        conductor1=(TextView)findViewById(R.id.value_conductor);
        time1=(TextView)findViewById(R.id.value_time);
        desc1=(TextView)findViewById(R.id.value_desc);

        topic1.setText(topic);
        venue1.setText(venue);
        event1.setText(event);
        conductor1.setText(conductor);
        time1.setText(time);
        desc1.setText(desc);

    }
}
