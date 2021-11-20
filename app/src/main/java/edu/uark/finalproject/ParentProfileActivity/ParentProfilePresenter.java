package edu.uark.finalproject.ParentProfileActivity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Parents;

public class ParentProfilePresenter implements ParentProfileContract.Presenter{

    ParentProfileContract.View mView;
    private ChildPickupRepository mParentData;
    private List<Parents> parentList;

    @Override
    public void start() {
        mView.setPresenter(this);
        parentList = new ArrayList<>();
        getParentsFromRepository();
    }

    private void getParentsFromRepository() {
        mParentData.getParents(new ChildPickupDataSource.LoadParentsCallback(){
            @Override
            public void onParentsLoaded(List<Parents> parents) {
                parentList = parents;
                mView.notifyParentsLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("InboxPresenter","Data not loaded");
            }

        });
    }

    @Override
    public void setView(ParentProfileContract.View view) {
        mView = view;
    }

    @Override
    public void setRepository(ChildPickupRepository parentData) {
        mParentData = parentData;
    }

    @Override
    public List<Parents> getParents() {
        return parentList;
    }

    @Override
    public void notifyAddClicked() {
        mView.startAddParentActivity();
    }
}
