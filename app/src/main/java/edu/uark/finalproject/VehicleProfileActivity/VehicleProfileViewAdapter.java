package edu.uark.finalproject.VehicleProfileActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.uark.finalproject.ParentProfileActivity.ParentProfileViewAdapter;
import edu.uark.finalproject.ParentProfileActivity.ViewParentActivity;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.Vehicles;

public class VehicleProfileViewAdapter extends RecyclerView.Adapter<VehicleProfileViewAdapter.ViewHolder>{

    public void setLocalDataSet(List<Vehicles> localDataSet) {
        this.localDataSet = localDataSet;
    }

    private List <Vehicles> localDataSet;

    public VehicleProfileViewAdapter(List<Vehicles> vehicles) {
        localDataSet = vehicles;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_vehicle_profile_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleProfileViewAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(String.valueOf(localDataSet.get(position).getId()));
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String vehicleMake = localDataSet.get(position).getMake();
        holder.getTvVehicleMake().setText(vehicleMake);
        String vehicleModel = localDataSet.get(position).getModel();
        holder.getTvVehicleModel().setText(vehicleModel);
        String vehicleColor = localDataSet.get(position).getColor();
        holder.getTvVehicleColor().setText(vehicleColor);

        //Allow user to click an individual vehicle profile and view information
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer vehicleId = Integer.valueOf((String) view.getTag());
                Intent addvehicle = new Intent();
                addvehicle.setClass(view.getContext(), ViewVehicleActivity.class);
                addvehicle.putExtra("vehicle_id", vehicleId);
                view.getContext().startActivity(addvehicle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvVehicleMake;
        private final TextView tvVehicleModel;
        private final TextView tvVehicleColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvVehicleMake = (TextView) itemView.findViewById(R.id.tvVehicleMake);
            tvVehicleModel = (TextView) itemView.findViewById(R.id.tvVehicleModel);
            tvVehicleColor = (TextView) itemView.findViewById(R.id.tvVehicleColor);
        }

        public TextView getTvVehicleMake() {
            return tvVehicleMake;
        }

        public TextView getTvVehicleModel() {
            return tvVehicleModel;
        }

        public TextView getTvVehicleColor() {
            return tvVehicleColor;
        }
    }
}
