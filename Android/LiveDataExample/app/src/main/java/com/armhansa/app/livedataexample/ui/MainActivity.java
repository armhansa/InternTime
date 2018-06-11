package com.armhansa.app.livedataexample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.armhansa.app.livedataexample.R;
import com.armhansa.app.livedataexample.database.realm.NumberRealm;
import com.armhansa.app.livedataexample.recyclerview.MyAdapter;
import com.armhansa.app.livedataexample.viewmodel.NumberViewModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

//    TextView text;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private SwipeRefreshLayout swipeContainer;

    private Observer<RealmResults<NumberRealm>> nameObserver ;

    private FloatingActionButton fab;

    private NumberViewModel myNumber;

    private Realm realm;

    private RealmResults<NumberRealm> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChangeNum.class));
            }
        });

        // Get the ViewModel.
        myNumber = ViewModelProviders.of(this).get(NumberViewModel.class);

        // Create the observer which updates the UI.
        nameObserver =
                new Observer<RealmResults<NumberRealm>>() {
                    @Override
                    public void onChanged(@Nullable RealmResults<NumberRealm> numberRealms) {
                        Log.d(TAG, "onChanged: listenerCalled");
                        if(numberRealms != null ) {
                            if(mAdapter == null)
                                mAdapter = new MyAdapter(numberRealms);
                            else {
                                mAdapter.clear();
                                mAdapter.addAll(numberRealms);
                            }
                            mRecyclerView.setAdapter(mAdapter);

                        }
                        results = numberRealms;
                    }
                };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myNumber.getMyNum().observe(this, nameObserver);

        swipeContainer = findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mAdapter.clear();
                mAdapter.addAll(myNumber.getMyNum().getValue());
                mRecyclerView.setAdapter(mAdapter);

                swipeContainer.setRefreshing(false);

            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_dark,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_red_light);

    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    public void deleteFirst(View view) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                results.deleteAllFromRealm();
            }
        });
    }

}
