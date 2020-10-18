package com.example.renta_team_test_task.ui.pages;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.renta_team_test_task.MyApplication;
import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.pojo.JUser;
import com.example.renta_team_test_task.ui.viewmodels.UserViewModel;

import javax.inject.Inject;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserFragment extends Fragment {
    public static final String KEY_ID = "key_id";
    private int currentId;

    private TextView nameTxt;
    private TextView lastNameTxt;
    private TextView emailTxt;
    private ImageView avatarImg;

    @Inject
    public UserViewModel userViewModel;

    public UserFragment() {
    }

    public static UserFragment newInstance(int id) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication) requireActivity().getApplication()).getAppComponent().usersSubComponent().create().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentId = getArguments().getInt(KEY_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initViews(view);

        userViewModel.getUserById(Integer.toString(currentId)).subscribeOn(Schedulers.io()).subscribe(maybeObserver);

        return view;
    }

    private MaybeObserver<JUser> maybeObserver = new MaybeObserver<JUser>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onSuccess(JUser jUser) {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    nameTxt.setText(jUser.getName());
                    lastNameTxt.setText(jUser.getLastName());
                    emailTxt.setText(jUser.getEmail());
                    Glide.with(requireActivity())
                            .asBitmap()
                            .load(jUser.getUrlImg())
                            .into(avatarImg);
                }
            });
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onComplete() {
        }
    };

    private void initViews(View view) {
        nameTxt = view.findViewById(R.id.name);
        lastNameTxt = view.findViewById(R.id.lastName);
        emailTxt = view.findViewById(R.id.email);
        avatarImg = view.findViewById(R.id.avatarImg);
    }
}