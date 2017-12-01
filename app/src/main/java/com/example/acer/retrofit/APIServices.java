package com.example.acer.retrofit;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.w3c.dom.Entity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface APIServices {

    @GET("posts/{id}")
    Observable<Person> findALlposts(@Path("id") int postid);
}
