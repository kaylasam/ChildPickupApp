package edu.uark.finalproject.data;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// Adding annotation
// to our Dao class
@Dao
public interface ChildPickupDao {

    // CHILD DAO
    /**
     * Insert a Child into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insertChild(Children child);

    /**
     * select all Children
     * @return A {@link Cursor} of all children in the table
     */
    @Query("SELECT * FROM Children")
    Cursor findAllChildren();

    /**
     * Delete a Child by ID
     * @return A number of Children deleted
     */
    @Query("DELETE FROM Children WHERE id = :id ")
    int deleteChild(long id);

    /**
     * Update the Child
     * @return A number of Children updated
     */
    @Update
    int updateChild(Children child);

    // PARENT DAO
    /**
     * Insert a Parent into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insertParent(Parents parent);

    /**
     * select all Parents
     * @return A {@link Cursor} of all parents in the table
     */
    @Query("SELECT * FROM Parents")
    Cursor findAllParents();

    /**
     * Delete a Parent by ID
     * @return A number of Parents deleted
     */
    @Query("DELETE FROM Parents WHERE id = :id ")
    int deleteParent(long id);

    /**
     * Update the Parent
     * @return A number of Parents updated
     */
    @Update
    int updateParent(Parents parent);

    // VEHICLE DAO
    /**
     * Insert a Vehicle into the table
     * @return row ID for newly inserted data
     */
    @Insert
    long insertVehicle(Vehicles vehicle);

    /**
     * select all Vehicles
     * @return A {@link Cursor} of all Vehicles in the table
     */
    @Query("SELECT * FROM Vehicles")
    Cursor findAllVehicles();

    /**
     * Delete a Vehicle by ID
     * @return A number of Vehicles deleted
     */
    @Query("DELETE FROM Vehicles WHERE id = :id ")
    int deleteVehicle(long id);

    /**
     * Update the Vehicles
     * @return A number of Vehicles updated
     */
    @Update
    int updateVehicle(Vehicles vehicle);
}


