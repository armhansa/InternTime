package com.armhansa.app.livedataexample.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.armhansa.app.livedataexample.RealmLiveData;
import com.armhansa.app.livedataexample.database.realm.NumberRealm;

import java.util.ArrayList;

import io.realm.Realm;


public class NumberViewModel extends ViewModel {

    private Realm mDatabase;
    private MutableLiveData<String> myNum;

    private RealmLiveData<NumberRealm> myNumObj;

    public NumberViewModel() {
        mDatabase = Realm.getDefaultInstance();
    }

    public RealmLiveData<NumberRealm> getMyNum() {
//        if(myNum == null) {
//            myNum = new MutableLiveData<>();
//            getDatabase();
//        }
        if(myNumObj == null) {
            myNumObj = new RealmLiveData<>(mDatabase.where(NumberRealm.class)
                    .findAllAsync());
        }
        return myNumObj;
    }

    @Override
    protected void onCleared() {
        mDatabase.close();
        super.onCleared();
    }
}
