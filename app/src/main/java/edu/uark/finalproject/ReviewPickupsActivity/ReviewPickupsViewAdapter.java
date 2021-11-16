package edu.uark.finalproject.ReviewPickupsActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.ReviewPickups;

public class ReviewPickupsViewAdapter extends RecyclerView.Adapter<ReviewPickupsViewAdapter.ViewHolder>{

    public void setLocalDataSet(List<ReviewPickups> localDataSet) {
        this.localDataSet = localDataSet;
    }

    private List <ReviewPickups> localDataSet;

    public ReviewPickupsViewAdapter(List<ReviewPickups> reviewPickups) {
        localDataSet = reviewPickups;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReviewPickupsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_review_pickups_item, viewGroup, false);

        return new ReviewPickupsViewAdapter.ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ReviewPickupsViewAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(String.valueOf(localDataSet.get(position).getId()));
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        String reviewPickupDate = localDataSet.get(position).getDate();
        holder.getTvReviewPickupDate().setText(reviewPickupDate);
        String reviewPickupTime = localDataSet.get(position).getTime();
        holder.getTvReviewPickupTime().setText(reviewPickupTime);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvReviewPickupDate;
        private final TextView tvReviewPickupTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvReviewPickupDate = (TextView) itemView.findViewById(R.id.tvReviewPickupDate);
            tvReviewPickupTime = (TextView) itemView.findViewById(R.id.tvReviewPickupTime);
        }

        public TextView getTvReviewPickupDate() {
            return tvReviewPickupDate;
        }

        public TextView getTvReviewPickupTime() {
            return tvReviewPickupTime;
        }
    }
}
