package edu.uark.finalproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import util.AppExecutors;

public class VehiclesRepository implements ChildrenDataSource {

    public static volatile VehiclesRepository INSTANCE;
    ChildrenDao childrenDao;
    AppExecutors mAppExecutors;
    private Context mContext;



    private VehiclesRepository(AppExecutors executors, Context context){
        childrenDao = ChildrenDatabase.getInstance(context).getChildrenDao();
        mAppExecutors = executors;
    }

    /**
     * public constructor - prevent creation of instance if one already exists
     * @param appExecutors
     * @param context
     * @return
     */
    public static VehiclesRepository getInstance(@NonNull AppExecutors appExecutors, @NonNull Context context){
        if(INSTANCE == null){
            synchronized (VehiclesRepository.class){
                if(INSTANCE == null){
                    INSTANCE = new VehiclesRepository(appExecutors, context);
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
                final Cursor c = childrenDao.findAll();
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
                                child.setAge(c.getInt(c.getColumnIndex(Children.CHILD_AGE)));
                                child.setGrade(c.getInt(c.getColumnIndex(Children.CHILD_GRADE)));
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
                final Cursor c = childrenDao.findAll();
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
                                child.setAge(c.getInt(c.getColumnIndex(Children.CHILD_AGE)));
                                child.setGrade(c.getInt(c.getColumnIndex(Children.CHILD_GRADE)));
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
                final int numUpdated = childrenDao.update(child);
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
                long id = childrenDao.insert(child);
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
                    int count = childrenDao.delete(id);
                    callback.onChildDeleted();
                }catch (Exception ex){
                    Log.e("REPOSITORY",ex.toString());
                    callback.onChildDeleteFailure();
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
