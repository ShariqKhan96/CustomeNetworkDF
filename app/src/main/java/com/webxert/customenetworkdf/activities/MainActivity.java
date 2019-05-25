package com.webxert.customenetworkdf.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.webxert.customenetworkdf.R;
import com.webxert.customenetworkdf.adapters.UserAdapter;
import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;
import com.webxert.customenetworkdf.interfaces.LoaderListener;
import com.webxert.customenetworkdf.utils.Utils;
import com.webxert.customenetworkdf.viewmodels.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderListener {

    RecyclerView userRecyclerView;
    UserAdapter adapter;
    MainActivityViewModel mainActivityViewModel;
    KProgressHUD progressDialog;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = Utils.progressDialog(this, "", "Please Wait");
        initViews();
        setUpRecyclerView();
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getLiveUsers().observe(this, new Observer<List<Employees>>() {
            @Override
            public void onChanged(@Nullable List<Employees> users) {
                adapter = new UserAdapter(mainActivityViewModel.getLiveUsers().getValue(), MainActivity.this);
                userRecyclerView.setAdapter(adapter);
            }
        });
        mainActivityViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean)
                    showProgress();
                else hideProgress();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addEmployee(new Employees("s", "s", "s", "s", "s"));
            }
        });

    }

    private void hideProgress() {
        progressDialog.dismiss();
    }

    private void showProgress() {
        progressDialog.show();
    }

    private void setUpRecyclerView() {

    }

    private void initViews() {

        fab = findViewById(R.id.fab);
        userRecyclerView = findViewById(R.id.recyclerview);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void onCallStart() {
        progressDialog.show();

    }

    @Override
    public void onCallComplete() {
        progressDialog.dismiss();
//        userRecyclerView.smoothScrollToPosition(mainActivityViewModel.getLiveUsers().getValue().size() - 1);
    }
}
