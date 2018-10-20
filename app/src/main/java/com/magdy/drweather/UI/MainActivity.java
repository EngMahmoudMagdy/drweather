package com.magdy.drweather.UI;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.magdy.drweather.Data.NotifyObject;
import com.magdy.drweather.R;
import com.magdy.drweather.Services.NotiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar ;
    int notiCount ;
    List<NotifyObject> notifyObjects ;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.app_name);
        findViewById(R.id.new_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),AddDataActivity.class));
            }
        });
        notifyObjects = new ArrayList<>();
        Intent mServiceIntent = new Intent(getBaseContext(), NotiService.class);
        mServiceIntent.addCategory(NotiService.TAG);
        stopService(mServiceIntent);
        mServiceIntent.setData(Uri.parse("data"));
        startService(mServiceIntent);

        dbref = FirebaseDatabase.getInstance().getReference("users/user1/noti");
        dbref .orderByChild("seen").startAt(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notiCount= (int) dataSnapshot.getChildrenCount();
                notifyObjects.clear();
                invalidateOptionsMenu();
                for(DataSnapshot snap : dataSnapshot.getChildren())
                {
                    NotifyObject notifyObject = snap.getValue(NotifyObject.class);
                    if (notifyObject != null)
                    {
                        notifyObject.setId(snap.getKey());
                        notifyObjects.add(notifyObject);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem2 = menu.findItem(R.id.notification_action);
        menuItem2.setIcon(Converter.convertLayoutToImage(MainActivity.this,notiCount ,R.drawable.ic_notifications_white_24dp));
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.notification_action)
        {
            for (NotifyObject object : notifyObjects)
            {
                dbref.child(object.getId()).child("seen").setValue(0);
            }
            startActivity(new Intent(getBaseContext(),NotificationsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


}
