package com.armhansa.app.livedataexample.database.realm;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class NumberRealm extends RealmObject {

//    @PrimaryKey @Index
//    private int index;

    @Required
    private String number;

    public void setNumber(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

}
