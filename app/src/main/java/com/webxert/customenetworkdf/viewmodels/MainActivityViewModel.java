package com.webxert.customenetworkdf.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;
import com.webxert.customenetworkdf.interfaces.LoaderListener;
import com.webxert.customenetworkdf.repositories.DataProvider;

import java.util.List;

/**
 * Created by hp on 4/22/2019.
 */

public class MainActivityViewModel extends ViewModel {
    MutableLiveData<List<Employees>> liveUsers;
    DataProvider repository;


    public void init(LoaderListener loaderListener) {
        Log.e(MainActivityViewModel.class.getSimpleName(), "Before Calling..");
        if (liveUsers != null) {
            return;
        }
        Log.e(MainActivityViewModel.class.getSimpleName(), "Calling..");
        repository = DataProvider.getInstance();
        DataProvider.loaderListener = loaderListener;
        liveUsers = repository.endUsers();
    }


    public LiveData<List<Employees>> getLiveUsers() {
        return liveUsers;
    }


}
