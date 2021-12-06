package edu.uark.finalproject.ParentProfileActivity;

import android.content.Intent;
import android.view.LayoutInflater;
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

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_parent_profile_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ParentProfileViewAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(String.valueOf(localDataSet.get(position).getId()));
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String parentName = localDataSet.get(position).getName();
        holder.getTvParentName().setText(parentName);
        String parentPhone = localDataSet.get(position).getPhone();
        holder.getTvParentNumber().setText(parentPhone);
        String parentEmail = localDataSet.get(position).getEmail();
        holder.getTvParentEmail().setText(parentEmail);

        //Allow user to click an individual child profile and view information
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer parentId = Integer.valueOf((String) view.getTag());
                Intent addparent = new Intent();
                addparent.setClass(view.getContext(), ViewParentActivity.class);
                addparent.putExtra("parent_id", parentId);
                view.getContext().startActivity(addparent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvParentName;
        private final TextView tvParentEmail;
        private final TextView tvParentNumber;

        public ViewHolder(View itemView) {
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
