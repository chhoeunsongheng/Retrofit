package com.example.acer.retrofit;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.w3c.dom.Entity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ACER on 11/26/2017.
 */

public interface APIServices {

    @GET("posts/{id}")
    Call<Person> findALlposts(@Path("id") int postid);
}
