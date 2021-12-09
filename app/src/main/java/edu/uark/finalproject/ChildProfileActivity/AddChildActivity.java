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

public class AddChildActivity extends AppCompatActivity {

    ChildPickupRepository childData;
    Children myChild;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_child);

        Intent callingIntent = this.getIntent();
        Integer childId = callingIntent.getIntExtra("child_id",-1);
        myChild = new Children();
        childData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        if(childId == -1){
            addNewChild();
        }else{
            populateExistingChild(childId);
        }
    }

    //Loads existing child
    private void populateExistingChild(Integer childId){
        Log.d("AddParentActivity","ChildId= "+childId);
        childData.getChild(childId, new ChildPickupDataSource.GetChildCallback() {
            @Override
            public void onChildLoaded(Children children) {
                myChild = children;
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    //Adds a new child and stores in database
    private void addNewChild(){
        myChild = new Children();
        myChild.setName("");
        myChild.setAge("");
        myChild.setGrade("");
        if(childData!= null){
            childData.createChild(myChild, new ChildPickupDataSource.CreateChildCallback() {
                @Override
                public void onChildCreated(int id) { myChild.setId(id); }

                @Override
                public void onChildCreateFail() { return; }
            });
        }
        return;
    }

    //Sets and saves child information from user input when "Add Child" button clicked
    public void onClick(View v){
        myChild.setName(((TextView)findViewById(R.id.acChildName)).getText().toString());
        myChild.setAge(((TextView)findViewById(R.id.acChildAge)).getText().toString());
        myChild.setGrade(((TextView)findViewById(R.id.acChildGrade)).getText().toString());
        childData.saveChild(myChild);
        finish();
    }
}
