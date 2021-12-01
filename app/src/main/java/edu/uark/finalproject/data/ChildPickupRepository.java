package edu.uark.finalproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import util.AppExecutors;

public class ChildPickupRepository implements ChildPickupDataSource {

    public static volatile ChildPickupRepository INSTANCE;
    ChildPickupDao childPickupDao;
    AppExecutors mAppExecutors;
    private Context mContext;



    private ChildPickupRepository(AppExecutors executors, Context context){
        childPickupDao = ChildPickupDatabase.getInstance(context).getChildPickupDao();
        mAppExecutors = executors;
    }

    /**
     * public constructor - prevent creation of instance if one already exists
     * @param appExecutors
     * @param context
     * @return
     */
    public static ChildPickupRepository getInstance(@NonNull AppExecutors appExecutors, @NonNull Context context){
        if(INSTANCE == null){
            synchronized (ChildPickupRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new ChildPickupRepository(appExecutors, context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getChildren(@NonNull LoadChildrenCallback callback) {
        Log.d("REPOSITORY","Loading...");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
//                String[] projection = {
//                        CommentedPhoto.COMMENTEDPHOTO_ID,
//                        CommentedPhoto.COMMENTEDPHOTO_COMMENT,
//                        CommentedPhoto.COMMENTEDPHOTO_FNAME,
//                        CommentedPhoto.COMMENTEDPHOTO_LAT,
//                        CommentedPhoto.COMMENTEDPHOTO_LONG};
                //final Cursor c = mContext.getContentResolver().query(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), projection, null, null, null);
                final Cursor c = childPickupDao.findAllChildren();
                final List<Children> children = new ArrayList<Children>(0);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            while(c.moveToNext()) {
                                Children child = new Children();
                                child.setId(c.getInt(c.getColumnIndex(Children.CHILD_ID)));
                                //child.setAge(c.getInt(c.getColumnIndex(Children.CHILD_AGE)));
                                child.setAge(c.getString(c.getColumnIndex(Children.CHILD_AGE)));
                                //child.setGrade(c.getInt(c.getColumnIndex(Children.CHILD_GRADE)));
                                child.setGrade(c.getString(c.getColumnIndex(Children.CHILD_GRADE)));
                                child.setName(c.getString(c.getColumnIndex(Children.CHILD_NAME)));

                                children.add(child);
                            }
                            c.close();
                            callback.onChildrenLoaded(children);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getChild(@NonNull Integer ChildId, @NonNull GetChildCallback callback) {
        Log.d("REPOSITORY","GetChild");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                final Cursor c = childPickupDao.findAllChildren();
                final Children child = new Children();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            if(c.moveToFirst()){
                                child.setId(c.getInt(c.getColumnIndex(Children.CHILD_ID)));
                                //child.setAge(c.getInt(c.getColumnIndex(Children.CHILD_AGE)));
                                child.setAge(c.getString(c.getColumnIndex(Children.CHILD_AGE)));
                                //child.setGrade(c.getInt(c.getColumnIndex(Children.CHILD_GRADE)));
                                child.setGrade(c.getString(c.getColumnIndex(Children.CHILD_GRADE)));
                                child.setName(c.getString(c.getColumnIndex(Children.CHILD_NAME)));
                            }
                            c.close();
                            callback.onChildLoaded(child);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void saveChild(@NonNull Children child) {
        Log.d("REPOSITORY","SaveToDoItem");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                ContentValues myCV = new ContentValues();
                myCV.put(Children.CHILD_ID,child.getId());
                myCV.put(Children.CHILD_AGE,child.getAge());
                myCV.put(Children.CHILD_NAME,child.getName());
                myCV.put(Children.CHILD_GRADE,child.getGrade());
                final int numUpdated = childPickupDao.updateChild(child);
                //final int numUpdated = mContext.getContentResolver().update(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), myCV,null,null);
                Log.d("REPOSITORY","Update Messages updated " + String.valueOf(numUpdated) + " rows");
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void createChild(@NonNull Children child, @NonNull CreateChildCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long id = childPickupDao.insertChild(child);
                mAppExecutors.mainThread().execute(new Runnable(){
                    @Override
                    public void run() {
                        callback.onChildCreated((int)id);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void deleteChild(@NonNull Integer id, @NonNull DeleteChildCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int count = childPickupDao.deleteChild(id);
                    callback.onChildDeleted();
                }catch (Exception ex){
                    Log.e("REPOSITORY",ex.toString());
                    callback.onChildDeleteFailure();
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    // PARENTS REPOSITORY

    @Override
    public void getParents(@NonNull LoadParentsCallback callback) {
        Log.d("REPOSITORY","Loading...");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
//                String[] projection = {
//                        CommentedPhoto.COMMENTEDPHOTO_ID,
//                        CommentedPhoto.COMMENTEDPHOTO_COMMENT,
//                        CommentedPhoto.COMMENTEDPHOTO_FNAME,
//                        CommentedPhoto.COMMENTEDPHOTO_LAT,
//                        CommentedPhoto.COMMENTEDPHOTO_LONG};
                //final Cursor c = mContext.getContentResolver().query(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), projection, null, null, null);
                final Cursor c = childPickupDao.findAllParents();
                final List<Parents> parents = new ArrayList<Parents>(0);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            while(c.moveToNext()) {
                                Parents parent = new Parents();
                                parent.setId(c.getInt(c.getColumnIndex(Parents.PARENT_ID)));
                                parent.setPhone(c.getString(c.getColumnIndex(Parents.PARENT_PHONE)));
                                parent.setEmail(c.getString(c.getColumnIndex(Parents.PARENT_EMAIL)));
                                parent.setName(c.getString(c.getColumnIndex(Parents.PARENT_NAME)));

                                parents.add(parent);
                            }
                            c.close();
                            callback.onParentsLoaded(parents);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getParent(@NonNull Integer ParentId, @NonNull GetParentCallback callback) {
        Log.d("REPOSITORY","GetChild");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                final Cursor c = childPickupDao.findAllParents();
                final Parents parent = new Parents();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            if(c.moveToFirst()){
                                parent.setId(c.getInt(c.getColumnIndex(Parents.PARENT_ID)));
                                parent.setEmail(c.getString(c.getColumnIndex(Parents.PARENT_EMAIL)));
                                parent.setName(c.getString(c.getColumnIndex(Parents.PARENT_NAME)));
                                parent.setPhone(c.getString(c.getColumnIndex(Parents.PARENT_PHONE)));
                            }
                            c.close();
                            callback.onParentLoaded(parent);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void saveParent(@NonNull Parents parent) {
        Log.d("REPOSITORY","SaveToDoItem");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                ContentValues myCV = new ContentValues();
                myCV.put(Parents.PARENT_ID,parent.getId());
                myCV.put(Parents.PARENT_NAME,parent.getName());
                myCV.put(Parents.PARENT_EMAIL,parent.getEmail());
                myCV.put(Parents.PARENT_PHONE,parent.getPhone());
                final int numUpdated = childPickupDao.updateParent(parent);
                //final int numUpdated = mContext.getContentResolver().update(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), myCV,null,null);
                Log.d("REPOSITORY","Update Messages updated " + String.valueOf(numUpdated) + " rows");
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void createParent(@NonNull Parents parent, @NonNull CreateParentCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long id = childPickupDao.insertParent(parent);
                mAppExecutors.mainThread().execute(new Runnable(){
                    @Override
                    public void run() {
                        callback.onParentCreated((int)id);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void deleteParent(@NonNull Integer id, @NonNull DeleteParentCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int count = childPickupDao.deleteParent(id);
                    callback.onParentDeleted();
                }catch (Exception ex){
                    Log.e("REPOSITORY",ex.toString());
                    callback.onParentDeleteFailure();
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    // VEHICLE REPOSITORY

    @Override
    public void getVehicles(@NonNull LoadVehiclesCallback callback) {
        Log.d("REPOSITORY","Loading...");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
//                String[] projection = {
//                        CommentedPhoto.COMMENTEDPHOTO_ID,
//                        CommentedPhoto.COMMENTEDPHOTO_COMMENT,
//                        CommentedPhoto.COMMENTEDPHOTO_FNAME,
//                        CommentedPhoto.COMMENTEDPHOTO_LAT,
//                        CommentedPhoto.COMMENTEDPHOTO_LONG};
                //final Cursor c = mContext.getContentResolver().query(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), projection, null, null, null);
                final Cursor c = childPickupDao.findAllVehicles();
                final List<Vehicles> vehicles = new ArrayList<Vehicles>(0);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            while(c.moveToNext()) {
                                Vehicles vehicle = new Vehicles();
                                vehicle.setId(c.getInt(c.getColumnIndex(Vehicles.VEHICLE_ID)));
                                vehicle.setColor(c.getString(c.getColumnIndex(Vehicles.VEHICLE_COLOR)));
                                vehicle.setMake(c.getString(c.getColumnIndex(Vehicles.VEHICLE_MAKE)));
                                vehicle.setModel(c.getString(c.getColumnIndex(Vehicles.VEHICLE_MODEL)));

                                vehicles.add(vehicle);
                            }
                            c.close();
                            callback.onVehiclesLoaded(vehicles);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getVehicle(@NonNull Integer VehicleId, @NonNull GetVehiclesCallback callback) {
        Log.d("REPOSITORY","GetChild");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                final Cursor c = childPickupDao.findAllVehicles();
                final Vehicles vehicle = new Vehicles();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            if(c.moveToFirst()){
                                vehicle.setId(c.getInt(c.getColumnIndex(Vehicles.VEHICLE_ID)));
                                vehicle.setModel(c.getString(c.getColumnIndex(Vehicles.VEHICLE_MODEL)));
                                vehicle.setMake(c.getString(c.getColumnIndex(Vehicles.VEHICLE_MAKE)));
                                vehicle.setColor(c.getString(c.getColumnIndex(Vehicles.VEHICLE_COLOR)));
                            }
                            c.close();
                            callback.onVehiclesLoaded(vehicle);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void saveVehicle(@NonNull Vehicles vehicle) {
        Log.d("REPOSITORY","SaveToDoItem");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                ContentValues myCV = new ContentValues();
                myCV.put(Vehicles.VEHICLE_ID,vehicle.getId());
                myCV.put(Vehicles.VEHICLE_COLOR,vehicle.getColor());
                myCV.put(Vehicles.VEHICLE_MAKE,vehicle.getMake());
                myCV.put(Vehicles.VEHICLE_MODEL,vehicle.getModel());
                final int numUpdated = childPickupDao.updateVehicle(vehicle);
                //final int numUpdated = mContext.getContentResolver().update(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), myCV,null,null);
                Log.d("REPOSITORY","Update Messages updated " + String.valueOf(numUpdated) + " rows");
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void createVehicle(@NonNull Vehicles vehicle, @NonNull CreateVehicleCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long id = childPickupDao.insertVehicle(vehicle);
                mAppExecutors.mainThread().execute(new Runnable(){
                    @Override
                    public void run() {
                        callback.onVehicleCreated((int)id);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void deleteVehicle(@NonNull Integer id, @NonNull DeleteVehicleCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int count = childPickupDao.deleteVehicle(id);
                    callback.onVehicleDeleted();
                }catch (Exception ex){
                    Log.e("REPOSITORY",ex.toString());
                    callback.onVehicleDeleteFailure();
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    // REVIEW PICKUP REPOSITORY

    @Override
    public void getReviewPickups(@NonNull LoadReviewPickupsCallback callback) {
        Log.d("REPOSITORY","Loading...");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
//                String[] projection = {
//                        CommentedPhoto.COMMENTEDPHOTO_ID,
//                        CommentedPhoto.COMMENTEDPHOTO_COMMENT,
//                        CommentedPhoto.COMMENTEDPHOTO_FNAME,
//                        CommentedPhoto.COMMENTEDPHOTO_LAT,
//                        CommentedPhoto.COMMENTEDPHOTO_LONG};
                //final Cursor c = mContext.getContentResolver().query(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), projection, null, null, null);
                final Cursor c = childPickupDao.findAllPickups();
                final List<ReviewPickups> pickups = new ArrayList<ReviewPickups>(0);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            while(c.moveToNext()) {
                                ReviewPickups pickup = new ReviewPickups();
                                pickup.setId(c.getInt(c.getColumnIndex(ReviewPickups.PICKUP_ID)));
                                pickup.setDate(c.getString(c.getColumnIndex(ReviewPickups.PICKUP_DATE)));
                                pickup.setTime(c.getString(c.getColumnIndex(ReviewPickups.PICKUP_TIME)));

                                pickups.add(pickup);
                            }
                            c.close();
                            callback.onReviewPickupsLoaded(pickups);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getReviewPickup(@NonNull Integer PickupId, @NonNull GetReviewPickupsCallback callback) {
        Log.d("REPOSITORY","GetPickup");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                final Cursor c = childPickupDao.findAllPickups();
                final ReviewPickups pickup = new ReviewPickups();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if(c == null){
                            callback.onDataNotAvailable();
                        } else{
                            if(c.getCount() == 0){
                                callback.onDataNotAvailable();
                            }
                            if(c.moveToFirst()){
                                pickup.setId(c.getInt(c.getColumnIndex(ReviewPickups.PICKUP_ID)));
                                pickup.setDate(c.getString(c.getColumnIndex(ReviewPickups.PICKUP_DATE)));
                                pickup.setTime(c.getString(c.getColumnIndex(ReviewPickups.PICKUP_TIME)));
                            }
                            c.close();
                            callback.onReviewPickupsLoaded(pickup);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void saveReviewPickup(@NonNull ReviewPickups pickup) {
        Log.d("REPOSITORY","SaveToDoItem");
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                ContentValues myCV = new ContentValues();
                myCV.put(ReviewPickups.PICKUP_ID,pickup.getId());
                myCV.put(ReviewPickups.PICKUP_DATE,pickup.getDate());
                myCV.put(ReviewPickups.PICKUP_TIME,pickup.getTime());
                final int numUpdated = childPickupDao.updatePickup(pickup);
                //final int numUpdated = mContext.getContentResolver().update(Uri.parse("content://" + MessageProvider.AUTHORITY + "/" + MessageProvider.MESSAGE_TABLE_NAME), myCV,null,null);
                Log.d("REPOSITORY","Update Messages updated " + String.valueOf(numUpdated) + " rows");
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void createReviewPickup(@NonNull ReviewPickups pickup, @NonNull CreateReviewPickupCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                long id = childPickupDao.insertPickup(pickup);
                mAppExecutors.mainThread().execute(new Runnable(){
                    @Override
                    public void run() {
                        callback.onReviewPickupCreated((int)id);
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);

    }

    @Override
    public void deleteReviewPickup(@NonNull Integer id, @NonNull DeleteReviewPickupCallback callback) {
        Log.d("REPOSITORY","Deleting...");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    int count = childPickupDao.deletePickup(id);
                    callback.onReviewPickupDeleted();
                }catch (Exception ex){
                    Log.e("REPOSITORY",ex.toString());
                    callback.onReviewPickupDeleteFailure();
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
