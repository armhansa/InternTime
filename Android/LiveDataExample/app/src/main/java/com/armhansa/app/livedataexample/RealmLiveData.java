package com.armhansa.app.livedataexample;

import android.arch.lifecycle.LiveData;

import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmLiveData<T extends RealmModel> extends LiveData<RealmResults<T>> {

    private RealmResults<T> results;

    private final RealmChangeListener<RealmResults<T>> listener =
            new RealmChangeListener<RealmResults<T>>() {
                @Override
                public void onChange(RealmResults<T> ts) {
                    setValue(ts);
                }
            };

    public RealmLiveData(RealmResults<T> results) {
        this.results = results;
    }

    @Override
    protected void onActive() {
        results.addChangeListener(listener);
        super.onActive();
    }

    @Override
    protected void onInactive() {
        results.removeAllChangeListeners();
        super.onInactive();
    }

}
