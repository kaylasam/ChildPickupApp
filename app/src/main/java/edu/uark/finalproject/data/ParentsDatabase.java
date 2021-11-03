package edu.uark.finalproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Parents.class}, version = 1, exportSchema = false)
public abstract class ParentsDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "parents_db";

    // below line is to create instance
    // for our database class.
    private static ParentsDatabase INSTANCE;

    // on below line we are getting instance for our database.
    public static ParentsDatabase getInstance(Context context){

        // below line is to check if
        // the instance is null or not.
        if(INSTANCE == null){
            // if the instance is null we
            // are creating a new instance
            INSTANCE =
                    // for creating a instance for our database
                    // we are creating a database builder and passing
                    // our database class with our database name.
                    Room.databaseBuilder(context, ParentsDatabase.class,DATABASE_NAME)
                            // below line is to
                            // build our database.
                            .build();
        }
        // after creating an instance
        // we are returning our instance
        return INSTANCE;
    }

    // below line is to create
    // abstract variable for dao.
    public abstract ParentsDao getCommentedPhotoDao();

}




