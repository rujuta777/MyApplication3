package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class Attendance extends AppCompatActivity
{

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        db=FirebaseFirestore.getInstance();

        Button profile=(Button)findViewById(R.id.student_Profile);
        Button back=(Button)findViewById(R.id.back);
        Button get_att=(Button)findViewById(R.id.get_att);
        Button add_notice=(Button)findViewById(R.id.att_notice_add);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(Attendance.this,Activity2.class);
               startActivity(i);
               overridePendingTransition(0,0);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Attendance.this,All_student_profile.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
        get_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Attendance.this,Get_Attendance.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
        add_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Attendance.this,Add_notice.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
    }
}
