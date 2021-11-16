package edu.uark.finalproject.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(
        // associate database with parent, child, and vehicle entities
        entities = {
                Children.class,
                Parents.class,
                Vehicles.class,
                ReviewPickups.class
        },
        version = 1,
        exportSchema = false
)
public abstract class ChildPickupDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "child_pickup_db";

    // below line is to create instance
    // for our database class.
    private static ChildPickupDatabase INSTANCE;

    // on below line we are getting instance for our database.
    public static ChildPickupDatabase getInstance(Context context){

        // below line is to check if
        // the instance is null or not.
        if(INSTANCE == null){
            // if the instance is null we
            // are creating a new instance
            INSTANCE =
                    // for creating a instance for our database
                    // we are creating a database builder and passing
                    // our database class with our database name.
                    Room.databaseBuilder(context,ChildPickupDatabase.class,DATABASE_NAME)
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
    public abstract ChildPickupDao getChildPickupDao();

}




