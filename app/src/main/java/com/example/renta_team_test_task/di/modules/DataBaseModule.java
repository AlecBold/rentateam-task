package com.example.renta_team_test_task.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.example.renta_team_test_task.data.AppDataBase;
import com.example.renta_team_test_task.utils.Variables;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataBaseModule {

    @Singleton
    @Provides
    public AppDataBase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, Variables.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
}
