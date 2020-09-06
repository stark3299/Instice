package com.example.android.instice1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    TextView userid1, password2, password4;
    Button signup;
    CheckBox checkbox;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userid1 = findViewById(R.id.userid1);
        password2 = findViewById(R.id.password2);
        password4 = findViewById(R.id.password4);
        signup = findViewById(R.id.signup);
        checkbox = findViewById(R.id.checkbox);
        userid1.setOnClickListener(this);
        password2.setOnClickListener(this);
        password4.setOnClickListener(this);
        signup.setOnClickListener(this);
        checkbox.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(SignUp.this , Common.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View view) {
        String ps1 = password4.getText().toString().trim();
        String em = userid1.getText().toString().trim();
        String ps = password2.getText().toString().trim();

        if (ps1.equals(ps)) {

            if (checkbox.isChecked()) {

                mAuth.createUserWithEmailAndPassword(em, ps).addOnCompleteListener(this, task -> {

                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "Error !!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SignUp.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(SignUp.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUp.this, Navigation.class));
                                finish();
                            }
                    }
                });
            }
            else {
                Toast.makeText(this, "Please agree the terms and condition", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(SignUp.this, "Password Must Be Same", Toast.LENGTH_SHORT).show();
        }
    }
}
