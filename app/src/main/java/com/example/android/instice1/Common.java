package com.example.android.instice1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Common extends AppCompatActivity implements View.OnClickListener {
    int RC_SIGN_IN = 0;
    public GoogleSignInClient mGoogleSignInClient;
    public LinearLayout google1;
    public TextView login1, admin1, google2;
    public Button phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        google1 = findViewById(R.id.google1);
        login1 = findViewById(R.id.login1);
        admin1 = findViewById(R.id.admin1);
        phone = findViewById(R.id.phone);
        //google2 = findViewById(R.id.google2);
        google1.setOnClickListener(this);
        login1.setOnClickListener(this);
        admin1.setOnClickListener(this);
        phone.setOnClickListener(this);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(Common.this, Navigation.class));
            Toast.makeText(Common.this, "SignIn Success", Toast.LENGTH_LONG).show();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(Common.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(Common.this, Navigation.class));
        }
        super.onStart();
    }

    @Override
    public void onClick(View view) {
     switch(view.getId()) {
         case R.id.google1:
             GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                     .requestEmail()
                     .build();
             // Build a GoogleSignInClient with the options specified by gso.
             mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
             google1.setOnClickListener(view1 -> signIn());
            break;
         case R.id.login1:
             Intent intent1 = new Intent(this,LogIn.class);
             startActivity(intent1);
             finish();
             break;
         case R.id.admin1:
             Intent intent2 = new Intent(this,Admin.class);
             startActivity(intent2);
             finish();
             break;
         case R.id.phone:
             Intent intent = new Intent(Common.this,SignUp.class);
             startActivity(intent);
             finish();
             break;

         }
     }
}
