package com.armhansa.app.livedataexample.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.armhansa.app.livedataexample.R;
import com.armhansa.app.livedataexample.database.realm.NumberRealm;
import com.armhansa.app.livedataexample.viewmodel.NumberViewModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChangeNum extends AppCompatActivity {

    private static final String TAG = "ChangeNum";

    private Realm realm;

    private NumberViewModel myNumber;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_num);

        Realm.init(this);

        realm = Realm.getDefaultInstance();

        text = findViewById(R.id.text);

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

    public void changeToZero(View view) {
        changeNum(0);
    }

    public void changeToOne(View view) {
        changeNum(1);
    }

    public void changeToTwo(View view) {
        changeNum(2);
    }

    public void changeToThree(View view) {
        changeNum(3);
    }

    public void changeToFour(View view) {
        changeNum(4);
    }

    public void changeNum(int newNum) {
        realm.beginTransaction();
        NumberRealm numTmp = realm.createObject(NumberRealm.class);
        numTmp.setNumber(String.valueOf(newNum));
        realm.commitTransaction();

        RealmResults<NumberRealm> result = realm.where(NumberRealm.class).findAll();

        Log.d(TAG, "OnClick: Change To "+myNumber.getMyNum());

    }
}
