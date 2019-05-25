package com.webxert.customenetworkdf.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.util.Log;

import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;
import com.webxert.customenetworkdf.interfaces.LoaderListener;
import com.webxert.customenetworkdf.repositories.DataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 4/22/2019.
 */

interface MyLoaderListener {
    void onLoadStart();

    void onLoadFinish();
}

public class MainActivityViewModel extends AndroidViewModel implements LoaderListener {
    MutableLiveData<List<Employees>> liveUsers = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    DataProvider repository;
    List<Employees> employees = new ArrayList<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        repository = DataProvider.getInstance();
        repository.endUsers(liveUsers, this);
    }


    public LiveData<List<Employees>> getLiveUsers() {
        return liveUsers;
    }


    public void addEmployee(Employees employees) {
        repository.addUser(employees, liveUsers);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Override
    public void onCallStart() {
        isLoading.setValue(true);
    }

    @Override
    public void onCallComplete() {
        isLoading.setValue(false);
    }
}
