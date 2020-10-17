package com.example.renta_team_test_task;

import android.app.Application;

import com.example.renta_team_test_task.di.AppComponent;
import com.example.renta_team_test_task.di.DaggerAppComponent;

public class MyApplication extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.factory().create(getApplicationContext());
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
