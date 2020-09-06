package com.example.android.instice1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class Checked extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mdatabase;
    ArrayList<Userr> users;
    static Checked INSTANCE;
    long ccase;
    RecycleAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        getSupportActionBar();
        mRecyclerView = findViewById(R.id.myrecyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActivityCompat.requestPermissions(Checked.this , new String[]{Manifest.permission.SEND_SMS ,
                Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        mdatabase = FirebaseDatabase.getInstance().getReference("Users");
        mdatabase.keepSynced(true);
        mdatabase.orderByChild("Date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users = new ArrayList<Userr>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Userr u = dataSnapshot1.getValue(Userr.class);
                    if(u.getCheck().equals(true)){
                        users.add(u);
                    }
                }
                ccase = users.size();
                mAdapter = new RecycleAdapter(Checked.this,users);
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Checked.this, "Oops!...Error in database", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static Checked getActivityInstance1() {
        return INSTANCE;
    }
    public long getData1(){
        return this.ccase;
    }
}
