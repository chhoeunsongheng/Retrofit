package com.example.acer.retrofit;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.Executor;

import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ACER on 11/26/2017.
 */

public class RestClient {

    public static OkHttpClient.Builder httpclient=new OkHttpClient.Builder();

    private static Retrofit.Builder builder=new Retrofit.Builder()
                           .baseUrl("https://jsonplaceholder.typicode.com/")
                           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                           .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createServie(Class<S> serviceClass){
        httpclient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original=chain.request();
                Request.Builder requestBuilder=original.newBuilder()
                        .header("Accept","application/json");
                Request request=requestBuilder.build();
                return chain.proceed(request);
            }

        });
        OkHttpClient client=httpclient.build();
        Retrofit retrofit=builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
