package edu.uark.finalproject.ParentProfileActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.uark.finalproject.BuildConfig;
import edu.uark.finalproject.ChildProfileActivity.ChildProfileContract;
import edu.uark.finalproject.ChildProfileActivity.ChildProfilePresenter;
import edu.uark.finalproject.Injection;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Parents;
import util.AppExecutors;

public class ParentProfileActivity extends AppCompatActivity {

    ParentProfileContract.Presenter mPresenter;
    ParentProfileContract.View mView;
    ChildPickupRepository parentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        mPresenter = new ParentProfilePresenter();
        mView = (ParentProfileContract.View) getSupportFragmentManager().findFragmentById(R.id.fragmentParentProfileView);
        mPresenter.setView(mView);
        parentData = Injection.provideDataRepository(new AppExecutors(),getApplicationContext());
        mPresenter.setRepository(parentData);
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

    public void onClick(View v){
        finish();
    }
}
