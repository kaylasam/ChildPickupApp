package edu.uark.finalproject.data;

import java.util.List;

import androidx.annotation.NonNull;

public interface ChildPickupDataSource {
    // CHILD DATA SOURCE
    /**
     * LoadMessagesCallback interface
     * Example of how to implement callback functions depending on the result of functions in interfaces
     * Currently, onDataNotAvailable is not implemented
     */
    interface LoadChildrenCallback {

        void onChildrenLoaded(List<Children> children);
        void onDataNotAvailable();
    }

    /**
     * GetPhotoCallback interface
     */
    interface GetChildCallback {
        void onChildLoaded(Children children);
        void onDataNotAvailable();
    }

    /**
     * CreatePhotoCallback interface
     * Return the id in createSuccessful
     */
    interface CreateChildCallback{
        void onChildCreated(int id);
        void onChildCreateFail();
    }

    /**
     * DeletePhotoCallback interface
     * Identify when a deletion is successful
     */
    interface DeleteChildCallback{
        void onChildDeleted();
        void onChildDeleteFailure();
    }

    /**
     * getPhotos loads all Photos, calls either success or failure callback
     * @param callback - Callback function
     */
    void getChildren(@NonNull LoadChildrenCallback callback);

    /**
     * getPhoto - Get a single Photo
     * @param PhotoId - String of the current PhotoID to be retrieved
     * @param callback - Callback function
     */
    void getChild(@NonNull Integer ChildId, @NonNull GetChildCallback callback);

    /**
     * SavePhoto saves a CommentedPhoto to the database
     * @param photo - Photo to be updated
     */
    void saveChild(@NonNull final Children child);

    /**
     * CreatePhoto adds a CommentedPhoto to the database
     * @param photo - Photo to be added
     * @param callback - Callback function after thread completion
     */
    void createChild(@NonNull Children child, @NonNull CreateChildCallback callback);


    /**
     * deletePhoto deletes a CommentedPhoto from the database
     * @param id
     */
    void deleteChild(@NonNull Integer id, @NonNull DeleteChildCallback callback);

    // PARENT DATA SOURCE

    /**
     * LoadMessagesCallback interface
     * Example of how to implement callback functions depending on the result of functions in interfaces
     * Currently, onDataNotAvailable is not implemented
     */
    interface LoadParentsCallback {

        void onParentsLoaded(List<Parents> parents);
        void onDataNotAvailable();
    }

    /**
     * GetPhotoCallback interface
     */
    interface GetParentCallback {
        void onParentLoaded(Parents parents);
        void onDataNotAvailable();
    }

    /**
     * CreatePhotoCallback interface
     * Return the id in createSuccessful
     */
    interface CreateParentCallback{
        void onParentCreated(int id);
        void onParentCreateFail();
    }

    /**
     * DeletePhotoCallback interface
     * Identify when a deletion is successful
     */
    interface DeleteParentCallback{
        void onParentDeleted();
        void onParentDeleteFailure();
    }

    /**
     * getPhotos loads all Photos, calls either success or failure callback
     * @param callback - Callback function
     */
    void getParents(@NonNull LoadParentsCallback callback);

    /**
     * getPhoto - Get a single Photo
     * @param PhotoId - String of the current PhotoID to be retrieved
     * @param callback - Callback function
     */
    void getParent(@NonNull Integer ParentId, @NonNull GetParentCallback callback);

    /**
     * SavePhoto saves a CommentedPhoto to the database
     * @param photo - Photo to be updated
     */
    void saveParent(@NonNull final Parents parent);

    /**
     * CreatePhoto adds a CommentedPhoto to the database
     * @param photo - Photo to be added
     * @param callback - Callback function after thread completion
     */
    void createParent(@NonNull Parents parent, @NonNull CreateParentCallback callback);


    /**
     * deletePhoto deletes a CommentedPhoto from the database
     * @param id
     */
    void deleteParent(@NonNull Integer id, @NonNull DeleteParentCallback callback);

    // VEHICLE DATA SOURCE

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

    // REVIEW PICKUP DATA SOURCE

    /**
     * LoadMessagesCallback interface
     * Example of how to implement callback functions depending on the result of functions in interfaces
     * Currently, onDataNotAvailable is not implemented
     */
    interface LoadReviewPickupsCallback {

        void onReviewPickupsLoaded(List<ReviewPickups> pickups);
        void onDataNotAvailable();
    }

    /**
     * GetPhotoCallback interface
     */
    interface GetReviewPickupsCallback {
        void onReviewPickupsLoaded(ReviewPickups pickups);
        void onDataNotAvailable();
    }

    /**
     * CreatePhotoCallback interface
     * Return the id in createSuccessful
     */
    interface CreateReviewPickupCallback{
        void onReviewPickupCreated(int id);
        void onReviewPickupCreateFail();
    }

    /**
     * DeletePhotoCallback interface
     * Identify when a deletion is successful
     */
    interface DeleteReviewPickupCallback{
        void onReviewPickupDeleted();
        void onReviewPickupDeleteFailure();
    }

    /**
     * getPhotos loads all Photos, calls either success or failure callback
     * @param callback - Callback function
     */
    void getReviewPickups(@NonNull LoadReviewPickupsCallback callback);

    /**
     * getPhoto - Get a single Photo
     * @param PhotoId - String of the current PhotoID to be retrieved
     * @param callback - Callback function
     */
    void getReviewPickup(@NonNull Integer PickupId, @NonNull GetReviewPickupsCallback callback);

    /**
     * SavePhoto saves a CommentedPhoto to the database
     * @param photo - Photo to be updated
     */
    void saveReviewPickup(@NonNull final ReviewPickups pickup);

    /**
     * CreatePhoto adds a CommentedPhoto to the database
     * @param photo - Photo to be added
     * @param callback - Callback function after thread completion
     */
    void createReviewPickup(@NonNull ReviewPickups pickup, @NonNull CreateReviewPickupCallback callback);


    /**
     * deletePhoto deletes a CommentedPhoto from the database
     * @param id
     */
    void deleteReviewPickup(@NonNull Integer id, @NonNull DeleteReviewPickupCallback callback);
}


