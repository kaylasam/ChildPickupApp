package edu.uark.finalproject.ReviewPickupsActivity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.uark.finalproject.data.ChildPickupDataSource;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.ReviewPickups;

public class ReviewPickupsPresenter implements ReviewPickupsContract.Presenter{

    ReviewPickupsContract.View mView;
    private ChildPickupRepository mReviewPickupData;
    private List<ReviewPickups> reviewPickupList;

    @Override
    public void start() {
        mView.setPresenter(this);
        reviewPickupList = new ArrayList<>();
        getReviewPickupsFromRepository();
    }

    private void getReviewPickupsFromRepository() {
        mReviewPickupData.getReviewPickups(new ChildPickupDataSource.LoadReviewPickupsCallback(){
            @Override
            public void onReviewPickupsLoaded(List<ReviewPickups> pickups) {
                reviewPickupList = pickups;
                mView.notifyReviewPickupsLoaded();
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("InboxPresenter","Data not loaded");
            }

        });
    }

    @Override
    public void setView(ReviewPickupsContract.View view) {
        mView = view;
    }

    @Override
    public void setRepository(ChildPickupRepository reviewPickupData) {
        mReviewPickupData = reviewPickupData;
    }

    @Override
    public List<ReviewPickups> getReviewPickups() {
        return reviewPickupList;
    }
}
