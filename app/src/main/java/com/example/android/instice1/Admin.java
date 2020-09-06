package com.example.android.instice1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity implements View.OnClickListener{
    String useradm1 = "s.ranjanbit@gmail.com";
    String passadm1 = "Google!@#123";
    SharedPreferences sharedPreferences;
    TextView useradm , passadm;
    private FirebaseAuth mAuth;
    Button btnadm1;
    private static final String pref = "pref";
    private static final String emails = "email";
    private static final String passwords = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        useradm = findViewById(R.id.loginadmid1);
        passadm = findViewById(R.id.passadm6);
        mAuth = FirebaseAuth.getInstance();
        btnadm1 = findViewById(R.id.loginadm);
        btnadm1.setOnClickListener(this);
    }
    @Override
    public void onBackPressed(){
        Intent i = new Intent(Admin.this , Common.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(View view) {

        switch(view.getId())
        {
            case R.id.forgot:
                Intent i = new Intent(Admin.this,Reset.class);
                startActivity(i);

            case R.id.loginadm :
                if(useradm1.equals(useradm.getText().toString()) && passadm1.equals(passadm.getText().toString())) {
                    mAuth.signInWithEmailAndPassword(useradm.getText().toString(), passadm.getText().toString()).addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            String em = useradm.getText().toString();
                            String ps = passadm.getText().toString();
                            sharedPreferences = getSharedPreferences(pref, MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(emails, em);
                            editor.putString(passwords, ps);
                            editor.apply();
                            Intent intent = new Intent(Admin.this, DashBoard.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, "Wrong Credential", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(this, "Something Wrong happen", Toast.LENGTH_SHORT).show();
                } break;
        }
    }
}
