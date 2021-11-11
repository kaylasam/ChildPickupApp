package edu.uark.finalproject.ChildProfileActivity;

import edu.uark.finalproject.data.ChildPickupRepository;

public interface ChildProfileContract {

    interface View{
    }

    interface Presenter{
        void setView(View mView);
        void setRepository(ChildPickupRepository childData);
    }
}
