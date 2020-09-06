package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class Terms extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ImageView imageView = findViewById(R.id.arrow_backT);
        getSupportActionBar();
        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(Terms.this , Setting.class);
            startActivity(intent);
            finish();
        });
    }
}
