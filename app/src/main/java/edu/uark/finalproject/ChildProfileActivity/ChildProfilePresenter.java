package edu.uark.finalproject.ChildProfileActivity;

import edu.uark.finalproject.data.ChildPickupRepository;

public class ChildProfilePresenter implements ChildProfileContract.Presenter{

    private ChildProfileContract.View mView;
    private ChildPickupRepository mChildData;

    @Override
    public void setView(ChildProfileContract.View view) {
        mView = view;
    }

    @Override
    public void setRepository(ChildPickupRepository childData) {
        mChildData = childData;
    }
}
