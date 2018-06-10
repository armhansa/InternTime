package com.armhansa.app.testapplication.database;

import android.arch.lifecycle.LiveData;

import com.armhansa.app.testapplication.database.util.RealmLiveData;
import com.armhansa.app.testapplication.model.Number;

import io.realm.Realm;
import io.realm.RealmResults;

public class NumberDao {

    private Realm db;

    public NumberDao(Realm db) {
        this.db = db;
    }

    public LiveData<Number> findAllAsync() {
        return new RealmLiveData<>(db.where(Number.class).findAllAsync().first());
    }

}
