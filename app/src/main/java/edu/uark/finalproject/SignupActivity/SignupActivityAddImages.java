package edu.uark.finalproject.SignupActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.R;

public class SignupActivityAddImages extends AppCompatActivity {

    TextView addParentImageButton;
    TextView addChildImageButton;
    TextView addVehicleImageButton;
    TextView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_next);

        // Hide the top menu bar
        getSupportActionBar().hide();

        addParentImageButton = findViewById(R.id.add_parentBTN);
        addChildImageButton = findViewById(R.id.add_childBTN);
        addVehicleImageButton = findViewById(R.id.add_vehicleBTN);
        nextButton = findViewById(R.id.next);


        addParentImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to select image from camera roll or take image
            }
        });

        addChildImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to select image from camera roll or take image
            }
        });

        addVehicleImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to select image from camera roll or take image
            }
        });


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}