package com.example.renta_team_test_task.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.renta_team_test_task.pojo.JUser;

import javax.inject.Singleton;

@Singleton
@Database(entities = {JUser.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
