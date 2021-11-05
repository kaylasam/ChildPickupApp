package edu.uark.finalproject.DashboardActivity;

import androidx.appcompat.app.AppCompatActivity;
import edu.uark.finalproject.R;

import android.os.Bundle;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}