package edu.uark.finalproject.data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// Adding annotation
// to our Dao class
@Dao
public interface VehiclesDao {
    /**
     * Insert a Vehicle into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insert(Vehicles vehicle);

    /**
     * select all Vehicles
     * @return A {@link Cursor} of all Vehicles in the table
     */
    @Query("SELECT * FROM Vehicles")
    Cursor findAll();

    /**
     * Delete a Vehicle by ID
     * @return A number of Vehicles deleted
     */
    @Query("DELETE FROM Vehicles WHERE id = :id ")
    int delete(long id);

    /**
     * Update the Vehicles
     * @return A number of Vehicles updated
     */
    @Update
    int update(Vehicles vehicle);
}


