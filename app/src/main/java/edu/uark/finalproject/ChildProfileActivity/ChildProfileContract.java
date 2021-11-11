package edu.uark.finalproject.ChildProfileActivity;

import java.util.List;

import edu.uark.finalproject.data.ChildPickupRepository;
import edu.uark.finalproject.data.Children;

public interface ChildProfileContract {

    interface View{
        public void setPresenter(Presenter presenter);
        public void notifyChildrenLoaded();
    }

    interface Presenter{
        public void start();
        public void setView(View mView);
        public void setRepository(ChildPickupRepository childData);
        public List<Children> getChildren();
    }
}
