package com.example.myapplication;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetAndChangePasswordActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_and_change_password);
        final EditText user_email=(EditText)findViewById(R.id.useremail);
        Button Pass=(Button)findViewById(R.id.pass);
        Button back=(Button)findViewById(R.id.back);
        auth=FirebaseAuth.getInstance();
        Pass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String email=user_email.getText().toString().trim();
                auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgetAndChangePasswordActivity.this, "Password reset link sent to your registered mail...", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(ForgetAndChangePasswordActivity.this,MainActivity.class);
                            startActivity(i);
                            overridePendingTransition(0, 0);

                        }
                        else{
                            Toast.makeText(ForgetAndChangePasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });


    }
}
