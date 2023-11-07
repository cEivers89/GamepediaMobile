package com.project.gamepediamobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.gamepediamobile.R;


public class LoginActivity extends AppCompatActivity {
    private EditText userName, password;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        userName = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.buttonLogin);

        loginBtn.setOnClickListener(v -> {
            if (userName.getText().toString().isEmpty() && password.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
            } else if (userName.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}