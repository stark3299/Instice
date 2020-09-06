package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class History extends AppCompatActivity {
    String id1 , emailH;
    TextView noHistory ;
    HistoryAdapter adapter;
    ImageView mImageView ;
    DatabaseReference mReference2 ;
    ArrayList<List1> list2 = new ArrayList<>();
    FirebaseUser mUser1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history1);
        getSupportActionBar();
        mImageView = findViewById(R.id.arrow_backH);
        id1 = Report2.getActivityInstance().getData();
        noHistory = findViewById(R.id.no_text);
        RecyclerView recyclerView = findViewById(R.id.card1);
        if(list2.isEmpty()){
            recyclerView.setVisibility(View.GONE);
            noHistory.setVisibility(View.VISIBLE);
        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
            noHistory.setVisibility(View.GONE);
        }
        mImageView.setOnClickListener(view -> {
            Intent intent = new Intent(History.this , Navigation.class);
            startActivity(intent);
            finish();
        });
        mUser1 = FirebaseAuth.getInstance().getCurrentUser();
        assert mUser1 != null;
        emailH = mUser1.getEmail();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mReference2 = FirebaseDatabase.getInstance().getReference("History").child(emailH);
        mReference2.keepSynced(true);
        mReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    List1 u = dataSnapshot1.getValue(List1.class);
                    list2.add(u);
                }
                adapter = new HistoryAdapter(History.this , list2);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(History.this, "Oops!...Error in database", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
