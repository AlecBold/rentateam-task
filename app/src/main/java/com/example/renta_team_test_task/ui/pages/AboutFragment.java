package com.example.renta_team_test_task.ui.pages;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.renta_team_test_task.R;

import java.io.IOException;
import java.util.Scanner;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AboutFragment extends Fragment {
    public static final String TAG = "AboutFragment";
    public static final String TASK_FILE = "task.txt";

    private TextView aboutTxt;

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initViews(view);

        setTaskText();
        return view;
    }

    private void initViews(View view) {
        aboutTxt = view.findViewById(R.id.aboutTxt);
        aboutTxt.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setTaskText() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                try {
                    Scanner scanner = new Scanner(requireActivity().
                            getApplicationContext().
                            getAssets()
                            .open(TASK_FILE));

                    while (scanner.hasNextLine()) {
                        emitter.onNext(scanner.nextLine() + "\n");
                    }
                    emitter.onComplete();
                } catch (IOException io) {
                    emitter.onError(io);
                }
            }
        });
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String s) {
                aboutTxt.setText(aboutTxt.getText().toString() + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
            }

            @Override
            public void onComplete() {
            }
        };
        observable.subscribe(observer);
    }

}