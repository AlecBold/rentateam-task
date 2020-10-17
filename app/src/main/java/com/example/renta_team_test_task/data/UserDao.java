package com.example.renta_team_test_task.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.renta_team_test_task.pojo.JUser;

import java.util.List;

import io.reactivex.Maybe;


@Dao
public interface UserDao {
    @Query("SELECT * FROM JUser")
    List<JUser> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<JUser> list);

    @Query("SELECT * FROM JUser WHERE id = :id")
    Maybe<JUser> getUserById(String id);
}
