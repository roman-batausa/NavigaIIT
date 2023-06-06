package com.example.navigaiit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;

    // Variables
    Animation logoAnim, sloganAnim;
    ImageView logo, slogan;
    TextView logo_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.logoname_text);
        String html = "<font color=#F6D853" + ">Naviga</font><font color=#800000" + ">IIT</font>";
        textView.setText(Html.fromHtml(html));

        // Animation
        logoAnim = AnimationUtils.loadAnimation(this,R.anim.map_logo_animation);
        sloganAnim = AnimationUtils.loadAnimation(this,R.anim.slogan_animation);

        // Hooks
        logo = findViewById((R.id.logo_image));
        slogan = findViewById((R.id.slogan_image));
        logo_name = findViewById((R.id.logoname_text));

        logo.setAnimation(logoAnim);
        slogan.setAnimation(sloganAnim);
        logo_name.setAnimation(sloganAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Login.class);

            // For animation
            Pair[] pairs = new Pair[2];
            pairs[0] = new Pair<View, String>(logo, "trans_logo_image");
            pairs[1] = new Pair<View, String>(logo_name, "trans_logoname_text");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(intent, options.toBundle());

        }, SPLASH_SCREEN);

    }
}