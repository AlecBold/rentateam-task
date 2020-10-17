package com.example.renta_team_test_task.repository;

import com.example.renta_team_test_task.data.AppDataBase;
import com.example.renta_team_test_task.pojo.JUser;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;

@Singleton
public class UsersLocalRepo {

    private AppDataBase appDataBase;

    @Inject
    public UsersLocalRepo(AppDataBase appDataBase) {
        this.appDataBase = appDataBase;
    }

    public Observable<List<JUser>> getUsers() {
        return Observable.fromCallable(new Callable<List<JUser>>() {
            @Override
            public List<JUser> call() throws Exception {
                return appDataBase.getUserDao().getUsers();
            }
        });
    }

    public Maybe<JUser> getUserById(String id) {
        return appDataBase.getUserDao().getUserById(id);
    }

    public void insertUsers(List<JUser> list) {
        appDataBase.getUserDao().insert(list);
    }
}
