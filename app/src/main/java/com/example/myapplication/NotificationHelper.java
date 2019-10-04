package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private NotificationManager mManager;
    String topic,venue,cond,time,desc,name;
    public NotificationHelper(Context base,String topic,String venue,String cond,String time,String desc,String name) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
            this.topic=topic;
            this.venue=venue;
            this.cond=cond;
            this.time=time;
            this.desc=desc;
            this.name=name;
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager()
    {
        if (mManager == null)
        {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification()
    {
        Intent resultIntent =new Intent(this,Event_Information.class);
        resultIntent.putExtra("topic",topic);
        resultIntent.putExtra("venue",venue);
        resultIntent.putExtra("name",name);
        resultIntent.putExtra("organiser",cond);
        resultIntent.putExtra("time",time);
        resultIntent.putExtra("description",desc);

        PendingIntent resultPendingIntent=PendingIntent.getActivity(this,1,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                db.collection("Event").document("topict")
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(NotificationHelper.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(NotificationHelper.this, "Not deleted", Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        }, 3600000);

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(topic)
                .setContentText(venue)
                .setSmallIcon(R.drawable.ic_sms_black_24dp)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent);
    }
}