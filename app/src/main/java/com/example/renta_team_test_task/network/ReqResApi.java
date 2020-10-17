package com.example.renta_team_test_task.network;

import com.example.renta_team_test_task.pojo.JResponse;
import com.example.renta_team_test_task.utils.Variables;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ReqResApi {

    @GET(Variables.REQUEST_USERS)
    Observable<JResponse> getUsers();
}
