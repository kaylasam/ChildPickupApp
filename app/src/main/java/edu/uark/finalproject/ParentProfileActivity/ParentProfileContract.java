package edu.uark.finalproject.ParentProfileActivity;

import java.util.List;

import edu.uark.finalproject.ChildProfileActivity.ChildProfileContract;
import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Children;
import edu.uark.finalproject.data.Parents;

public interface ParentProfileContract {

    interface View{
        public void setPresenter(ParentProfileContract.Presenter presenter);
        public void notifyParentsLoaded();
        public void startAddParentActivity();
    }

    interface Presenter{
        public void start();
        public void setView(ParentProfileContract.View mView);
        public void setRepository(ChildPickupRepository parentData);
        public List<Parents> getParents();
        public void notifyAddClicked();
    }
}
