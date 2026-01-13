package com.example.cw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class StaffLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        View loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(StaffLoginActivity.this, StaffHomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
