package edu.uark.finalproject.SignupActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private ImageView parentPic, childPic, vehiclePic;
    private ImageButton backBTN, gpsBTN;
    private EditText parName, phone, email, password, make, model, color, year,
            license, childName, childAge, childGrade;
    private Button register;
    TextView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Hide the top menu bar
        getSupportActionBar().hide();

        nextButton = findViewById(R.id.next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });

    }
}