package com.magdy.drweather.UI;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.magdy.drweather.Adapters.NotifyAdapter;
import com.magdy.drweather.Data.NotifyObject;
import com.magdy.drweather.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar ;
    @BindView(R.id.recycler)
    RecyclerView recyclerView ;
    List<NotifyObject> notifyObjects ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.notifications);
        }
        notifyObjects = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        final NotifyAdapter adapter = new NotifyAdapter(this,notifyObjects);
        recyclerView.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference("users/user1/noti").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notifyObjects.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    NotifyObject notifyObject = snapshot.getValue(NotifyObject.class);
                    if (notifyObject!=null)
                    {
                        notifyObject.setId(snapshot.getKey());
                        notifyObjects.add(notifyObject);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
