package edu.uark.finalproject.ViewQueueActivity;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.R;

public class ViewQueueActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ArrayList<String> parentUsers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parent_queue);

        // mock parent queue data
        parentUsers.add("George Washington");
        parentUsers.add("Wendy Williams");
        parentUsers.add("Alex Nelson");

        mRecyclerView = (RecyclerView) findViewById(R.id.rvParentQueue);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewQueueAdapter viewQueueAdapter = new ViewQueueAdapter(this, parentUsers);
        mRecyclerView.setAdapter(viewQueueAdapter);
    }
}
