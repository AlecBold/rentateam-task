package com.example.renta_team_test_task.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.ui.pages.ListUserFragment;
import com.example.renta_team_test_task.ui.pages.AboutFragment;
import com.example.renta_team_test_task.utils.AppConstants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private HashMap<String, Stack<Fragment>> mStacks;

    private String currentTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        mStacks = new HashMap<>();
        mStacks.put(AppConstants.TAB_USER_LIST, new Stack<>());
        mStacks.put(AppConstants.TAB_ABOUT, new Stack<>());

        currentTab = AppConstants.TAB_USER_LIST;
        bottomNavigationView.setSelectedItemId(R.id.list_user_dest);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.list_user_dest:
                    selectedTab(AppConstants.TAB_USER_LIST);
                    return true;
                case R.id.about_dest:
                    selectedTab(AppConstants.TAB_ABOUT);
                    return true;
            }
            return false;
        }
    };

    private void selectedTab(String tab) {
        // First click on tab
        if (mStacks.get(tab).size() == 0) {
            switch (tab) {
                case AppConstants.TAB_USER_LIST:
                    startTabFragment(tab, ListUserFragment.newInstance());
                    break;
                case AppConstants.TAB_ABOUT:
                    startTabFragment(tab, AboutFragment.newInstance());
                    break;
            }
        } else if (tab.equals(currentTab) && mStacks.get(tab).size() > 1) {
            // If being on the current tab, again clicks in the bottom navigation on the current tab
            // And if stack more than 1
            // Pop on back fragment
            popFragment();
        } else {
            // switch tab
            switchFragments(currentTab, tab);
        }
        currentTab = tab;
    }

    private void startTabFragment(String tab, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!tab.equals(currentTab)) {
            if (mStacks.get(currentTab).size() != 0) {
                transaction.hide(mStacks.get(currentTab).lastElement());
            }
        }
        transaction.add(R.id.frame_container, fragment).commit();
        mStacks.get(tab).push(fragment);
    }

    public void pushFragment(String tab, Fragment fragment) {
        Fragment currentFragment = mStacks.get(tab).lastElement();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, fragment)
                .hide(currentFragment)
                .commit();
        mStacks.get(tab).push(fragment);
    }

    private void switchFragments(String fromTab, String toTab) {
        if (mStacks.get(fromTab).size() != 0 && mStacks.get(toTab).size() != 0) {
            Fragment from = mStacks.get(fromTab).lastElement();
            Fragment to = mStacks.get(toTab).lastElement();
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(from)
                    .show(to)
                    .commit();
        }
    }

    private void popFragment() {
        if (mStacks.get(currentTab).size() > 1) {
            // pop current fragment from stack
            Fragment currentFragment = mStacks.get(currentTab).pop();
            // get previous fragment
            Fragment previous = mStacks.get(currentTab).elementAt(mStacks.get(currentTab).size() - 1);

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(currentFragment)
                    .show(previous)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mStacks.get(currentTab).size() == 1)
            finish();
        else
            popFragment();
    }

}