package edu.uark.finalproject.ComposeActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.uark.finalproject.Injection;
import edu.uark.finalproject.R;
import edu.uark.finalproject.data.Message;
import edu.uark.finalproject.data.MessageDataSource;
import edu.uark.finalproject.data.MessagesRepository;
import util.AppExecutors;

public class ComposeActivity extends AppCompatActivity {

    MessagesRepository inbox;
    Message myMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        Intent callingIntent = this.getIntent();
        Integer emailId = callingIntent.getIntExtra("email_id",-1);
        myMessage = new Message();
        inbox = Injection.provideMessagesRepository(new AppExecutors(),getApplicationContext());
        if(emailId == -1){
            populateNewEmail();
        }else{
            populateExistingEmail(emailId);
        }
    }

    private void populateExistingEmail(Integer emailId){
        Log.d("ComposeActivity","EmailId= "+emailId);
        inbox.getMessage(emailId, new MessageDataSource.GetMessageCallback() {
            @Override
            public void onMessageLoaded(Message message) {
                myMessage = message;
                //populateViews();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void populateNewEmail(){
        myMessage = new Message();
        myMessage.setFrom("ahnelson@uark.edu");
        myMessage.setDate(System.currentTimeMillis());
        myMessage.setContent("");
        myMessage.setRead(Boolean.FALSE);
        myMessage.setDraft(Boolean.TRUE);
        myMessage.setSubject("");
        myMessage.setTo("");
        if(inbox!= null){
            inbox.createMessage(myMessage, new MessageDataSource.CreateMessageCallback() {
                @Override
                public void onMessageCreated(int id) {
                    myMessage.setId(id);
                }

                @Override
                public void onMessageCreateFail() {
                    return;
                }
            });
        }
        return;
    }



    public void onClick(View v){
        myMessage.setDraft(false);
        myMessage.setSubject(((TextView)findViewById(R.id.etDetailSubject)).getText().toString());
        inbox.saveMessage(myMessage);
        finish();
    }


}