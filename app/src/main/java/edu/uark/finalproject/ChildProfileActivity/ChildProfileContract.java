package edu.uark.finalproject.ChildProfileActivity;

import java.util.List;

import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Children;

public interface ChildProfileContract {

    interface View{
        void setPresenter(Presenter presenter);
        void notifyChildrenLoaded();
        void startAddChildActivity();
    }

    interface Presenter{
        void start();
        void setView(View mView);
        void setRepository(ChildPickupRepository childData);
        List<Children> getChildren();
        void notifyAddClicked();
    }
}
