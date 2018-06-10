package com.armhansa.app.testapplication.model;

import io.realm.RealmModel;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Number implements RealmModel{

    @PrimaryKey @Index
    private int id;

    @Required
    private int counter;

    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
}
