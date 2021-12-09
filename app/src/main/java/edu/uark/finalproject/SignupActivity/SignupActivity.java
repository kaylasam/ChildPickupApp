package edu.uark.finalproject.SignupActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.uark.finalproject.DashboardActivity.DashboardActivity;
import edu.uark.finalproject.R;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;


public class SignupActivity extends AppCompatActivity {

    private ImageView parentPic, childPic, vehiclePic;
    private ImageButton backBTN, gpsBTN;
    private EditText parName, phone, email, password, make, model, color, year,
            license, childName, childAge, childGrade;
    TextView registerBTN;


    //permission constants
    private static final int STORAGE_REQUEST_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 200;

    //image pick constants
    private static final int IMAGE_PICK_GALLERY_CODE = 300;
    private static final int IMAGE_PICK_CAMERA_CODE = 400;

    //permission arrays
    private String[] cameraPermissions;
    private String[] storagePermissions;

    //Image uri
    private Uri image_uri;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://child-pickup-app-default-rtdb.firebaseio.com/");
    private ProgressDialog progressDialog;
    private DatabaseReference mRootReference = firebaseDatabase.getReference();
    private DatabaseReference mName = mRootReference.child("parent");
    private FirebaseDatabase rootNode;
    private DatabaseReference db_reference;

    TextView nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registerBTN = findViewById(R.id.next);
        parName = findViewById(R.id.username_input);
        phone = findViewById(R.id.phoneNumber_input);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        make = findViewById(R.id.car_input);
        color = findViewById(R.id.carColor_input);
        license = findViewById(R.id.licensePlate_input);
        childName = findViewById(R.id.childName_input);
        childAge = findViewById(R.id.childAge_input);
        childGrade = findViewById(R.id.child_grade_input);

        //init permission array
        cameraPermissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        firebaseAuth = FirebaseAuth.getInstance();

        // Hide the top menu bar
        getSupportActionBar().hide();

        nextButton = findViewById(R.id.next);

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
                Intent intent = new Intent(v.getContext(), SignupActivityAddImages.class);
                startActivity(intent);
            }
        });

    }
    private String fullname, phonenumber, pemail, ppassword, car_input, vcolor, vlicense, childname, childage, childgrade;

    private void inputData(){

        //input data
        fullname = parName.getEditableText().toString();
        phonenumber = phone.getText().toString().trim();
        pemail = email.getText().toString().trim();
        ppassword = password.getText().toString().trim();
        car_input = make.getText().toString().trim();
        vcolor = color.getText().toString().trim();
        vlicense = license.getText().toString().trim();
        childname = childName.getText().toString().trim();
        childage = childAge.getText().toString().trim();
        childgrade = childGrade.getText().toString().trim();


        //makes sure fields are not emptys
        if (TextUtils.isEmpty(fullname)){
            Toast.makeText(this, "Enter Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phonenumber)){
            Toast.makeText(this, "Enter Phone Number...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pemail)){
            Toast.makeText(this, "Enter Email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ppassword)){
            Toast.makeText(this, "Enter Password...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(car_input)) {
            Toast.makeText(this, "Enter Make...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(vcolor)){
            Toast.makeText(this, "Enter Color...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(vlicense)){
            Toast.makeText(this, "Enter License...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(childname)){
            Toast.makeText(this, "Enter Child Name...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(childage)){
            Toast.makeText(this, "Enter Age...", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(childgrade)){
            Toast.makeText(this, "Enter Grade...", Toast.LENGTH_SHORT).show();
            return;
        }
        saveFirebaseData();
    }

    private void saveFirebaseData() {
        //connect to firebase database
        rootNode = FirebaseDatabase.getInstance("https://child-pickup-app-default-rtdb.firebaseio.com/");
        db_reference = rootNode.getReference("Parents");

        //setup data
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("childgrade", "" + childgrade);
        hashMap.put("childage", "" + childage);
        hashMap.put("childname", "" + childname);
        hashMap.put("color", "" + vcolor);
        hashMap.put("make", "" + car_input);
        hashMap.put("password", "" + ppassword);
        hashMap.put("email", "" + pemail);
        hashMap.put("name", "" + fullname);
        hashMap.put("pid", "" + firebaseAuth.getUid());

        //save to database
        db_reference.setValue(hashMap);

    }

}