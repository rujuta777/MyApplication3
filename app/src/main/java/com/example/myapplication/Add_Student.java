package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Add_Student extends AppCompatActivity {

    private FirebaseFirestore db;
    private ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__student);
        db=FirebaseFirestore.getInstance();

        final EditText namet, classt, divt, rollt, emailt, mobilet;
        namet=(EditText)findViewById(R.id.name_student);
        classt=(EditText)findViewById(R.id.class1_student);
        divt=(EditText)findViewById(R.id.division_student);
        rollt=(EditText)findViewById(R.id.roll_student);

        mobilet=(EditText)findViewById(R.id.mobile_student);
        emailt= (EditText) findViewById(R.id.email_student);
        Button regi=(Button)findViewById(R.id.reg_button_student);

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=namet.getText().toString().trim();
                final String class1=classt.getText().toString().trim();
                final String div=divt.getText().toString().trim();
                final String Roll=rollt.getText().toString().trim();

                final String mobile=mobilet.getText().toString().trim();
                final String email=emailt.getText().toString().trim();
                final DocumentReference noteRef=db.collection("Profile").document(email);

             /* Intent i=new Intent(getApplicationContext(),Activity2.class);
                i.putExtra("Name",name);
                i.putExtra("roll_no",roll);*/

                if(name.isEmpty())
                {
                    namet.requestFocus();
                    Toast.makeText(Add_Student.this, "Name Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(class1.isEmpty())
                {
                    classt.requestFocus();
                    Toast.makeText(Add_Student.this, "Class block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(div.isEmpty())
                {
                    divt.requestFocus();
                    Toast.makeText(Add_Student.this, "Division Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Roll.isEmpty())
                {
                    rollt.requestFocus();
                    Toast.makeText(Add_Student.this, "Roll No Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(mobile.isEmpty())
                {
                    mobilet.requestFocus();
                    Toast.makeText(Add_Student.this, "Mobile Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty())
                {
                    emailt.requestFocus();
                    Toast.makeText(Add_Student.this, "Email Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Record3 rec=new Record3( name,class1,div,Roll, mobile,email, "0");
                    noteRef.set(rec);
                    noteRef.set(rec).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Add_Student.this, "Information is store Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_Student.this, "Failed to store Information", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(Add_Student.this, "Registration Successful...", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(Add_Student.this,Attendance.class);
                    startActivity(i);
                    overridePendingTransition(0,0);


                }

            }
        });
    }
}
/*
   */