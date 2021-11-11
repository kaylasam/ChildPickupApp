package edu.uark.finalproject.VehicleProfileActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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

    @NonNull
    @Override
    public VehicleProfileViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleProfileViewAdapter.ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String vehicleMake = localDataSet.get(position).getMake();
        holder.getTvVehicleMake().setText(vehicleMake);
        String vehicleModel = localDataSet.get(position).getModel();
        holder.getTvVehicleModel().setText(vehicleModel);
        String vehicleColor = localDataSet.get(position).getColor();
        holder.getTvVehicleColor().setText(vehicleColor);
    }

    @Override
    public int getItemCount() {
        return 0;
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
