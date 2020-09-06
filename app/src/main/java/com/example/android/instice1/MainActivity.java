package com.example.android.instice1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {
    Animation slide_down ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.sLogo);
        ImageView imageView1 = findViewById(R.id.sLogo1);
        new Handler().postDelayed(() -> {

            Intent i = new Intent(MainActivity.this, Common.class);
            startActivity(i);
            overridePendingTransition(R.anim.zoom_out, R.anim.zoom_in);
            finish();
        }, 4000);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext() , R.anim.slide_down );
        slide_down.setStartOffset(2000);

        imageView.animate()
                .translationY(700)
                .setInterpolator(new AccelerateInterpolator())
                .setInterpolator(new BounceInterpolator())
                .setDuration(2000);

        imageView1.setVisibility(View.INVISIBLE);
        imageView1.setAnimation(slide_down);
       /* Intent intent = new Intent(getApplicationContext() , Common.class);
        ActivityOptions options = ActivityOptions.makeCustomAnimation(getApplicationContext() , R.anim.zoom_in , R.anim.zoom_out);
        getApplicationContext().startActivity(intent , options.toBundle());
        */

    }
}
