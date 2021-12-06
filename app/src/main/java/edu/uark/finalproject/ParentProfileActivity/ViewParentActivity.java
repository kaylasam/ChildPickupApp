package edu.uark.finalproject.ParentProfileActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.Injection;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Parents;
import util.AppExecutors;

public class ViewParentActivity extends AppCompatActivity {

    //Creates objects for repository and parent table
    ChildPickupRepository parentData;
    Parents myParent;

    //Variables for information fields
    private TextView Name;
    private TextView Phone;
    private TextView Email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_parent);

        //Sets information fields with xml
        Name = findViewById(R.id.apParentName);
        Phone = findViewById(R.id.apParentPhoneNumber);
        Email = findViewById(R.id.apParentEmail);

        Intent callingIntent = this.getIntent();
        Integer parentId = callingIntent.getIntExtra("parent_id",-1);
        myParent = new Parents();
        parentData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        populateExistingParent(parentId);

    }

    //Retrieves Parent information fields
    private void populateExistingParent(Integer parentId){
        Log.d("AddParentActivity","ParentId= "+parentId);
        parentData.getParent(parentId, new ChildPickupDataSource.GetParentCallback() {
            @Override
            public void onParentLoaded(Parents parents) {
                myParent = parents;
                Name.setText(parents.getName());
                Phone.setText(parents.getPhone());
                Email.setText(parents.getEmail());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    //Returns to parent profile view
    public void onClick(View v){
        finish();
    }

}
