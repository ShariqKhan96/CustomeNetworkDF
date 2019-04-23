package com.webxert.customenetworkdf.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.webxert.customenetworkdf.R;
import com.webxert.customenetworkdf.adapters.UserAdapter;
import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;
import com.webxert.customenetworkdf.interfaces.LoaderListener;
import com.webxert.customenetworkdf.viewmodels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderListener {

    RecyclerView userRecyclerView;
    UserAdapter adapter;
    MainActivityViewModel mainActivityViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(this);

        mainActivityViewModel.getLiveUsers().observe(this, new Observer<List<Employees>>() {
            @Override
            public void onChanged(@Nullable List<Employees> users) {

                getSupportActionBar().setTitle(users.size() + "");

                Log.e(MainActivity.class.getSimpleName(), users.toString());

            }
        });
      //  initViews();

    }

    private void initViews() {
        userRecyclerView = findViewById(R.id.recyclerview);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(mainActivityViewModel.getLiveUsers().getValue(), MainActivity.this);
        userRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCallStart() {
        progressDialog.show();

    }

    @Override
    public void onCallComplete() {
        progressDialog.dismiss();
    }
}
