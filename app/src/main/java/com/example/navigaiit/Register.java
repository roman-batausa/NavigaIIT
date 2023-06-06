package com.example.navigaiit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView textView = findViewById(R.id.logoname_text_register);
        String html = "<font color=#F6D853" + ">Naviga</font><font color=#800000" + ">IIT</font>";
        textView.setText(Html.fromHtml(html));
    }
}