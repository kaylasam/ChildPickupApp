package edu.uark.finalproject.VehicleProfileActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.BuildConfig;
import edu.uark.finalproject.Injection;
import edu.uark.finalproject.ParentProfileActivity.ParentProfileContract;
import edu.uark.finalproject.ParentProfileActivity.ParentProfilePresenter;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.ChildPickupRepository;
import util.AppExecutors;

public class VehicleProfileActivity extends AppCompatActivity {

    VehicleProfileContract.Presenter mPresenter;
    VehicleProfileContract.View mView;
    ChildPickupRepository vehicleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_profile);
        mPresenter = new VehicleProfilePresenter();
        mView = (VehicleProfileContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentVehicleProfileView);
        mPresenter.setView(mView);
        vehicleData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        mPresenter.setRepository(vehicleData);
        checkFirstRun();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void checkFirstRun() {

        final String PREFS_NAME = "MyPrefsFile";
        final String PREF_VERSION_CODE_KEY = "version_code";
        final int DOESNT_EXIST = -1;

        // Get current version code
        int currentVersionCode = BuildConfig.VERSION_CODE;

        // Get saved version code
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedVersionCode = prefs.getInt(PREF_VERSION_CODE_KEY, DOESNT_EXIST);

        // Check for first run or upgrade
        if (currentVersionCode == savedVersionCode) {

            // This is just a normal run
            return;

        } else if (savedVersionCode == DOESNT_EXIST) {
            //generateDummyData();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

            // TODO This is a new install (or the user cleared the shared preferences)

        } else if (currentVersionCode > savedVersionCode) {
            //generateDummyData();
            prefs.edit().putInt(PREF_VERSION_CODE_KEY, currentVersionCode).apply();

            // TODO This is an upgrade
        }

        // Update the shared preferences with the current version code
    }

}
