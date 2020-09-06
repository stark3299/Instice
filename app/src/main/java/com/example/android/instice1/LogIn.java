package com.example.android.instice1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity implements View.OnClickListener {
    TextView loginid1, password6, forgot;
    Button login;
    CheckBox checkbox1;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    private static final String pref = "pref";
    private static final String emails = "email";
    private static final String passwords = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginid1 = findViewById(R.id.loginid1);
        password6 = findViewById(R.id.password6);
        forgot = findViewById(R.id.forgot);
        login = findViewById(R.id.login);
        checkbox1 = findViewById(R.id.checkbox1);
        mAuth=FirebaseAuth.getInstance();
        login.setOnClickListener(this);
        forgot.setOnClickListener(this);

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(LogIn.this , Common.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.login:
                mAuth.signInWithEmailAndPassword(loginid1.getText().toString(),password6.getText().toString()).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(LogIn.this, Navigation.class);
                        String em = loginid1.getText().toString();
                        String ps = password6.getText().toString();
                        sharedPreferences = getSharedPreferences(pref, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(emails, em);
                        editor.putString(passwords, ps);
                        editor.apply();
                        startActivity(intent);
                        finish();
                    }

                    else {
                        Toast.makeText(LogIn.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.forgot:
                Intent intent1 = new Intent(this,Reset.class);
                startActivity(intent1);
                break;
        }
    }
}
