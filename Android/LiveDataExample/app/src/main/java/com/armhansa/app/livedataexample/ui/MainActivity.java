package com.armhansa.app.livedataexample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.armhansa.app.livedataexample.R;
import com.armhansa.app.livedataexample.database.realm.NumberRealm;
import com.armhansa.app.livedataexample.viewmodel.NumberViewModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    TextView text;
    FloatingActionButton fab;

    NumberViewModel myNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        text = findViewById(R.id.text);
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
        final Observer<RealmResults<NumberRealm>> nameObserver =
                new Observer<RealmResults<NumberRealm>>() {
                    @Override
                    public void onChanged(@Nullable RealmResults<NumberRealm> numberRealms) {
                        if(numberRealms != null && numberRealms.size() != 0) {
                            text.setText(numberRealms.get(0).getNumber());
                        }
                    }
                };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        myNumber.getMyNum().observe(this, nameObserver);

    }

}
