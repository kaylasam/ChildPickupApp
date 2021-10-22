package edu.uark.finalproject.data;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static MessageContent messageContentfromString(String content) {
        return new MessageContent(content);
    }

    @TypeConverter
    public static String messageContentToString(MessageContent content) {
        return content.toString();
    }
}
