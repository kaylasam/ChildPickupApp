package edu.uark.finalproject.InboxActivity;

import java.util.List;

import edu.uark.finalproject.data.Message;
import edu.uark.finalproject.data.MessagesRepository;

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
