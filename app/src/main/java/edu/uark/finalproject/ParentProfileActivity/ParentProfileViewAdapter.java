package edu.uark.finalproject.ParentProfileActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.Parents;

public class ParentProfileViewAdapter extends RecyclerView.Adapter<ParentProfileViewAdapter.ViewHolder>{

    public void setLocalDataSet(List<Parents> localDataSet) {
        this.localDataSet = localDataSet;
    }

    private List <Parents> localDataSet;

    public ParentProfileViewAdapter(List<Parents> parents) {
        localDataSet = parents;
    }

    @NonNull
    @Override
    public ParentProfileViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ParentProfileViewAdapter.ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String parentName = localDataSet.get(position).getName();
        holder.getTvParentName().setText(parentName);
        String parentPhone = localDataSet.get(position).getPhone();
        holder.getTvParentNumber().setText(parentPhone);
        String parentEmail = localDataSet.get(position).getEmail();
        holder.getTvParentEmail().setText(parentEmail);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvParentName;
        private final TextView tvParentEmail;
        private final TextView tvParentNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvParentEmail = (TextView) itemView.findViewById(R.id.tvParentEmail);
            tvParentName = (TextView) itemView.findViewById(R.id.tvParentName);
            tvParentNumber = (TextView) itemView.findViewById(R.id.tvParentPhoneNumber);
        }

        public TextView getTvParentName() {
            return tvParentName;
        }

        public TextView getTvParentEmail() {
            return tvParentEmail;
        }

        public TextView getTvParentNumber() {
            return tvParentNumber;
        }
    }
}
