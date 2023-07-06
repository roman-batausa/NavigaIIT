package com.example.navigaiit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        logo = findViewById((R.id.login_logo_image));
        slogan = findViewById((R.id.slogan_image));
        logo_name = findViewById((R.id.logoname_text));

        logo.setAnimation(logoAnim);
        slogan.setAnimation(sloganAnim);
        logo_name.setAnimation(sloganAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        }, SPLASH_SCREEN);

    }
}