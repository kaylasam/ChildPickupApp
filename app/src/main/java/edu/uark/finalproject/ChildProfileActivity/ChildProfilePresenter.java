package edu.uark.finalproject.ChildProfileActivity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Children;

public class ChildProfilePresenter implements ChildProfileContract.Presenter{

    private ChildProfileContract.View mView;
    private ChildPickupRepository mChildData;
    private List<Children> childrenList;

    @Override
    public void setView(ChildProfileContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
        childrenList = new ArrayList<>();
        getChildrenFromRepository();
    }

    private void getChildrenFromRepository() {
        mChildData.getChildren(new ChildPickupDataSource.LoadChildrenCallback() {

            @Override
            public void onChildrenLoaded(List<Children> children) {
                childrenList = children;
                mView.notifyChildrenLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("InboxPresenter","Data not loaded");
            }
        });
    }

    @Override
    public void setRepository(ChildPickupRepository childData) {
        mChildData = childData;
    }

    @Override
    public List<Children> getChildren() {
        return childrenList;
    }
}
