package edu.uark.finalproject.data.dummydata;

import java.util.Random;

import edu.uark.finalproject.data.Message;

public class Post {

    private int userId;
    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message PostToMessage(User user){
        Random rand = new Random();
        Message message = new Message();
        message.setSubject(this.title);
        message.setContent(this.body);
        message.setRead(false);
        message.setDraft(false);
        message.setFrom(user.getEmail());
        message.setTo("me");
        message.setDate(System.currentTimeMillis()- rand.nextInt(1500000));
        return message;
    }

}
