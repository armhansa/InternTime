package com.armhansa.app.livedataexample.model;

import android.arch.lifecycle.LiveData;

public class MyNumber extends LiveData<String> {

    // Make Singleton Class
    private static MyNumber myNumberInstance;

    private MyNumber() {
    }
    public static MyNumber getInstance() {
        if(myNumberInstance == null) {
            myNumberInstance = new MyNumber();
        }
        return myNumberInstance;
    }

    @Override
    protected void setValue(String value) {
        super.setValue(value);
    }



}
