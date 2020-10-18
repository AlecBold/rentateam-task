package com.example.renta_team_test_task.di.componets;

import com.example.renta_team_test_task.di.scopes.ActivityScope;
import com.example.renta_team_test_task.ui.pages.ListUserFragment;
import com.example.renta_team_test_task.ui.pages.UserFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent
public interface UsersSubComponent {

    @Subcomponent.Factory
    interface Factory {
        UsersSubComponent create();
    }

    void inject(ListUserFragment listUserFragment);
    void inject(UserFragment userFragment);
}
