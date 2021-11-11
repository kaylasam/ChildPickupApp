package edu.uark.finalproject.ChildProfileActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.Children;

public class ChildProfileViewAdapter extends RecyclerView.Adapter<ChildProfileViewAdapter.ViewHolder> {

    public void setLocalDataSet(List<Children> localDataSet) {
        this.localDataSet = localDataSet;
    }

    private List<Children> localDataSet;

    public ChildProfileViewAdapter(List<Children> children) {
        localDataSet = children;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String childName = localDataSet.get(position).getName();
        holder.getTvChildName().setText(childName);
        String childAge = localDataSet.get(position).getAge().toString();
        holder.getTvChildAge().setText(childAge);
        String childGrade = localDataSet.get(position).getGrade().toString();
        holder.getTvChildGrade().setText(childGrade);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvChildName;
        private final TextView tvChildAge;
        private final TextView tvChildGrade;

        public ViewHolder(View itemView) {
            super(itemView);
            tvChildName = (TextView)itemView.findViewById(R.id.tvChildName);
            tvChildAge = (TextView) itemView.findViewById(R.id.tvChildAge);
            tvChildGrade = (TextView) itemView.findViewById(R.id.tvChildGrade);
        }

        public TextView getTvChildName() {
            return tvChildName;
        }

        public TextView getTvChildAge() {
            return tvChildAge;
        }

        public TextView getTvChildGrade() {
            return tvChildGrade;
        }
    }
}
