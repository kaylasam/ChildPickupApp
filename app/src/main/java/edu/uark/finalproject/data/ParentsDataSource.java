package edu.uark.finalproject.data;

import java.util.List;

import androidx.annotation.NonNull;

public interface ParentsDataSource {
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

}


