package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class DashBoard extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView  mTextMessage , add_Td , all_Td , com_Td , uncom_Td ;
    LinearLayout add1 , all , com , uncom;
    ImageView add_Id , all_Id , com_Id , uncom_Id ;
    String email;
    FirebaseAuth auth1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(R.string.title_home);
                    return true;
                case R.id.allreports:
                    toolbar.setTitle(R.string.title_dashboard);
                    Intent i1 = new Intent(DashBoard.this,RecyclerView1.class);
                    startActivity(i1);
                    return true;
                case R.id.comreports:
                    toolbar.setTitle(R.string.title_report);
                    Intent i2 = new Intent(DashBoard.this,Checked.class);
                    startActivity(i2);
                    return true;
                case R.id.navigation_notifications:
                    toolbar.setTitle(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
       // long tcase1 = RecyclerView1.getActivityInstance().getData();
        // long ccase1 = Checked.getActivityInstance1().getData1();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        TextView ttcase = findViewById(R.id.case1);
        TextView cccase = findViewById(R.id.case2);
        all_Id = findViewById(R.id.all_Id);
        all_Td = findViewById(R.id.all_Td);
        com_Id = findViewById(R.id.com_Id);
        com_Td = findViewById(R.id.com_Td);
        uncom_Td = findViewById(R.id.uncom_Td);
        uncom_Id = findViewById(R.id.uncom_Id);
        add_Id = findViewById(R.id.add_Id);
        add_Td = findViewById(R.id.add_Td);
        toolbar = findViewById(R.id.toolbar);
        add1 = findViewById(R.id.add1);
        all = findViewById(R.id.all);
        com = findViewById(R.id.com);
        uncom = findViewById(R.id.uncom);
        uncom_Td.setOnClickListener(this);
        uncom_Id.setOnClickListener(this);
        com_Td.setOnClickListener(this);
        com_Id.setOnClickListener(this);
        all_Td.setOnClickListener(this);
        all_Id.setOnClickListener(this);
        add_Td.setOnClickListener(this);
        add_Id.setOnClickListener(this);
        add1.setOnClickListener(this);
        all.setOnClickListener(this);
        com.setOnClickListener(this);
        uncom.setOnClickListener(this);
       // ttcase.setText(String.valueOf(tcase1));
        // cccase.setText(String.valueOf(ccase1));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            //  Email address
            email = user.getEmail();
        }
        getSupportActionBar();
        toolbar.setTitle(R.string.title_home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent ie = new Intent(DashBoard.this,Common.class);
            startActivity(ie);
            finish();
        }
        if (id == R.id.refresh) {
            finish();
            System.exit(0);
        }
        if (id == R.id.chngpass) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(DashBoard.this, "Email Sent...", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(DashBoard.this, "DataBase Error!", Toast.LENGTH_LONG).show();
                            }
                    });
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add1:
            case R.id.add_Id:
            case R.id.add_Td:
                Intent i = new Intent(DashBoard.this,Report.class);
                startActivity(i);
                break;

            case R.id.all:
            case R.id.all_Id:
            case R.id.all_Td:
                Intent i1 = new Intent(DashBoard.this,RecyclerView1.class);
                startActivity(i1);
                break;

            case R.id.com:
            case R.id.com_Id:
            case R.id.com_Td:
                Intent i2 = new Intent(DashBoard.this,Checked.class);
                startActivity(i2);
                break;

            case R.id.uncom:
            case R.id.uncom_Id:
            case R.id.uncom_Td:
                Intent i3 = new Intent(DashBoard.this,Unchecked.class);
                startActivity(i3);
                break;
        }

    }
}
