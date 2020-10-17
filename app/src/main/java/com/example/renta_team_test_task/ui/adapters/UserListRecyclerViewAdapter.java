package com.example.renta_team_test_task.ui.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.pojo.JUser;
import com.example.renta_team_test_task.ui.pages.UserFragment;

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
                Bundle bundle = new Bundle();
                bundle.putInt(UserFragment.KEY_ID, jUser.getId());
                Navigation.findNavController(view).navigate(R.id.action_list_user_dest_to_userFragment, bundle);
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
