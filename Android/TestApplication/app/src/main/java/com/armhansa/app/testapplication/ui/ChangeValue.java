package com.armhansa.app.testapplication.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.armhansa.app.testapplication.R;

public class ChangeValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_value);
    }

    public void changeToZero(View view) {
        changeValue(0);
    }

    public void changeToOne(View view) {
        changeValue(1);
    }

    public void changeToTwo(View view) {
        changeValue(2);
    }

    public void changeToThree(View view) {
        changeValue(3);
    }

    public void changeToFour(View view) {
        changeValue(4);
    }

    public void changeValue(int newValue) {

    }
}
