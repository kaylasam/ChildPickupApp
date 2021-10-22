package edu.uark.finalproject.data.dummydata;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import edu.uark.finalproject.data.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class DummyDataGenerator {
    static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private List<User> users;
    private List<Post> posts;


    public void generateDummyData(DummyDataGeneratorCallback callback) {
        makeUserCall(callback);
    }

    public void makeUserCall(DummyDataGeneratorCallback callback){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<List<User>> call2 = userAPI.loadUsers();
        call2.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()) {
                    users = new ArrayList<User>(response.body());
                    makePostCall(callback);
                }else{
                    Log.e("DummyDataGenerator","Unable to make call");
                    callback.onDataNotCreated();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("DummyDataGenerator","Unable to make call");
                callback.onDataNotCreated();
            }
        });
    }

    public void makePostCall(DummyDataGeneratorCallback callback){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PostAPI postAPI = retrofit.create(PostAPI.class);
        Call<List<Post>> call = postAPI.loadPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                PostsResponse(response,callback);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("DummyDataGenerator","Call Failed");
                callback.onDataNotCreated();
            }
        });

    }

    public void PostsResponse(Response<List<Post>> response, DummyDataGeneratorCallback callback){
        if(response.isSuccessful()) {
            posts = new ArrayList<Post>(response.body());
            List<Message> messagesList = new ArrayList<>();
            for (int i = 0; i< posts.size();i++){
                Post p = posts.get(i);
                Message m = p.PostToMessage(users.get(p.getUserId()-1));
                messagesList.add(m);
            }
            callback.dummyDataCreated(messagesList);

        }else{
            Log.e("DummyDataGenerator","Can't create data");
            callback.onDataNotCreated();
        }
    }
}
