package com.webxert.customenetworkdf.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
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
    MutableLiveData<List<Employees>> liveUsers = new MutableLiveData<>();
    // public static Context context = null;
    public static LoaderListener loaderListener;

    public static DataProvider getInstance() {
        //  DataProvider.context = context;
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public MutableLiveData<List<Employees>> endUsers() {
        loaderListener.onCallStart();
        Retrofit retrofit = RetrofitBuilder.getRetrofit();
        IClient client = retrofit.create(IClient.class);

        client.getUsers().enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                loaderListener.onCallComplete();

                Log.e("users", response.body().size()+"");
                if (response.code() == 200) {
                    if (response.isSuccessful()) {
                        users.addAll(response.body());
                    }
                    liveUsers.postValue(users);
                    //liveUsers.setValue(users);
                    //liveUsers.postValue(users);
                }

            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {
                loaderListener.onCallComplete();
                Log.e("onFailure :", t.getMessage());

            }
        });

        return liveUsers;
    }

}
