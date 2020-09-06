package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity implements View.OnClickListener{
    Button done;
    String mssg1 , modifymssg;
    TextView mssg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        done = findViewById(R.id.done);
        done.setOnClickListener(this);
        mssg = findViewById(R.id.mssg);
        String resetId2 = intent.getExtras().getString("EmailID");
        mssg1 = mssg.getText().toString();
        modifymssg = mssg1 + " " + resetId2 + " " + "Please check your Email and click on reset link to continue...";
        mssg.setText(modifymssg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done:
                Intent i = new Intent(Confirmation.this,Common.class);
                startActivity(i);
                finish();
        }
    }
}
