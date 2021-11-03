package edu.uark.finalproject.data;

import java.util.List;

import androidx.annotation.NonNull;

public interface VehiclesDataSource {
    /**
     * LoadMessagesCallback interface
     * Example of how to implement callback functions depending on the result of functions in interfaces
     * Currently, onDataNotAvailable is not implemented
     */
    interface LoadVehiclesCallback {

        void onVehiclesLoaded(List<Vehicles> vehicles);
        void onDataNotAvailable();
    }

    /**
     * GetPhotoCallback interface
     */
    interface GetVehiclesCallback {
        void onVehiclesLoaded(Vehicles vehicles);
        void onDataNotAvailable();
    }

    /**
     * CreatePhotoCallback interface
     * Return the id in createSuccessful
     */
    interface CreateVehicleCallback{
        void onVehicleCreated(int id);
        void onVehicleCreateFail();
    }

    /**
     * DeletePhotoCallback interface
     * Identify when a deletion is successful
     */
    interface DeleteVehicleCallback{
        void onVehicleDeleted();
        void onVehicleDeleteFailure();
    }

    /**
     * getPhotos loads all Photos, calls either success or failure callback
     * @param callback - Callback function
     */
    void getVehicles(@NonNull LoadVehiclesCallback callback);

    /**
     * getPhoto - Get a single Photo
     * @param PhotoId - String of the current PhotoID to be retrieved
     * @param callback - Callback function
     */
    void getVehicle(@NonNull Integer VehicleId, @NonNull GetVehiclesCallback callback);

    /**
     * SavePhoto saves a CommentedPhoto to the database
     * @param photo - Photo to be updated
     */
    void saveVehicle(@NonNull final Vehicles vehicle);

    /**
     * CreatePhoto adds a CommentedPhoto to the database
     * @param photo - Photo to be added
     * @param callback - Callback function after thread completion
     */
    void createVehicle(@NonNull Vehicles vehicle, @NonNull CreateVehicleCallback callback);


    /**
     * deletePhoto deletes a CommentedPhoto from the database
     * @param id
     */
    void deleteVehicle(@NonNull Integer id, @NonNull DeleteVehicleCallback callback);

}


