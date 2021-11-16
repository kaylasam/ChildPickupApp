package edu.uark.finalproject.ReviewPickupsActivity;

import java.util.List;

import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.ReviewPickups;

public interface ReviewPickupsContract {
    interface View{
        public void setPresenter(ReviewPickupsContract.Presenter presenter);
        public void notifyReviewPickupsLoaded();
    }

    interface Presenter{
        public void start();
        public void setView(ReviewPickupsContract.View mView);
        public void setRepository(ChildPickupRepository reviewPickupsData);
        public List<ReviewPickups> getReviewPickups();
    }
}
