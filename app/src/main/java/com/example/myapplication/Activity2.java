package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.DecimalFormat;
import android.icu.text.Edits;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Nullable;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Activity2 extends AppCompatActivity {

    private FirebaseFirestore db;

    TextView textdisplay;
    TextView name_pop, roll_pop, att_pop;

    String display, display2, roll, roll1;
    Double avg;
    Button pro;
    int flag;

    DocumentReference documentReference;
    // private FirebaseFirestore db;
    DataAdapter3 mdataAdapter;
    RecyclerView rvlist;
    List<Record4> userlist = new ArrayList<>();
    Dialog myDialog;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        if (flag == 1)
        {
            Intent intent = new Intent(this,Activity2.class);
            startActivityForResult(intent, 0);
            overridePendingTransition(0, 0);

            finish();
        }

        final String emailt;
        final Button att = (Button) findViewById(R.id.attendance_but);
        Button lost = (Button) findViewById(R.id.lost_but);
        Button event = (Button) findViewById(R.id.event_but);
        Button back = (Button) findViewById(R.id.back);
        pro = (Button) findViewById(R.id.myprofile);
        textdisplay = (TextView) findViewById(R.id.textDisplay);
        mdataAdapter = new DataAdapter3(userlist);
        rvlist = findViewById(R.id.rvlist3);
        db = FirebaseFirestore.getInstance();

        myDialog = new Dialog(this);

        myDialog.setContentView(R.layout.custompopup);
        name_pop = (TextView) myDialog.findViewById(R.id.namevalue_pop);
        roll_pop = (TextView) myDialog.findViewById(R.id.rollvalue_pop);
        att_pop = (TextView) myDialog.findViewById(R.id.attvalue_pop);


        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(mdataAdapter);

        Calendar rightNow = Calendar.getInstance();
        Integer currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

        Integer currentmin=rightNow.get(Calendar.MINUTE);
        final String timet = currentHour.toString()+currentmin.toString();
        final Integer time=Integer.parseInt(timet);


        SharedPreferences sharedPreferences = getSharedPreferences("data",0);
        emailt = sharedPreferences.getString("email","");
        db.collection("Student")
                .addSnapshotListener(new EventListener<QuerySnapshot>()
                {


                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        for (DocumentChange dc : documentSnapshots.getDocumentChanges()) {
                            //Toast.makeText(Activity2.this, "Inside For Loop", Toast.LENGTH_SHORT).show();
                            if (dc.getType() == DocumentChange.Type.ADDED && !documentSnapshots.isEmpty()) {

                                Record1 record = dc.getDocument().toObject(Record1.class);
                                if (record.getEmailt().equals(emailt)) {
                                    //Toast.makeText(Activity2.this, "Inside If Block", Toast.LENGTH_SHORT).show();
                                    display = "Name :  " + record.getNamet() + "\n" + "Roll_No : " + record.getRollt();
                                    roll = record.getRollt();
                                }
                            }
                        }
                        textdisplay.setText(display);
                    }
                });


//        documentReference= db.collection("Notice").document();

        att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(emailt.equals("rujutarajput1999@gmail.com"))
                {
                    Intent intent = new Intent(Activity2.this, Attendance.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                }
                else
                {
                    Toast.makeText(Activity2.this, "You can not Access this", Toast.LENGTH_SHORT).show();
                }
            }
        });


        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, EventActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);

            }
        });

        pro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                db.collection("Attendance")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                Double sum = 0.0;
                                Double total = 0.0;
                                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                                    total += 1.0;
                                    if (dc.getType() == DocumentChange.Type.ADDED && !queryDocumentSnapshots.isEmpty()) {
                                        Map<String, Object> attend = dc.getDocument().getData();
                                        for (Map.Entry<String, Object> stringObjectEntry : attend.entrySet()) {
                                            if (stringObjectEntry.getKey().contains(roll)) {
                                                if (stringObjectEntry.getValue().equals("P")) {
                                                    sum += 1.0;
                                                }
                                            }
                                        }
                                    }
                                }
                                avg = (sum / total) * 100;
                                display2= df2.format(avg);
                                att_pop.setText(display2);
                                display2 = display + "\n" + display2 + '%';

                            }
                        });
                db.collection("Student")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {


                            @Override
                            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                                for (DocumentChange dc : documentSnapshots.getDocumentChanges()) {
                                    //Toast.makeText(Activity2.this, "Inside For Loop", Toast.LENGTH_SHORT).show();
                                    if (dc.getType() == DocumentChange.Type.ADDED && !documentSnapshots.isEmpty()) {

                                        Record1 record = dc.getDocument().toObject(Record1.class);
                                        if (record.getEmailt().equals(emailt)) {
                                            name_pop.setText(record.getNamet());
                                            roll_pop.setText(record.getRollt());


                                        }
                                    }
                                }

                            }
                        });
                // myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialog.show();

            }
        });

        db.collection("Notice")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                        {

                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                Record4 r=dc.getDocument().toObject(Record4.class);
                                String name=r.getName();
                                int past_time=Integer.parseInt(r.getTime().replace(":",""));
                                if(past_time<=time){
                                    db.collection("Notice").document(name).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    });
                                }

                                //    Toast.makeText(Activity2.this, "Inside if " , Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                });


        db.collection("Notice")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                        for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                        {
                            if(dc.getType() == DocumentChange.Type.ADDED)
                            {
                                Record4 r=dc.getDocument().toObject(Record4.class);
                                //  Log.d("record", "onEvent: "+r);
                                userlist.add(r);
                                mdataAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }


}


