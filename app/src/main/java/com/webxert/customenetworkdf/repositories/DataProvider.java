package com.webxert.customenetworkdf.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;
import com.webxert.customenetworkdf.interfaces.LoaderListener;
import com.webxert.customenetworkdf.network.IClient;
import com.webxert.customenetworkdf.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by hp on 4/22/2019.
 */
/*
 * Singleton Pattern
 * */
public class DataProvider {

    public static DataProvider instance;
    List<Employees> users = new ArrayList<>();

    // public static Context context = null;
    public static LoaderListener loaderListener;

    public static DataProvider getInstance() {
        //  DataProvider.context = context;
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public void endUsers(final MutableLiveData<List<Employees>> employees, final LoaderListener loaderListener) {

        loaderListener.onCallStart();
        Retrofit retrofit = RetrofitBuilder.getRetrofit();

        IClient client = retrofit.create(IClient.class);

        client.getUsers().enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                loaderListener.onCallComplete();

                Log.e("users", response.body().size() + "");
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        employees.setValue(response.body());
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {
                loaderListener.onCallComplete();
                Log.e("onFailure :", t.getMessage());

            }
        });


    }

    public void addUser(final Employees employees, final MutableLiveData<List<Employees>> liveUsers) {
        // loaderListener.onCallStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                List<Employees> temp = new ArrayList<>(liveUsers.getValue());
                Log.e("BEFORE: ", liveUsers.getValue().size() + "");
                temp.add(employees);
                liveUsers.setValue(temp);
                // liveUsers.postValue(temp);
                Log.e("AFTER : ", liveUsers.getValue().size() + "");
                //    loaderListener.onCallComplete();


            }
        }, 2000);
    }
}
