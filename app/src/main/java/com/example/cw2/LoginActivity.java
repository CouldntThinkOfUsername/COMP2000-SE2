package com.example.cw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View loginButton = findViewById(R.id.login_button);
        Button registerButton = findViewById(R.id.register_button);
        Button staffLoginButton = findViewById(R.id.staff_login_button);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        staffLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, StaffLoginActivity.class);
            startActivity(intent);
        });
    }
}
