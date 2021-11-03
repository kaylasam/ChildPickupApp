package edu.uark.finalproject.data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// Adding annotation
// to our Dao class
@Dao
public interface ParentsDao {
    /**
     * Insert a Parent into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insert(Parents parent);

    /**
     * select all Parents
     * @return A {@link Cursor} of all parents in the table
     */
    @Query("SELECT * FROM Parents")
    Cursor findAll();

    /**
     * Delete a Parent by ID
     * @return A number of Parents deleted
     */
    @Query("DELETE FROM Parents WHERE id = :id ")
    int delete(long id);

    /**
     * Update the Parent
     * @return A number of Parents updated
     */
    @Update
    int update(Parents parent);
}


