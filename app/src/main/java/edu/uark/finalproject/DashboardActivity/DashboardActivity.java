package edu.uark.finalproject.DashboardActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import edu.uark.finalproject.ChildProfileActivity.ChildProfileActivity;
import edu.uark.finalproject.ParentProfileActivity.ParentProfileActivity;
import edu.uark.finalproject.R;
import edu.uark.finalproject.VehicleProfileActivity.VehicleProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "DashboardActivity";
    CardView btn_parent_profile, btn_child_profile, btn_vehicle_profile, btn_schedule_pickup,
            btn_map_view, btn_review_pickups;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_parent_profile = (CardView)findViewById(R.id.ParentProfileCard);
        btn_child_profile = (CardView)findViewById(R.id.ChildProfileCard);
        btn_vehicle_profile = (CardView)findViewById(R.id.VehicleProfileCard);
        btn_schedule_pickup = (CardView)findViewById(R.id.SchedulePickupCard);
        btn_map_view = (CardView)findViewById(R.id.MapViewCard);
        btn_review_pickups = (CardView)findViewById(R.id.ReviewPickupsCard);
        btn_parent_profile.setOnClickListener(this);
        btn_child_profile.setOnClickListener(this);
        btn_vehicle_profile.setOnClickListener(this);
        btn_schedule_pickup.setOnClickListener(this);
        btn_map_view.setOnClickListener(this);
        btn_review_pickups.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ParentProfileCard:
                Intent parentAct = new Intent(this, ParentProfileActivity.class);
                startActivity(parentAct);
                break;
            case R.id.ChildProfileCard:
                Intent childAct = new Intent(this, ChildProfileActivity.class);
                startActivity(childAct);
                break;
            case R.id.VehicleProfileCard:
                Intent vehicleAct = new Intent(this, VehicleProfileActivity.class);
                startActivity(vehicleAct);
                break;
            case R.id.SchedulePickupCard:
                Toast.makeText(this, "SchedulePickupCard", Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.schedule_pickup);
                break;
            case R.id.MapViewCard:
                Toast.makeText(this, "MapViewCard", Toast.LENGTH_SHORT).show();
                //setContentView(R.layout.activity_maps);
                break;
            case R.id.ReviewPickupsCard:
                Toast.makeText(this, "ReviewPickupsCard", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}