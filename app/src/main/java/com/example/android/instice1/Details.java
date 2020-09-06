package com.example.android.instice1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {
    TextView namef , emailf , addressf , statef , countryf , descf , mobilef , datef , timef , cdatef ;
    Button mButton;
    ImageView imagef;
    String userId;
    DatabaseReference mDatabaseReference ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        namef = findViewById(R.id.namef);
        emailf = findViewById(R.id.emailf);
        addressf = findViewById(R.id.addressf);
        statef = findViewById(R.id.statef);
        countryf = findViewById(R.id.countryf);
        descf = findViewById(R.id.descf);
        datef = findViewById(R.id.datef);
        cdatef = findViewById(R.id.cdatef);
        timef = findViewById(R.id.timef);
        mobilef = findViewById(R.id.mobilef);
        imagef = findViewById(R.id.imagef);
        mButton = findViewById(R.id.button);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mDatabaseReference.keepSynced(true);
        userId = getIntent().getExtras().get("UserId").toString();

        mDatabaseReference.orderByChild("id").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String nameff = datas.child("name").getValue().toString();
                    String emailff = datas.child("email").getValue().toString();
                    String mobileff = datas.child("mobile_no").getValue().toString();
                    String addressff = datas.child("address").getValue().toString();
                    String stateff = datas.child("state").getValue().toString();
                    String countryff = datas.child("country").getValue().toString();
                    String descff = datas.child("description").getValue().toString();
                    String dateff = datas.child("date").getValue().toString();
                    String timeff = datas.child("time").getValue().toString();
                    String imageff = datas.child("imageUrl").getValue().toString();
                    String cdateff = datas.child("current_date").getValue().toString();

                    namef.setText(nameff);
                    emailf.setText(emailff);
                    addressf.setText(addressff);
                    mobilef.setText(mobileff);
                    statef.setText(stateff);
                    countryf.setText(countryff);
                    descf.setText(descff);
                    datef.setText(dateff);
                    timef.setText(timeff);
                    cdatef.setText(cdateff);
                    Picasso.get().load(imageff).into(imagef);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
