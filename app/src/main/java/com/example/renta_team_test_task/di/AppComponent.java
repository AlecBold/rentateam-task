package com.example.renta_team_test_task.di;

import android.content.Context;

import com.example.renta_team_test_task.di.componets.UsersSubComponent;
import com.example.renta_team_test_task.di.modules.DataBaseModule;
import com.example.renta_team_test_task.di.modules.NetworkModule;
import com.example.renta_team_test_task.di.modules.SubComponentsModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, DataBaseModule.class, SubComponentsModule.class})
public interface AppComponent {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context);
    }

    UsersSubComponent.Factory usersSubComponent();
}
