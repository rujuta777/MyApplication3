package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class landf extends AppCompatActivity {

    Button lost,back ;

    private static final String TAG ="Something" ;
    //private FirebaseAuth auth;
    DocumentReference documentReference;
    private FirebaseFirestore db;

    data mdataAdapter;
    RecyclerView rvlist;
    List<Record> userlist;
    private String object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landf);

        lost=(Button)findViewById(R.id.button3);
        back=(Button)findViewById(R.id.button);
        lost.setOnClickListener(( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(landf.this,lost.class);
                startActivity(i);
            }
        }));



        back.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {



                                        userlist=new ArrayList<>();
                                        mdataAdapter=new data(userlist);

                                        db=FirebaseFirestore.getInstance();
                                        documentReference= db.collection("Event").document();
                                        // auth= FirebaseAuth.getInstance();

                                        rvlist=findViewById(R.id.rvlist);

                                        rvlist.setHasFixedSize(true);
                                        rvlist.setLayoutManager(new LinearLayoutManager(landf.this));
                                        rvlist.setAdapter(mdataAdapter);

                                        db.collection("landf").get().
                                                addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                         @Override
                                                                         public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                                             for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                                                 Record record = documentSnapshot.toObject(Record.class);
                                                                                 mdataAdapter.notifyDataSetChanged();


                                                                             }
                                                                         }
                                                                     }

                                                );

                 /*   db.collection("landf")
                            .orderBy("datet")
                            .addSnapshotListener(new EventListener<QuerySnapshot>()
                    {


                        @Override
                        public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                            for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                            {
                                if(dc.getType() == DocumentChange.Type.ADDED && dc!=null)
                                {
                                    Record record=dc.getDocument().toObject(Record.class);
                                    boolean add = userlist.add(record);
                                    mdataAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });*/

                                        Intent i =new Intent(landf.this,report.class);
                                        startActivity(i);

                                    }
                                }
        );
        Intent i= getIntent();
        object=i.getStringExtra("object");


    }
}



