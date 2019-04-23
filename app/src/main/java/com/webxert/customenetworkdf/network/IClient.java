package com.webxert.customenetworkdf.network;

import com.webxert.customenetworkdf.data.Employees;
import com.webxert.customenetworkdf.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hp on 4/22/2019.
 */

public interface IClient {
    @GET("employees")
    Call<List<Employees>> getUsers();
}
