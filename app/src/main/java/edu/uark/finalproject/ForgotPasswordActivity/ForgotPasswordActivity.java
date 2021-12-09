package edu.uark.finalproject.ForgotPasswordActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.LoginActivity.LoginActivity;
import edu.uark.finalproject.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    /**
     * Main class for forgot password activity. This page will prompt
     * the user for an email to send a reset password link. Goes back to
     * login screen afterwards.
     */

    TextView signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        // Hide the top menu bar
        getSupportActionBar().hide();

        signupButton = findViewById(R.id.send_reset);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send request to send password reset link
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}