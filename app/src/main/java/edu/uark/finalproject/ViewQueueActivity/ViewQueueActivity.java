package edu.uark.finalproject.ViewQueueActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.uark.finalproject.BuildConfig;
import edu.uark.finalproject.Injection;
import edu.uark.finalproject.ParentProfileActivity.ParentProfileContract;
import edu.uark.finalproject.ParentProfileActivity.ParentProfilePresenter;
import edu.uark.finalproject.R;
import edu.uark.finalproject.ReviewPickupsActivity.ReviewPickupsContract;
import edu.uark.finalproject.ReviewPickupsActivity.ReviewPickupsPresenter;
import edu.uark.finalproject.data.ChildPickupRepository;
import util.AppExecutors;

public class ViewQueueActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String[] parentUsers = {"George Washington", "Wendy Williams", "Alex Nelson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parent_queue);

        mRecyclerView = (RecyclerView) findViewById(R.id.rvParentQueue);

//        parentUsersList = new ArrayList<>();
//
//        User firstParent = new User("George Washington");
//        parentUsersList.add(firstParent);
//        User secondParent = new User("Wendy Williams");
//        parentUsersList.add(secondParent);
//        User thirdParent = new User("Alex Nelson");
//        parentUsersList.add(thirdParent);
//
//        mRecyclerView = (RecyclerView) findViewById(R.id.rvParentQueue);
//       // mRecyclerView.setHasFixedSize(true);
//        //parentQueue.setLayoutManager();
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter = new ViewQueueAdapter(parentUsersList);

    }

    @Override
    protected void onStart() {
        super.onStart();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewQueueAdapter viewQueueAdapter = new ViewQueueAdapter(this, parentUsers);
        mRecyclerView.setAdapter(viewQueueAdapter);
    }
}
