package com.example.renta_team_test_task.ui.pages;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.renta_team_test_task.MyApplication;
import com.example.renta_team_test_task.R;
import com.example.renta_team_test_task.pojo.JUser;
import com.example.renta_team_test_task.ui.adapters.UserListRecyclerViewAdapter;
import com.example.renta_team_test_task.ui.viewmodels.UserViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListUserFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private UserListRecyclerViewAdapter adapter;

    @Inject
    public UserViewModel userViewModel;

    public ListUserFragment() {
    }

    public static ListUserFragment newInstance() {
        ListUserFragment fragment = new ListUserFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user, container, false);
        initViews(view);

        adapter = new UserListRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);
        userViewModel.getUsers().subscribeOn(Schedulers.io()).subscribe(observer);

        refreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onRefresh() {
        progressBar.setVisibility(View.VISIBLE);
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        userViewModel.updateUsers().subscribeOn(Schedulers.io()).subscribe(observer);
    }

    private Observer<List<JUser>> observer = new Observer<List<JUser>>() {
        @Override
        public void onSubscribe(Disposable d) {}

        @Override
        public void onNext(List<JUser> list) {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refreshOnSuccess(list);
                }
            });
        }

        @Override
        public void onError(Throwable e) {
            requireActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refreshOnError(e);
                }
            });
        }

        @Override
        public void onComplete() {}
    };

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.vertical_divider));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(dividerItemDecoration);

        refreshLayout = view.findViewById(R.id.refreshLayout);
        progressBar = view.findViewById(R.id.progressBar);
    }

    private void refreshOnSuccess(List<JUser> list) {
        adapter.setJUserList(list);
        progressBar.setVisibility(View.GONE);
    }

    private void refreshOnError(Throwable e) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(requireActivity(), R.string.error_message, Toast.LENGTH_LONG).show();
    }
}