package edu.uark.finalproject.VehicleProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.Injection;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Vehicles;
import util.AppExecutors;

public class ViewVehicleActivity extends AppCompatActivity {

    //Creates objects for repository and vehicle table
    ChildPickupRepository vehicleData;
    Vehicles myVehicle;

    //Variables for information fields
    private TextView Make;
    private TextView Model;
    private TextView Color;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_vehicle);

        //Sets information fields with xml
        Make = findViewById(R.id.vvVehicleMake);
        Model = findViewById(R.id.vvVehicleModel);
        Color = findViewById(R.id.vvVehicleColor);

        Intent callingIntent = this.getIntent();
        Integer vehicleId = callingIntent.getIntExtra("vehicle_id",-1);
        myVehicle = new Vehicles();
        vehicleData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        populateExistingVehicle(vehicleId);
    }

    //Retrieves vehicle information fields
    private void populateExistingVehicle(Integer vehicleId){
        Log.d("AddVehicleActivity","VehicleId= "+vehicleId);
        vehicleData.getVehicle(vehicleId, new ChildPickupDataSource.GetVehiclesCallback() {
            @Override
            public void onVehiclesLoaded(Vehicles vehicle) {
                myVehicle = vehicle;
                Make.setText(vehicle.getMake());
                Model.setText(vehicle.getModel());
                Color.setText(vehicle.getColor());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    //Returns to vehicle profile view
    public void onClick(View v){
        finish();
    }
}
