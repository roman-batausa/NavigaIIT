package com.example.navigaiit;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button register_btn, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textView = findViewById(R.id.login_logoname_text);
        String html = "<font color=#F6D853" + ">Naviga</font><font color=#800000" + ">IIT</font>";
        textView.setText(Html.fromHtml(html));

        // ++++++++++++++++++++ toolbar ++++++++++++++++++++++++
        toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.baseline_arrow_back_35));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        // ++++++++++++++++++++++++++++++++++++++++++++++++++++

        register_btn = findViewById(R.id.dont_have_an_account_btn);
        //Hooks
        image = findViewById(R.id.login_logo_image);
        logoText = findViewById(R.id.login_logoname_text);
        sloganText = findViewById(R.id.login_welcome_text);
        username = findViewById(R.id.username_layout);
        password = findViewById(R.id.password_layout);
        login_btn = findViewById(R.id.login_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "trans_logo_image");
                pairs[1] = new Pair<View, String>(logoText, "trans_logoname_text");
                pairs[2] = new Pair<View, String>(sloganText, "trans_welcome_text");
                pairs[3] = new Pair<View, String>(username, "trans_username_textfield");
                pairs[4] = new Pair<View, String>(password, "trans_password_textfield");
                pairs[5] = new Pair<View, String>(login_btn, "trans_button");
                pairs[6] = new Pair<View, String>(register_btn, "trans_acc_button");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, options.toBundle());

            }
        });

    }

}