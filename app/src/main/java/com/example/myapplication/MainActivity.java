package com.example.myapplication;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private DataAdapter mdataAdapter;

    private ProgressDialog PD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(0,0);
        auth=FirebaseAuth.getInstance();
       // db=FirebaseFirestore.getInstance();//

        final EditText email=(EditText)findViewById(R.id.email);
        final EditText pass=(EditText)findViewById(R.id.pass);
        Button sign_in=(Button)findViewById(R.id.signin);
        TextView register=(TextView)findViewById(R.id.register);
        TextView forget_pass=(TextView)findViewById(R.id.forget);




           sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // final String Email=email.getText().toString().trim();
                final String Pass=pass.getText().toString().trim();
                auth=FirebaseAuth.getInstance();
                final String Email=email.getText().toString().trim();
                if(Email.isEmpty())
                {
                    email.requestFocus();
                    Toast.makeText(MainActivity.this, "Email box is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.isEmpty())
                {
                    pass.requestFocus();
                    Toast.makeText(MainActivity.this, "Password should not be Empty", Toast.LENGTH_SHORT).show();
                }
                else if(Pass.length() < 6)
                {
                    pass.requestFocus();
                    Toast.makeText(MainActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }
                            else
                            {

                                Intent i=new Intent(MainActivity.this,Activity2.class);
                                SharedPreferences sharedPreferences = getSharedPreferences("data",0);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email",Email);
                                editor.commit();
                                startActivity(i);
                            }

                        }
                    });
                }
            }
        });



   /*   sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Activity2.class);
                startActivity(intent);
            }
        });*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        forget_pass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this,ForgetAndChangePasswordActivity.class);
                startActivity(intent);
            }
        });

    }

}
