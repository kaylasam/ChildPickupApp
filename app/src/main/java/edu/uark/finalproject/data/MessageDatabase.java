package edu.uark.finalproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Message.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class MessageDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "messages_db";
    private static MessageDatabase INSTANCE;

    public static MessageDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,MessageDatabase.class,DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract MessageDao getMessageDao();
}
