package com.example.cw2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StaffHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);

        Button editMenuButton = findViewById(R.id.edit_menu);
        Button manageReservationsButton = findViewById(R.id.manage_reservations);
        Button logoutButton = findViewById(R.id.log_out);

        editMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(StaffHomeActivity.this, StaffMenuActivity.class);
            startActivity(intent);
        });

        manageReservationsButton.setOnClickListener(v -> {
            Intent intent = new Intent(StaffHomeActivity.this, StaffReservationsActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(StaffHomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
