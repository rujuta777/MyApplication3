package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Nullable;

public class All_student_profile extends AppCompatActivity {
    DocumentReference documentReference;
    private FirebaseFirestore db;
    String[] answer=new String[1];
    DataAdapter2 mdataAdapter;
    RecyclerView rvlist1;
    List<Record3>userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button back=(Button)findViewById(R.id.back);
        userlist=new ArrayList<>();
        mdataAdapter=new DataAdapter2(userlist);
        setContentView(R.layout.activity_all_student_profile);
        db=FirebaseFirestore.getInstance();
        documentReference= db.collection("Student").document();
        rvlist1=findViewById(R.id.rvuserlist2);

        rvlist1.setHasFixedSize(true);
        rvlist1.setLayoutManager(new LinearLayoutManager(this));
        rvlist1.setAdapter(mdataAdapter);




        db.collection("Student").orderBy("rollt").addSnapshotListener(new EventListener<QuerySnapshot>() {


            @Override
            public void onEvent(QuerySnapshot documentSnapshots,  FirebaseFirestoreException e) {
                for(DocumentChange dc : documentSnapshots.getDocumentChanges())
                {
                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {
                        final Record3 rec=dc.getDocument().toObject(Record3.class);
                        db.collection("Attendance")
                                .addSnapshotListener(new EventListener<QuerySnapshot>()
                                {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                                        Double sum = 0.0;
                                        Double total = 0.0;
                                        for(DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()){
                                            total+=1.0;
                                            if(dc.getType()==DocumentChange.Type.ADDED){
                                                Map<String, Object> attend = dc.getDocument().getData();
                                                for (Map.Entry<String, Object> stringObjectEntry : attend.entrySet()) {

                                                    if (stringObjectEntry.getKey().contains(rec.getRollt())) {
                                                        if (stringObjectEntry.getValue().equals("P")) {
                                                            //Toast.makeText(Activity2.this, "Inside If...", Toast.LENGTH_SHORT).show();
                                                            sum += 1.0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        Double avg = ((sum / total) * 100);
                                        Record3 rec2=new
                                                Record3(rec.getNamet(), rec.getClasst(), rec.getDivt(), rec.getRollt(), rec.getMobilet(), rec.getEmailt(), avg.toString()+"%");
                                        userlist.add(rec2);
                                        mdataAdapter.notifyDataSetChanged();
                                    }
                                });

                    }
                }
            }
        });

    }
}
