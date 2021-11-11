package edu.uark.finalproject.VehicleProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.ParentProfileActivity.ParentProfileViewAdapter;
import edu.uark.finalproject.R;

public class VehicleProfileViewFragment extends Fragment implements VehicleProfileContract.View{

    private VehicleProfileContract.Presenter mPresenter;
    private RecyclerView rvVehicleProfile;

    public VehicleProfileViewFragment() {
        // Required empty public constructor
    }

    public static VehicleProfileViewFragment newInstance() {
        VehicleProfileViewFragment fragment = new VehicleProfileViewFragment();
        return fragment;
    }

    @Override
    public void onResume(){
        super.onResume();
        rvVehicleProfile.setAdapter(new VehicleProfileViewAdapter(mPresenter.getVehicles()));
        rvVehicleProfile.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_vehicle_profile_view, container, false);
        Button button = root.findViewById(R.id.vehicleBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });
        rvVehicleProfile = root.findViewById(R.id.rvAuthorizedVehicles);
        return root;
    }

    @Override
    public void setPresenter(VehicleProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void notifyVehiclesLoaded() {
        ((VehicleProfileViewAdapter)rvVehicleProfile.getAdapter()).setLocalDataSet(mPresenter.getVehicles());
        rvVehicleProfile.getAdapter().notifyDataSetChanged();

    }
}
