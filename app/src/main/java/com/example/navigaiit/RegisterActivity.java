package com.example.navigaiit;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity{

    Toolbar toolbar;
    Button login_btn, register_btn;
    TextInputLayout register_username, register_fullname, register_email, register_password;
    String u_name, f_name, email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textView = findViewById(R.id.register_logoname_text);
        String html = "<font color=#F6D853" + ">Naviga</font><font color=#800000" + ">IIT</font>";
        textView.setText(Html.fromHtml(html));

        login_btn = findViewById(R.id.already_have_an_account_btn);
        register_btn = findViewById(R.id.register_btn);
        register_username = findViewById(R.id.register_username);
        register_fullname = findViewById(R.id.register_fullname);
        register_email = findViewById(R.id.register_email);
        register_password = findViewById(R.id.register_password);



        toolbar = findViewById(R.id.register_toolbar);
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

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_name = register_username.getEditText().getText().toString();
                f_name = register_fullname.getEditText().getText().toString();
                email = register_email.getEditText().getText().toString();
                pass = register_password.getEditText().getText().toString();

                if(!u_name.isEmpty() && !f_name.isEmpty() && !email.isEmpty() && !pass.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, u_name + ", " + f_name + ", " + email + ", " + pass,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Make sure to fill the spaces accordingly",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}