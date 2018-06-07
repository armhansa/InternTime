package com.armhansa.app.easyproject.model;

import com.armhansa.app.easyproject.livedata.LiveRealmData;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class ListUser {

    private Realm realm;
    private LiveRealmData<User> users;

    public ListUser() {
        realm = Realm.getDefaultInstance();
    }

    public ArrayList<User> getAllUsers() {
        RealmResults<User> results = realm.where(User.class)
                .findAll();
        ArrayList<User> users = new ArrayList<>(results);
        return users;
    }

    public void addUser(User user) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(user);
        realm.commitTransaction();
    }

}
