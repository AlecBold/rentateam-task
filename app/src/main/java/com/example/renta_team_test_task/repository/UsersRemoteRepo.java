package com.example.renta_team_test_task.repository;

import com.example.renta_team_test_task.network.ReqResApi;
import com.example.renta_team_test_task.pojo.JResponse;
import com.example.renta_team_test_task.pojo.JUser;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;

@Singleton
public class UsersRemoteRepo {
    private Retrofit retrofitService;

    @Inject
    public UsersRemoteRepo(Retrofit retrofitService) {
        this.retrofitService = retrofitService;
    }

    public Observable<List<JUser>> getResponse() {
        return retrofitService.create(ReqResApi.class).getUsers().map(new Function<JResponse, List<JUser>>() {
            @Override
            public List<JUser> apply(JResponse jResponse) throws Exception {
                return jResponse.getJUserList();
            }
        });
    }
}
