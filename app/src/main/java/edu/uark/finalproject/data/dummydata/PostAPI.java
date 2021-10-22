package edu.uark.finalproject.data.dummydata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostAPI {
    @GET("posts/")
    Call<List<Post>> loadPosts();
}
