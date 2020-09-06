package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ImageView imageView = findViewById(R.id.arrow_backA);
        getSupportActionBar();
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(About.this , Navigation.class);
            startActivity(intent);
            finish();
        });
    }
}
