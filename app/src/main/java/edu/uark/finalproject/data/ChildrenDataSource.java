package edu.uark.finalproject.data;

import java.util.List;

import androidx.annotation.NonNull;

public interface ChildrenDataSource {
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

}


