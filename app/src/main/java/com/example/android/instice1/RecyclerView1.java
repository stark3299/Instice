package com.example.android.instice1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.SearchView;
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
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import java.util.ArrayList;

public class RecyclerView1 extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mdatabase;
    static RecyclerView1 INSTANCE;
    SearchView mSearchView;
    ArrayList<Userr> users = new ArrayList<>();
    long tcase;
    RecycleAdapter mAdapter , mAdapter1;
    CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        INSTANCE = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        getSupportActionBar();
        mRecyclerView = findViewById(R.id.myrecyclerview);
        mSearchView = findViewById(R.id.search_view);
        mCheckBox = findViewById(R.id.checkBox2);
        ActivityCompat.requestPermissions(RecyclerView1.this , new String[]{Manifest.permission.SEND_SMS ,
                Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mdatabase = FirebaseDatabase.getInstance().getReference("Users");
        mdatabase.keepSynced(true);
        mdatabase.orderByChild("Date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Userr u = dataSnapshot1.getValue(Userr.class);
                    tcase = dataSnapshot1.getChildrenCount();
                    users.add(u);
                }
                mAdapter = new RecycleAdapter(RecyclerView1.this , users );
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(RecyclerView1.this, "Oops!...Error in database", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        MaterialSearchView searchView = findViewById(R.id.search_view);
        searchView.setMenuItem(item);
        return true;
    }


    public static RecyclerView1 getActivityInstance() {
        return INSTANCE;
    }
    public long getData(){
        return this.tcase;
    }
    protected void onStart() {
        super.onStart();
        if (mSearchView!=null){
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    search(query);
                    return true;
                }
            });
        }
    }
    private void search(String query1){
        ArrayList<Userr> myuser = new ArrayList<>();
        for(Userr object : users ) {
            if(object.getName().toLowerCase().contains(query1.toLowerCase()))
            {
             myuser.add(object);
            }
        }
        mAdapter1 = new RecycleAdapter(RecyclerView1.this,myuser);
        mRecyclerView.setAdapter(mAdapter1);
    }

}
