package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.myapplication.R.id.checkbox;
import static com.example.myapplication.R.id.getadate;

public class Get_Attendance extends AppCompatActivity {

    EditText getdate;
    CheckBox check[];
    Button store;
    FirebaseFirestore db;
    DocumentReference docref;
    final Calendar mycal=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__attendance);
        db=FirebaseFirestore.getInstance();
        check=new CheckBox[18];
        check[0]=(CheckBox)findViewById(R.id.checkBox37);
        check[1]=(CheckBox)findViewById(R.id.checkBox38);
        check[2]=(CheckBox)findViewById(R.id.checkBox39);
        check[3]=(CheckBox)findViewById(R.id.checkBox40);
        check[4]=(CheckBox)findViewById(R.id.checkBox41);
        check[5]=(CheckBox)findViewById(R.id.checkBox42);
        check[6]=(CheckBox)findViewById(R.id.checkBox43);
        check[7]=(CheckBox)findViewById(R.id.checkBox44);
        check[8]=(CheckBox)findViewById(R.id.checkBox45);
        check[9]=(CheckBox)findViewById(R.id.checkBox46);
        check[10]=(CheckBox)findViewById(R.id.checkBox47);
        check[11]=(CheckBox)findViewById(R.id.checkBox48);
        check[12]=(CheckBox)findViewById(R.id.checkBox49);
        check[13]=(CheckBox)findViewById(R.id.checkBox50);
        check[14]=(CheckBox)findViewById(R.id.checkBox51);
        check[15]=(CheckBox)findViewById(R.id.checkBox52);
        check[16]=(CheckBox)findViewById(R.id.checkBox53);
        check[17]=(CheckBox)findViewById(R.id.checkBox54);
        getdate=(EditText)findViewById(getadate);
        store=(Button)findViewById(R.id.done);
    }

    public void onStart() {
        super.onStart();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                mycal.set(Calendar.YEAR, year);
                mycal.set(Calendar.MONTH, month);
                mycal.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        getdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Get_Attendance.this, date, mycal.get(Calendar.YEAR), mycal.get(Calendar.MONTH), mycal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(Get_Attendance.this, "", Toast.LENGTH_SHORT).show();
                Map<String, String> attend=new HashMap<>();
                String ch;
                String name;
                for(int i=0; i<18; i++)
                {
                    name=check[i].getText().toString();
                    if(check[i].isChecked()){
                        ch="P";

                    }
                    else{
                        ch="A";
                    }
                    attend.put(name, ch);
                }
                docref=db.collection("Attendance").document(getdate.getText().toString());
                docref.set(attend);
                docref.set(attend).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        Toast.makeText(Get_Attendance.this, "Attendance Stored Successfully...", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Get_Attendance.this, "Failed To Store Attendance", Toast.LENGTH_SHORT).show();
                    }
                });
                Intent i=new Intent(Get_Attendance.this,MainActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });


    }

    private void updateLabel() {
        String myformat="dd/MM/yy";
        SimpleDateFormat sdf=new SimpleDateFormat(myformat, Locale.US);
        String mydate=sdf.format(mycal.getTime());
        mydate=mydate.replace('/', '-');
        getdate.setText(mydate);
    }
}
