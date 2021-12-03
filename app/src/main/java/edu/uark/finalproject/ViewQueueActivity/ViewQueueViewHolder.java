package edu.uark.finalproject.ViewQueueActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.ChildProfileActivity.ViewChildActivity;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.Children;
import edu.uark.finalproject.data.ReviewPickups;

public class ViewQueueViewHolder extends RecyclerView.ViewHolder {

    TextView tvParentName;

    public ViewQueueViewHolder(View view){
        super(view);

        tvParentName = (TextView) view.findViewById(R.id.tvQueueName);
    }

}
