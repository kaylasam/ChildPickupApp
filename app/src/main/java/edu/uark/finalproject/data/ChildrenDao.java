package edu.uark.finalproject.data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// Adding annotation
// to our Dao class
@Dao
public interface ChildrenDao {
    /**
     * Insert a Child into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insert(Children child);

    /**
     * select all Children
     * @return A {@link Cursor} of all children in the table
     */
    @Query("SELECT * FROM Children")
    Cursor findAll();

    /**
     * Delete a Child by ID
     * @return A number of Children deleted
     */
    @Query("DELETE FROM Children WHERE id = :id ")
    int delete(long id);

    /**
     * Update the Child
     * @return A number of Children updated
     */
    @Update
    int update(Children child);
}


