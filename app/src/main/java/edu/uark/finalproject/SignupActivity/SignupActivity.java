package edu.uark.finalproject.SignupActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import edu.uark.finalproject.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private ImageView parentPic, childPic, vehiclePic;
    private ImageButton backBTN, gpsBTN;
    private EditText parName, phone, email, password, make, model, color, year,
            license, childName, childAge, childGrade;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Hide the top menu bar
        getSupportActionBar().hide();

    }
}