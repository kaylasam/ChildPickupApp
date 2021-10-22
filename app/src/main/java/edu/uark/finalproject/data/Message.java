package edu.uark.finalproject.data;

import android.content.ContentValues;

import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Message class
 * Implements serializable for easy pass through between intents
 * Includes Room annotations for columns for each of the private members
 */

@Entity
public class Message implements Serializable{

    //Static strings for the column names usable by other classes
    public static final String MESSAGE_ID = "id";
    public static final String MESSAGE_SUBJECT = "subject";
    public static final String MESSAGE_TO = "to";
    public static final String MESSAGE_FROM = "from";
    public static final String MESSAGE_CONTENT = "content";
    public static final String MESSAGE_READ = "read";
    public static final String MESSAGE_DATE = "date";
    public static final String MESSAGE_DRAFT = "draft";


    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = MESSAGE_SUBJECT)
    private String subject;

    @ColumnInfo(name = MESSAGE_TO)
    private String to;

    @ColumnInfo(name = MESSAGE_FROM)
    private String from;

    @ColumnInfo(name = MESSAGE_CONTENT)
    private MessageContent content;

    @ColumnInfo(name= MESSAGE_READ)
    private Boolean read;

    @ColumnInfo(name=MESSAGE_DATE)
    private long date;

    @ColumnInfo(name=MESSAGE_DRAFT)
    private Boolean draft;

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MessageContent getContent() {
        return content;
    }

    public void setContent(MessageContent content) {
        this.content = content;
    }

    public void setContent(String content){
        this.content = new MessageContent(content);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public static Message fromContentValues(ContentValues contentValues){
        Message message = new Message();
        if(contentValues.containsKey(MESSAGE_ID)){
            message.setId(contentValues.getAsInteger(MESSAGE_ID));
        }
        if(contentValues.containsKey(MESSAGE_SUBJECT)){
            message.setSubject(contentValues.getAsString(MESSAGE_SUBJECT));
        }
        if(contentValues.containsKey(MESSAGE_TO)){
            message.setTo(contentValues.getAsString(MESSAGE_TO));
        }
        if(contentValues.containsKey(MESSAGE_FROM)){
            message.setFrom(contentValues.getAsString(MESSAGE_FROM));
        }
        if(contentValues.containsKey(MESSAGE_READ)){
            message.setRead(contentValues.getAsBoolean(MESSAGE_READ));
        }
        if(contentValues.containsKey(MESSAGE_DATE)){
            message.setDate(contentValues.getAsLong(MESSAGE_DATE));
        }
        if(contentValues.containsKey(MESSAGE_CONTENT)){
            message.setContent(new MessageContent(contentValues.getAsString(MESSAGE_CONTENT)));
        }
        if(contentValues.containsKey(MESSAGE_DRAFT)){
            message.setDraft(contentValues.getAsBoolean(MESSAGE_DRAFT));
        }
        return message;
    }
}
