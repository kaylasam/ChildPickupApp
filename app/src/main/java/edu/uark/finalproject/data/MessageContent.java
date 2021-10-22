package edu.uark.finalproject.data;

import androidx.annotation.NonNull;
import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import java.io.Serializable;

public class MessageContent implements Serializable {

    public String contentString;
    public MessageContent(){
        contentString = "";
    }

    @NonNull
    @Override
    public String toString() {
        return contentString;
    }

    public MessageContent(String content){
        contentString = content;
    }

}

