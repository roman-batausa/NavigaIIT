package com.example.navigaiit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView textView = findViewById(R.id.logoname_text_login);
        String html = "<font color=#F6D853" + ">Naviga</font><font color=#800000" + ">IIT</font>";
        textView.setText(Html.fromHtml(html));
    }
}