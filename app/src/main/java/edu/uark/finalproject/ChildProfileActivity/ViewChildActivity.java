package edu.uark.finalproject.ChildProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.Injection;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Children;
import util.AppExecutors;

public class ViewChildActivity extends AppCompatActivity {

    //Creates objects for repository and child table
    ChildPickupRepository childData;
    Children myChild;

    //Variables for information fields
    private TextView Name;
    private TextView Age;
    private TextView Grade;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_child);

        //Sets information fields with xml
        Name = findViewById(R.id.acChildName);
        Age = findViewById(R.id.acChildAge);
        Grade = findViewById(R.id.acChildGrade);

        Intent callingIntent = this.getIntent();
        Integer childId = callingIntent.getIntExtra("child_id",-1);
        myChild = new Children();
        childData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        populateExistingChild(childId);
    }

    //Retrieves child information fields
    private void populateExistingChild(Integer childId){
        Log.d("AddParentActivity","ChildId= "+childId);
        childData.getChild(childId, new ChildPickupDataSource.GetChildCallback() {
            @Override
            public void onChildLoaded(Children children) {
                myChild = children;
                Name.setText(children.getName());
                Age.setText(children.getAge());
                Grade.setText(children.getGrade());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    //Returns to child profile view
    public void onClick(View v){
        finish();
    }
}
