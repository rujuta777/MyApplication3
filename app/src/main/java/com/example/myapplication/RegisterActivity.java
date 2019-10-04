
package com.example.myapplication;


import android.app.Notification;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisterActivity extends AppCompatActivity
{
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private FirebaseAuth auth;

    int flag=0;
    private ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth=FirebaseAuth.getInstance();
        final EditText namet, classt, divt, rollt, passt, mobilet;
        namet=(EditText)findViewById(R.id.name);
        classt=(EditText)findViewById(R.id.class1);
        divt=(EditText)findViewById(R.id.division);
        rollt=(EditText)findViewById(R.id.roll);
        passt=(EditText)findViewById(R.id.department);
        mobilet=(EditText)findViewById(R.id.mobile);
        final EditText emailt = (EditText) findViewById(R.id.email);
        Button reg=(Button)findViewById(R.id.reg_button);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=namet.getText().toString().trim();
                final String class1=classt.getText().toString().trim();
                final String div=divt.getText().toString().trim();
                final String roll=rollt.getText().toString().trim();
                final String Pass=passt.getText().toString().trim();
                final String mobile=mobilet.getText().toString().trim();
                final String email=emailt.getText().toString().trim();
                final DocumentReference noteRef=db.collection("Student").document(email);


                if(name.isEmpty())
                {
                    namet.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Name Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(class1.isEmpty())
                {
                    classt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Class block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(div.isEmpty())
                {
                    divt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Division Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(roll.isEmpty())
                {
                    rollt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Roll No Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.isEmpty())
                {
                    passt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Password Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(mobile.isEmpty())
                {
                    mobilet.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Mobile Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty())
                {
                    emailt.requestFocus();
                    Toast.makeText(RegisterActivity.this, "Email Block is Empty", Toast.LENGTH_SHORT).show();}
                else
                {
                    auth.createUserWithEmailAndPassword(email,Pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Registration falied...", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {

                                            Record1 record=new Record1( name,class1,div,roll, Pass, mobile,email, "0%");
                                            noteRef.set(record);
                                            noteRef.set(record).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(RegisterActivity.this, "Information is store Successfully", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(RegisterActivity.this, "Failed to store Information", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                            Toast.makeText(RegisterActivity.this, "Registration Successful...", Toast.LENGTH_SHORT).show();
                                            Intent i =new Intent(RegisterActivity.this,MainActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(0, 0);



                                    }
                                }
                            });
                }

            }
        });
    }
}