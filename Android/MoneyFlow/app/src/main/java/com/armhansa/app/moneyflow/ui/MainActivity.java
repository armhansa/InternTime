package com.armhansa.app.moneyflow.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.armhansa.app.moneyflow.R;
import com.armhansa.app.moneyflow.model.Event;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new ListOfEvent())
                    .commit();
        }
    }

    public void showEvent(Event event) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new EventDesribes().Instance...)
                .commit();
    }

}
