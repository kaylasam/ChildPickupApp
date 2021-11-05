package edu.uark.finalproject.ParentProfileActivity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.R;

public class ParentProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_profile);

    }

    public void onClick(View v){
        finish();
    }
}
