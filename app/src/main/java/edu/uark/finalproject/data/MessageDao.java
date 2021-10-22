package edu.uark.finalproject.data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MessageDao {
    /**
     * Insert a todoitem into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insert(Message message);    /**
     * select all todoitems
     * @return A {@link Cursor} of all todoitems in the table
     */
    @Query("SELECT * FROM Message")
    Cursor findAll();      /**
     * Delete a todoitem by ID
     * @return A number of todoitems deleted
     */
    @Query("DELETE FROM Message WHERE id = :id ")
    int delete(long id);    /**
     * Update the todoitem
     * @return A number of todoitems updated
     */
    @Update
    int update(Message message);
}
