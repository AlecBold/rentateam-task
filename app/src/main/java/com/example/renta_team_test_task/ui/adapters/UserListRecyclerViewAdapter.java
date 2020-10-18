package com.example.renta_team_test_task.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.pojo.JUser;
import com.example.renta_team_test_task.ui.MainActivity;
import com.example.renta_team_test_task.ui.pages.UserFragment;
import com.example.renta_team_test_task.utils.AppConstants;

import java.util.List;

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.UserHolder> {
    private Context context;

    private List<JUser> jUsers;

    public UserListRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_list, parent, false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        JUser jUser = jUsers.get(position);
        holder.name.setText(jUser.getName());
        holder.lastName.setText(jUser.getLastName());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).pushFragment(AppConstants.TAB_USER_LIST, UserFragment.newInstance(jUser.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (jUsers != null) {
            return jUsers.size();
        }
        return 0;
    }

    public void setJUserList(List<JUser> jUsers) {
        this.jUsers = jUsers;
        notifyDataSetChanged();
    }

    class UserHolder extends RecyclerView.ViewHolder {

        private CardView parent;
        private TextView name;
        private TextView lastName;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            name = itemView.findViewById(R.id.itemUser_name);
            lastName = itemView.findViewById(R.id.itemUser_lastName);
        }
    }
}
