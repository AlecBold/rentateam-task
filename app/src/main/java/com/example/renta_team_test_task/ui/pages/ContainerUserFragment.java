package com.example.renta_team_test_task.ui.pages;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.renta_team_test_task.MyApplication;
import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.di.componets.UsersSubComponent;

public class ContainerUserFragment extends Fragment {
    public static final String TAG = "UsersFragment";

    UsersSubComponent usersSubComponent;

    public ContainerUserFragment() {
    }

    public static ContainerUserFragment newInstance() {
        ContainerUserFragment fragment = new ContainerUserFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        usersSubComponent = ((MyApplication) requireActivity().getApplication()).getAppComponent().usersSubComponent().create();
        usersSubComponent.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_container_user, container, false);

        return view;
    }
}