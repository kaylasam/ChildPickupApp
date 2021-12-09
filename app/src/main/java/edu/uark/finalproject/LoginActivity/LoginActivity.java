package edu.uark.finalproject.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.ForgotPasswordActivity.ForgotPasswordActivity;
import edu.uark.finalproject.R;
import edu.uark.finalproject.SignupActivity.SignupActivity;

public class LoginActivity extends AppCompatActivity {
    TextView loginButton, forgotPasswordButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hide the top menu bar
        getSupportActionBar().hide();

        loginButton = findViewById(R.id.login);
        forgotPasswordButton= findViewById(R.id.forgotPasswordButton);
        signUpButton = findViewById(R.id.SignUpButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

}
