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

public class AddParentActivity extends AppCompatActivity{

    ChildPickupRepository parentData;
    Parents myParent;

    private EditText Name;
    private EditText Phone;
    private EditText Email;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_parent);

        //Name = findViewById(R.id.apParentName);
        //Phone = findViewById(R.id.apParentPhoneNumber);
        //Email = findViewById(R.id.apParentEmail);

        Intent callingIntent = this.getIntent();
        Integer parentId = callingIntent.getIntExtra("parent_id",-1);
        myParent = new Parents();
        parentData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        if(parentId == -1){
            addNewParent();
        }else{
            populateExistingParent(parentId);
        }
    }

    private void populateExistingParent(Integer parentId){
        Log.d("AddParentActivity","ParentId= "+parentId);
        parentData.getParent(parentId, new ChildPickupDataSource.GetParentCallback() {
            @Override
            public void onParentLoaded(Parents parents) {
                myParent = parents;
                //Name.setText(parents.getName());
                //Phone.setText(parents.getPhone());
                //Email.setText(parents.getEmail());
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void addNewParent(){
        myParent = new Parents();
        myParent.setName("");
        myParent.setPhone("");
        myParent.setEmail("");
        if(parentData!= null){
            parentData.createParent(myParent, new ChildPickupDataSource.CreateParentCallback() {
                @Override
                public void onParentCreated(int id) { myParent.setId(id); }

                @Override
                public void onParentCreateFail() { return; }
            });
        }
        return;
    }

    public void onClick(View v){
        myParent.setName(((TextView)findViewById(R.id.apParentName)).getText().toString());
        myParent.setPhone(((TextView)findViewById(R.id.apParentPhoneNumber)).getText().toString());
        myParent.setEmail(((TextView)findViewById(R.id.apParentEmail)).getText().toString());
        parentData.saveParent(myParent);
        finish();
    }

}
