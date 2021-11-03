package edu.uark.finalproject.InboxActivity;

import java.util.List;

public interface InboxContract {

    interface View{
        public void setPresenter(Presenter presenter);
        public void startComposeActivity();
        public void notifyMessagesLoaded();
    }

    interface Presenter{
        public void setView(View view);
        public void start();
        public void sendClicked();
        public void setRepository(MessagesRepository inbox);
        public List<Message> getMessages();
        public void getMessagesFromRepository();
    }

}
