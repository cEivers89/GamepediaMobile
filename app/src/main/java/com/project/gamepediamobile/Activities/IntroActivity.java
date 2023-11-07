package com.project.gamepediamobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

import com.project.gamepediamobile.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        AppCompatButton letsGoBtn = findViewById(R.id.letsGoBtn);
        letsGoBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this, LoginActivity.class)));
    }
}