package com.example.myapplication;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import id.zelory.compressor.Compressor;

//import io.grpc.Compressor;

public class report extends AppCompatActivity {

    private ImageView userImage;
    private EditText Object,Place,Description;
    private Button back;
    DatePicker date;
    TimePicker time;
    private ProgressDialog progressDialog;
    private Uri imageUri;
    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String user_id;
    private String time1,date1;


    private Bitmap compressed;

    public report() {
        imageUri = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        progressDialog = new ProgressDialog(this);

        final EditText  Object= findViewById(R.id.editText);
        final EditText  Place = findViewById(R.id.editText2);
        final EditText Description = findViewById(R.id.editText5);
        final TimePicker time= findViewById(R.id.timePicker);
        final DatePicker date=findViewById(R.id.datePicker);
        final Button back=findViewById(R.id.button2);
        final ImageView userImage=findViewById(R.id.imageView2);
        firebaseAuth = FirebaseAuth.getInstance();
//        user_id = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        userImage.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                                 if (ContextCompat.checkSelfPermission(report.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                                                     Toast.makeText(report.this, "Permission Denied", Toast.LENGTH_LONG).show();
                                                     ActivityCompat.requestPermissions(report.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                                                 } else {

                                                     choseImage();

                                                 }

                                             } else {

                                                 choseImage();

                                             }

                                         }

                                         private void choseImage() {
                                         }

                                     }

        );

        back.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                //  progressDialog.setMessage("Storing Data...");
                progressDialog.show();

                final String obj = Object.getText().toString().trim();
                final String place = Place.getText().toString().trim();
                final String desc = Description.getText().toString().trim();

                Integer hour= time.getHour();
                Integer min=time.getMinute();
                time1= hour.toString()+":"+min.toString();

                Integer day=date.getDayOfMonth();
                Integer month=date.getMonth();
                Integer month1=month+1;
                Integer year=date.getYear();
                final String datet=day.toString()+"-"+month1.toString()+"-"+year.toString();
                if(obj.isEmpty())
                {
                    Object.requestFocus();
                    Toast.makeText(report.this, "object Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(place.isEmpty())
                {
                    Place.requestFocus();
                    Toast.makeText(report.this, "place block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(desc.isEmpty())
                {
                    Description.requestFocus();
                    Toast.makeText(report.this, "Description Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(time1.isEmpty())
                {
                    time.requestFocus();
                    Toast.makeText(report.this, "time Block is Empty", Toast.LENGTH_SHORT).show();
                }
                else if(datet.isEmpty())
                {
                    date.requestFocus();
                    Toast.makeText(report.this, "date Block is Empty", Toast.LENGTH_SHORT).show();
                }

                else if (imageUri != null) {
                    File newFile = new File(imageUri.getPath());
                    try {

                        compressed = new Compressor(report.this)
                                .setMaxHeight(125)
                                .setMaxWidth(125)
                                .setQuality(50)
                                .compressToBitmap(newFile);{
                            /*@Override
                            public String getMessageEncoding() {
                                return null;
                            }

                            @Override
                            public OutputStream compress(OutputStream os) throws IOException {
                                return null;
                            }*/
                        }




                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    compressed.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] thumbData = byteArrayOutputStream.toByteArray();

                    UploadTask image_path = storageReference.child("imageview2").child(user_id + ".jpg").putBytes(thumbData);
                    image_path.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                storeData(task, Object, Place, Description, time1, datet);

                            } else {

                                String error = task.getException().getMessage();
                                Toast.makeText(report.this, "(IMAGE Error) : " + error, Toast.LENGTH_LONG).show();

                                progressDialog.dismiss();

                            }
                        }
                    });
                }
            }



            private void storeData(Task<UploadTask.TaskSnapshot>task, EditText Object, EditText Place, EditText Descripion, String date, String time)
            {
                Uri download_uri;

                if (task != null) {
                    download_uri = task.getResult().getStorage().getDownloadUrl().getResult();
                } else {

                    download_uri = imageUri;

                }

                Map<String,String> userData = new HashMap<>();
                userData.put("Desc",Descripion.getText().toString());
                userData.put("Place",Place.getText().toString());
                userData.put("Object",Object.getText().toString());
                userData.put("Time",time1);
                userData.put("Date",date1);
                userData.put("imageview2",download_uri.toString());

                Task<Void> voidTask = firebaseFirestore.collection("Users").document(user_id).set(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(report.this, "User Data is Stored Successfully", Toast.LENGTH_LONG).show();
                            Intent mainIntent = new Intent(report.this, MainActivity.class);
                            startActivity(mainIntent);
                            finish();

                        } else {

                            String error = task.getException().getMessage();
                            Toast.makeText(report.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();

                        }

                        progressDialog.dismiss();

                    }
                });
            }

            private void choseImage() {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(report.this);
            }

            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                //     super.OnActivityResult (requestCode, resultCode, data);

                if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                    CropImage.ActivityResult result = CropImage.getActivityResult(data);
                    if (resultCode == RESULT_OK) {

                        imageUri = result.getUri();
                        userImage.setImageURI(imageUri);


                    } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                        Exception error = result.getError();

                    }
                }

            }


        });}
}

