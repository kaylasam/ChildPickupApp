package edu.uark.finalproject.VehicleProfileActivity;

import java.util.List;

import edu.uark.finalproject.ParentProfileActivity.ParentProfileContract;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Vehicles;

public interface VehicleProfileContract {

    interface View{
        public void setPresenter(VehicleProfileContract.Presenter presenter);
        public void notifyVehiclesLoaded();
    }

    interface Presenter{
        void setView(View mView);
        void setRepository(ChildPickupRepository vehicleData);
        void start();
        public List<Vehicles> getVehicles();
    }
}
