package com.example.acer.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.w3c.dom.Entity;
import org.w3c.dom.Text;

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
                Call<Person> call=apiService.findALlposts(100);
                call.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        Log.e("ooooo",response.body().toString());
                        tv.setText(response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
            }
        });

    }


}
