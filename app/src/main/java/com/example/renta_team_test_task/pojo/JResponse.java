package com.example.renta_team_test_task.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JResponse {
    @SerializedName("data")
    private List<JUser> JUserList;

    public List<JUser> getJUserList() {
        return JUserList;
    }

    public void setJUserList(List<JUser> JUserList) {
        this.JUserList = JUserList;
    }
}
