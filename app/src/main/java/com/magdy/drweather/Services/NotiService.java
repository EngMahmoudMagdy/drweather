package com.magdy.drweather.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.magdy.drweather.Data.NotifyObject;
import com.magdy.drweather.R;
import com.magdy.drweather.UI.SplashActivity;

public class NotiService extends Service {
    public static final String TAG = "noti" ;
    int id = 0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO do something useful
        return Service.START_STICKY_COMPATIBILITY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("users/user1/noti");
        dbref.orderByChild("seen").equalTo(2).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap : dataSnapshot.getChildren())
                {
                    NotifyObject notifyObject = snap.getValue(NotifyObject.class);
                    if (notifyObject != null)
                    {
                        notifyObject.setId(snap.getKey());
                        createnoti(notifyObject);
                        dbref.child(notifyObject.getId()).child("seen").setValue(1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void createnoti(NotifyObject object) {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(TAG,object);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.dr_weather_circle)
                        .setContentTitle(object.getName())
                        .setContentText(object.getDesc())
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);
        notificationBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.dr_weather_circle));
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id/* ID of notification */, notificationBuilder.build());
        id++;
    }
}
