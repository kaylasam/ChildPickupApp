package edu.uark.finalproject.ViewQueueActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.R;

public class ViewQueueAdapter extends RecyclerView.Adapter<ViewQueueViewHolder> {

    Context c;
    ArrayList<String> parentUsers;

    public ViewQueueAdapter(Context c, ArrayList<String> parentUsers) {
        this.c = c;
        this.parentUsers = parentUsers;
    }

    @Override
    public ViewQueueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.rv_parent_queue_item, parent, false);
        return new ViewQueueViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewQueueViewHolder holder, int position) {
        holder.tvParentName.setText(parentUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return parentUsers.size();
    }
}
