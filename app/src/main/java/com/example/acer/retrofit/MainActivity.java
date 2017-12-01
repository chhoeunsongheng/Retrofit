package com.example.acer.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    APIServices apiServices;
    Button btnload;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnload=findViewById(R.id.btnload);
        tv=findViewById(R.id.textload);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                APIServices apiService=RestClient.createServie(APIServices.class);
//                Call<Person> call=apiService.findALlposts(100);
//                call.enqueue(new Callback<Person>() {
//                    @Override
//                    public void onResponse(Call<Person> call, Response<Person> response) {
//                        Log.e("ooooo",response.body().toString());
//                        tv.setText(response.body().toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<Person> call, Throwable t) {
//
//                    }
//                });
                Observable<Person> observable=apiService.findALlposts(1);
                observable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeWith(new Observer<Person>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    Log.e("ooooo","sucess");
                                }
                                @Override
                                public void onNext(Person value) {
                                    Log.e("ooooo",""+value.toString());
                                    tv.setText(value.toString());
                                }
                                @Override
                                public void onError(Throwable e) {
                                    Log.e("ooooo","Error");
                                }
                                @Override
                                public void onComplete() {
                                    Log.e("ooooo","completed");
                                    Log.e("ooooo",Person.class.toString());
                                }
                            });
            }
        });

    }


}
