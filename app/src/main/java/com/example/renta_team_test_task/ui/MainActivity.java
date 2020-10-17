package com.example.renta_team_test_task.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.validators.NetworkValidator;
import com.example.renta_team_test_task.ui.pages.AboutFragment;
import com.example.renta_team_test_task.ui.pages.ContainerUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private Fragment currentFragment;
    private final Fragment usersFragment = ContainerUserFragment.newInstance();
    private final Fragment aboutFragment = AboutFragment.newInstance();

    private final String USERS_FR_TAG = "users_tag";
    private final String ABOUT_FR_TAG = "about_tag";

    private FragmentManager frManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        setFragments();

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.list_user_dest:
                        fragment = usersFragment;
                        break;
                    case R.id.about_dest:
                        fragment = aboutFragment;
                        break;
                    default:
                        return false;
                }
                frManager.beginTransaction().hide(currentFragment).show(fragment).commit();
                currentFragment = fragment;
                return true;
            }
        };


    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_nav);
    }

    private void setFragments() {
        frManager = getSupportFragmentManager();
        currentFragment = usersFragment;
        frManager.beginTransaction()
                .add(R.id.frame_container, usersFragment, USERS_FR_TAG)
                .commit();
        frManager.beginTransaction()
                .add(R.id.frame_container, aboutFragment, ABOUT_FR_TAG)
                .hide(aboutFragment)
                .commit();
    }
}