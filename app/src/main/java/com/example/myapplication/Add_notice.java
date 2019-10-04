package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.jar.Attributes;

public class Add_notice extends AppCompatActivity {

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    String name,Add;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);

        final EditText name_add_notice=(EditText)findViewById(R.id.notice_add_name);
        final EditText add_notice=(EditText)findViewById(R.id.add_notice);
        Button save_notice=(Button)findViewById(R.id.save_notice);


        save_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar rightNow = Calendar.getInstance();
                Integer currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                currentHour=(currentHour+23)%24;
                Integer currentmin=rightNow.get(Calendar.MINUTE);
                currentmin+=59%60;

                name=name_add_notice.getText().toString().trim();
                Add=add_notice.getText().toString().trim();


                Intent i=new Intent(Add_notice.this,Activity2.class);
                i.putExtra("name",name);

                if(name.isEmpty())
                {
                    name_add_notice.requestFocus();
                    Toast.makeText(Add_notice.this, "Please Add Name", Toast.LENGTH_SHORT).show();
                }else if(Add.isEmpty())
                {
                    add_notice.requestFocus();
                    Toast.makeText(Add_notice.this, "Please Add notice", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String time=currentHour.toString()+currentmin.toString();
                    final DocumentReference noteRef=db.collection("Notice").document(name);
                    Record4 record4=new Record4(name,Add, time);
                    noteRef.set(record4);
                    noteRef.set(record4).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(Add_notice.this, "Notice is added ", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Add_notice.this, "Something went wrong,try again", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent intent=new Intent(Add_notice.this,Attendance.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            }
        });
    }
}