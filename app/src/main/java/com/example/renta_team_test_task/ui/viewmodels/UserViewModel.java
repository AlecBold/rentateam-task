package com.example.renta_team_test_task.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.renta_team_test_task.di.scopes.ActivityScope;
import com.example.renta_team_test_task.pojo.JUser;
import com.example.renta_team_test_task.repository.AppRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

@ActivityScope
public class UserViewModel extends ViewModel {
    private Observable<List<JUser>> observable;
    private AppRepository repository;

    @Inject
    public UserViewModel(AppRepository repository) {
        this.repository = repository;
    }

    public Observable<List<JUser>> getUsers() {
        if (observable == null) {
            observable = repository.getUsers();
        }
        return observable;
    }

    public Observable<List<JUser>> getLocalUsers() {
        return repository.getLocalUsers();
    }

    public Observable<List<JUser>> updateUsers() {
        observable = repository.getUsers();
        return observable;
    }

    public Maybe<JUser> getUserById(String id) {
        return repository.getUserById(id);
    }
}
