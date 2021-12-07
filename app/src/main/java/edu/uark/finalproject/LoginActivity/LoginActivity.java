package edu.uark.finalproject.LoginActivity;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.ParentProfileActivity.ParentProfileActivity;
import edu.uark.finalproject.R;

public class LoginActivity extends AppCompatActivity {
    TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide the top menu bar
        getSupportActionBar().hide();

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                startActivity(intent);
                System.out.println("HELLO LOGIN");
            }
        });

    }

}

