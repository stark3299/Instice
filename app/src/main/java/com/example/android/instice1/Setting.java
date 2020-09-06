package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Setting extends AppCompatActivity {
    LinearLayout setting_profile , setting_Pass , setting_logout , setting_delete ,
        setting_terms , setting_version;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference("Profile");
    StorageReference mStorageReference = FirebaseStorage.getInstance().getReference("Profile");
    GoogleSignInClient mGoogleSignInClient;
    ImageView mImageView;
    String userId , email ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setting_profile = findViewById(R.id.setting_profile);
        setting_Pass = findViewById(R.id.settings_chngepass);
        setting_logout = findViewById(R.id.settings_logout);
        setting_delete = findViewById(R.id.settings_delete);
        setting_terms = findViewById(R.id.settings_terms);
        setting_version = findViewById(R.id.settings_version);
        mImageView = findViewById(R.id.arrow_backS);
        userId = mUser.getUid();
        email = mUser.getEmail();
        getSupportActionBar();
        mImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this , Navigation.class);
            startActivity(intent);
            finish();
        });
        setting_profile.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this , Profile.class);
            startActivity(intent);
        });
        setting_Pass.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this , Reset.class);
            startActivity(intent);
        });
        setting_delete.setOnClickListener(v -> {
            mUser.delete().addOnSuccessListener(aVoid -> Toast.makeText(Setting.this, "User Deleted", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(Setting.this, "User not Deleted", Toast.LENGTH_SHORT).show());
            mReference.child(userId).removeValue();
            StorageReference ref = mStorageReference.child(email);
            ref.delete().addOnSuccessListener(aVoid -> Toast.makeText(Setting.this, "DataBase Deleted", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(Setting.this, "DataBase not Deleted", Toast.LENGTH_SHORT).show());
            Intent intent = new Intent(Setting.this , Common.class);
            startActivity(intent);
            finish();
        });
        setting_logout.setOnClickListener(v -> {
            signOut();
            mReference.child(userId).removeValue();
            StorageReference ref = mStorageReference.child(email);
            ref.delete().addOnSuccessListener(aVoid -> Toast.makeText(Setting.this, "DataBase Deleted", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(Setting.this, "DataBase not Deleted", Toast.LENGTH_SHORT).show());
            Intent intent = new Intent(Setting.this , Common.class);
            startActivity(intent);
            finish();

        });
        setting_terms.setOnClickListener(v -> {
            Intent intent = new Intent(Setting.this , Terms.class);
            startActivity(intent);
        });

        setting_version.setOnClickListener(v -> Toast.makeText(this, "App Version is 1.0 .", Toast.LENGTH_SHORT).show());
    }
    private void signOut() {
        mAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
        });
    }
}
