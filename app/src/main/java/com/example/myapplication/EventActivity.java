package com.example.myapplication;



import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.events.Event;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

public class EventActivity extends AppCompatActivity {
    private static final String TAG ="Something" ;
    //private FirebaseAuth auth;
    DocumentReference documentReference;
    private FirebaseFirestore db;

    DataAdapter mdataAdapter;
    RecyclerView rvlist;
    List<Record2>userlist;
    final Calendar mycal=Calendar.getInstance();
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userlist=new ArrayList<>();
        mdataAdapter=new DataAdapter(userlist);
        setContentView(R.layout.activity_event);
        db=FirebaseFirestore.getInstance();
        documentReference= db.collection("Event").document();
        // auth= FirebaseAuth.getInstance();
        ImageButton add_event=(ImageButton)findViewById(R.id.plus_but);
        Button atted=(Button)findViewById(R.id.attendance_but);
        Button lost=(Button)findViewById(R.id.lost_but);
        Button event=(Button)findViewById(R.id.event_but);
        Button back =(Button)findViewById(R.id.back);
        rvlist=findViewById(R.id.rvuserlist);

        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(mdataAdapter);

        Calendar c = Calendar.getInstance();
        final String cur_time=c.getTime().toString();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

        Calendar rightNow = Calendar.getInstance();
        Integer currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        Integer currentmin=rightNow.get(Calendar.MINUTE);

        final String time=currentHour.toString()+currentmin.toString();
        final Integer time1=Integer.parseInt(time);

        String myformat="dd/MM/yy";
        java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat(myformat, Locale.US);
        final String mydate=sdf.format(mycal.getTime());
        final String Mydate=mydate.replace('/', '-');

        db.collection("Event").orderBy("datet").addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                {
                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {
                        Record2 record=dc.getDocument().toObject(Record2.class);
                        userlist.add(record);
                        mdataAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        atted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,Activity2.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,EventActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,Activity2.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,Add_event.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });
    }
}


/*


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.events.Event;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nullable;

public class EventActivity extends AppCompatActivity {
    private static final String TAG ="Something" ;
    //private FirebaseAuth auth;
    DocumentReference documentReference;
    private FirebaseFirestore db;

    DataAdapter mdataAdapter;
    RecyclerView rvlist;
    List<Record2>userlist;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userlist=new ArrayList<>();
        mdataAdapter=new DataAdapter(userlist);
        setContentView(R.layout.activity_event);
        db=FirebaseFirestore.getInstance();
        documentReference= db.collection("Event").document();
        // auth= FirebaseAuth.getInstance();
        ImageButton add_event=(ImageButton)findViewById(R.id.plus_but);
        Button atted=(Button)findViewById(R.id.attendance_but);
        Button lost=(Button)findViewById(R.id.lost_but);
        Button event=(Button)findViewById(R.id.event_but);
        Button back =(Button)findViewById(R.id.back);
        rvlist=findViewById(R.id.rvuserlist);

        rvlist.setHasFixedSize(true);
        rvlist.setLayoutManager(new LinearLayoutManager(this));
        rvlist.setAdapter(mdataAdapter);

        Calendar c = Calendar.getInstance();
        final String cur_time=c.getTime().toString();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");


        db.collection("Event").orderBy("datet").addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                {
                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {
                        Record2 record=dc.getDocument().toObject(Record2.class);
                        userlist.add(record);
                        mdataAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        atted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,Activity2.class);
                startActivity(i);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,EventActivity.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,Activity2.class);
                startActivity(i);
            }
        });
        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EventActivity.this,Add_event.class);
                startActivity(i);
            }
        });

    }
}

 */