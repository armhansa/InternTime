package com.armhansa.app.easyproject.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.armhansa.app.easyproject.R;
import com.armhansa.app.easyproject.UserAdapter;
import com.armhansa.app.easyproject.model.User;
import com.armhansa.app.easyproject.viewmodel.UserViewModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;
    private Observer<ArrayList<User>> users;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);

        Realm.init(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        users = new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(@Nullable ArrayList<User> users) {
                Log.d(TAG, "OnChanged!");
                queryRealm();
            }
        };
        userViewModel.getUsers().observe(this, users);

        queryRealm();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "OnClick: Pressed!");
                userViewModel.editValue();
//                startActivityForResult(new Intent(MainActivity.this, CreateUser.class), 24);
            }
        });
    }

    private void queryRealm() {

        adapter = new UserAdapter(userViewModel.getUsers().getValue());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 24) {
            queryRealm();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
