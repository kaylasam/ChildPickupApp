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

public class AddVehicleActivity extends AppCompatActivity {

    //Creates objects for repository and vehicle table
    ChildPickupRepository vehicleData;
    Vehicles myVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vehicle);

        Intent callingIntent = this.getIntent();
        Integer vehicleId = callingIntent.getIntExtra("vehicle_id",-1);
        myVehicle = new Vehicles();
        vehicleData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());

        //If there are no vehicles then add new vehicle otherwise show existing vehicles
        if(vehicleId == -1){
            addNewVehicle();
        }else{
            populateExistingVehicle(vehicleId);
        }
    }

    //Loads existing vehicle
    private void populateExistingVehicle(Integer vehicleId){
        Log.d("AddVehicleActivity","VehicleId= "+vehicleId);
        vehicleData.getVehicle(vehicleId, new ChildPickupDataSource.GetVehiclesCallback() {
            @Override
            public void onVehiclesLoaded(Vehicles vehicle) {
                myVehicle = vehicle;
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    //Adds a new vehicle and stores in database
    private void addNewVehicle(){
        myVehicle = new Vehicles();
        myVehicle.setMake("");
        myVehicle.setModel("");
        myVehicle.setColor("");
        if(vehicleData!= null){
            vehicleData.createVehicle(myVehicle, new ChildPickupDataSource.CreateVehicleCallback() {
                @Override
                public void onVehicleCreated(int id) { myVehicle.setId(id); }

                @Override
                public void onVehicleCreateFail() { return; }
            });
        }
        return;
    }

    //Sets and saves vehicle information from user input when "Add Vehicle" button clicked
    public void onClick(View v){
        myVehicle.setMake(((TextView)findViewById(R.id.avVehicleMake)).getText().toString());
        myVehicle.setModel(((TextView)findViewById(R.id.avVehicleModel)).getText().toString());
        myVehicle.setColor(((TextView)findViewById(R.id.avVehicleColor)).getText().toString());
        vehicleData.saveVehicle(myVehicle);
        finish();
    }
}
