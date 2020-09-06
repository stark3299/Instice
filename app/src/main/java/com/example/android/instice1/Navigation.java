package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener{
    LinearLayout add2 , history , extra , setting1;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    StorageReference mStorageReference ;
    String email1 = "abc@gmail.com";
    ImageView nav_profile , profile , add_In , history_In , setting_In , extra_In ;
    public long back;
    TextView tx1 , add_Tn , history_Tn , setting_Tn , extra_Tn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView nav_id = findViewById(R.id.nav_id);
        TextView nav_header_title = findViewById(R.id.nav_header_title);
        history_In = findViewById(R.id.history_In);
        history_Tn = findViewById(R.id.history_Tn);
        setting_In = findViewById(R.id.setting_In);
        setting1 = findViewById(R.id.setting1);
        setting_Tn = findViewById(R.id.setting_Tn);
        extra = findViewById(R.id.extra);
        extra_In = findViewById(R.id.extra_In);
        extra_Tn = findViewById(R.id.extra_Tn);
        nav_profile = findViewById(R.id.nav_profile);
        profile = findViewById(R.id.profile);
        add2 = findViewById(R.id.add2);
        add_In = findViewById(R.id.add_In);
        add_Tn = findViewById(R.id.add_Tn);
        tx1 = findViewById(R.id.textView);
        history = findViewById(R.id.history);
        history_Tn.setOnClickListener(this);
        history_In.setOnClickListener(this);
        history.setOnClickListener(this);
        setting_Tn.setOnClickListener(this);
        setting_In.setOnClickListener(this);
        setting1.setOnClickListener(this);
        extra_Tn.setOnClickListener(this);
        extra_In.setOnClickListener(this);
        extra.setOnClickListener(this);
        add_Tn.setOnClickListener(this);
        add_In.setOnClickListener(this);
        add2.setOnClickListener(this);
      //  String wel_title = Profile.getActivityInstance().getData();
       // nav_id.setText(wel_title);
       // nav_header_title.setText(wel_title);
        if(user!=null) {
            email1 = user.getEmail();
            //tx1.setText(email1);
        }
        //getSupportActionBar();
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        mStorageReference = FirebaseStorage.getInstance().getReference("Profile").child(email1);
       // Glide.with(this).load(mStorageReference).apply(options).into(profile);
        // Glide.with(this).load(mStorageReference).apply(options).into(nav_profile);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        back = System.currentTimeMillis();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } if (back + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(this, "Press back again to exit. ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(Navigation.this);
            materialAlertDialogBuilder.setTitle("Exit").setMessage("Are you really want to exit this app ? ")
                    .setNegativeButton("No", (dialog, which) -> materialAlertDialogBuilder.setOnDismissListener(dialog1 -> {
                        Toast.makeText(Navigation.this, "Good", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }))
                    .setPositiveButton("Yes", (dialog, which) -> finish()).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(Navigation.this , Navigation.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(Navigation.this , Report2.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(Navigation.this , History.class);
            startActivity(intent);

        } else if (id == R.id.nav_tools) {
            Intent intent = new Intent(Navigation.this , Setting.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Navigation.this , About.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            Intent intent = new Intent(Navigation.this , Privacy.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add2:
            case R.id.add_In:
            case R.id.add_Tn:
                Intent intent = new Intent(Navigation.this , Report2.class);
                startActivity(intent);
                break;

            case R.id.history:
            case R.id.history_In:
            case R.id.history_Tn:
                Intent intent1 = new Intent(Navigation.this , History.class);
                startActivity(intent1);
                break;

            case R.id.setting1:
            case R.id.setting_In:
            case R.id.setting_Tn:
                Intent intent2 = new Intent(Navigation.this , Setting.class);
                startActivity(intent2);
                break;

            case R.id.extra:
            case R.id.extra_In:
            case R.id.extra_Tn:
                dialogN();
                break;

        }
    }
    public void dialogN (){
        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(Navigation.this);
        materialAlertDialogBuilder.setTitle("Exit").setMessage("Are you really want to exit this app ? ")
                .setNegativeButton("No", (dialog, which) -> materialAlertDialogBuilder.setOnDismissListener(dialog1 -> {
                    Toast.makeText(Navigation.this, "Good", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }))
                .setPositiveButton("Yes", (dialog, which) -> finish()).show();
    }
}
