package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import java.util.regex.Matcher;

public class Reset extends AppCompatActivity implements View.OnClickListener{
    private TextView resetid;
    FirebaseAuth auth;
    Button submit;
    ImageView mImageView;
    String resetid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        Toolbar toolbar = findViewById(R.id.toolbarRe);
        mImageView = findViewById(R.id.arrow_backR);
        getSupportActionBar();
        toolbar.setTitle("Reset Password");
        auth = FirebaseAuth.getInstance();
        resetid = findViewById(R.id.resetid);
        resetid1 = resetid.getText().toString().trim();
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        mImageView.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.submit:
                submitButtonTask();
                break;

            case R.id.arrow_backR:
                Intent intent = new Intent(Reset.this , Setting.class);
                startActivity(intent);
                finish();

        }
    }
    private void submitButtonTask() {
        Matcher m = Patterns.EMAIL_ADDRESS.matcher(resetid1);
        if (resetid1.equals("") || resetid1.length() == 0)    // First check if email id is not null else show error toast
        {
            Toast.makeText(Reset.this, "Enter your EmailID ...", Toast.LENGTH_SHORT).show();
        }
        else if (!m.find())    // Check if email id is valid or not
        {
            Toast.makeText(Reset.this, "Check EmailID", Toast.LENGTH_SHORT).show();
        }
        else
            auth.sendPasswordResetEmail(resetid1)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Reset.this , Confirmation.class);
                            intent.putExtra("EmailID",resetid1);
                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(Reset.this, "Invalid EmailID ... ",Toast.LENGTH_SHORT).show();
                    });
            Toast.makeText(Reset.this, "Mail Sent",Toast.LENGTH_SHORT).show();
    }
}

