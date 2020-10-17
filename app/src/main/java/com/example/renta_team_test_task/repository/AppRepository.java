package com.example.renta_team_test_task.repository;


import android.util.Log;

import com.example.renta_team_test_task.pojo.JUser;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class AppRepository {
    private static final String TAG = "AppRepository";

    private UsersRemoteRepo remoteRepo;
    private UsersLocalRepo localRepo;

    @Inject
    public AppRepository(UsersRemoteRepo remoteRepo, UsersLocalRepo localRepo) {
        this.remoteRepo = remoteRepo;
        this.localRepo = localRepo;
    }

    public Observable<List<JUser>> getUsers() {
        return Observable.mergeDelayError(remoteRepo.getResponse().doOnNext(new Consumer<List<JUser>>() {
            @Override
            public void accept(List<JUser> list) throws Exception {
                localRepo.insertUsers(list);
            }
        }).subscribeOn(Schedulers.io()), localRepo.getUsers().subscribeOn(Schedulers.io()));
    }

    public Observable<List<JUser>> getLocalUsers() {
        return localRepo.getUsers();
    }

    public Maybe<JUser> getUserById(String id) {
        return localRepo.getUserById(id);
    }

//    public MutableLiveData<List<JUser>> getUsers() {
//        MutableLiveData<List<JUser>> mutableLiveData = new MutableLiveData<>();
//        retrofitService.create(ReqResApi.class).getUsers().enqueue(new Callback<JResponse>() {
//            @Override
//            public void onResponse(Call<JResponse> call, Response<JResponse> response) {
//                if (response.isSuccessful()) {
//                    mutableLiveData.setValue(response.body().getJUserList());
//                    appDataBase.getUserDao().insert(response.body().getJUserList());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JResponse> call, Throwable t) {
//                mutableLiveData.setValue(null);
//            }
//        });
//        return mutableLiveData;
//    }
//
//    public LiveData<JUser> getUserById(int id) {
//        return appDataBase.getUserDao().getUserById(id);
//    }
//
//    public Observable<JResponse> updateUsers() {
//        Observable<JResponse> observable = retrofitService.create(ReqResApi.class).getUsers();
//        observable.create(new ObservableOnSubscribe<List<JUser>>() {
//            @Override
//            public void subscribe(ObservableEmitter<List<JUser>> emitter) throws Exception {
//                emitter.onNext();
//            }
//        };
//        return observable;
//    }
//
//    private void putToDatabase() {
//
//    }
}
