package edu.uark.finalproject.VehicleProfileActivity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Parents;
import edu.uark.finalproject.data.Vehicles;

public class VehicleProfilePresenter implements VehicleProfileContract.Presenter{

    VehicleProfileContract.View mView;
    private ChildPickupRepository mVehicleData;
    private List<Vehicles> vehicleList;

    @Override
    public void start() {
        mView.setPresenter(this);
        vehicleList = new ArrayList<>();
        getVehiclesFromRepository();
    }

    private void getVehiclesFromRepository() {
        mVehicleData.getVehicles(new ChildPickupDataSource.LoadVehiclesCallback(){
            @Override
            public void onVehiclesLoaded(List<Vehicles> vehicles) {
                vehicleList = vehicles;
                mView.notifyVehiclesLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("InboxPresenter","Data not loaded");
            }

        });
    }

    @Override
    public void setView(VehicleProfileContract.View view) {
        mView = view;
    }

    @Override
    public void setRepository(ChildPickupRepository vehicleData) {
        mVehicleData = vehicleData;
    }

    @Override
    public List<Vehicles> getVehicles() {
        return vehicleList;
    }

    @Override
    public void notifyAddClicked() {
        mView.startAddVehicleActivity();
    }
}
