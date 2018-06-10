package com.armhansa.app.testapplication.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.armhansa.app.testapplication.database.NumberDao;
import com.armhansa.app.testapplication.database.dao.RecipeDao;
import com.armhansa.app.testapplication.model.Number;

import io.realm.Realm;
import io.realm.RealmResults;

public class NumberViewModel extends ViewModel {

    private Realm database;
    private NumberDao dao;

    private LiveData<Number> number;

    public NumberViewModel() {
        database = Realm.getDefaultInstance();
        dao = new NumberDao(database);
        number = Transformations.map(dao.findFirstAsync());
    }

//    public void deleteAllRecipes() {
//        dao.deleteAll();
//    }

    @Override
    protected void onCleared() {
        database.close();
        super.onCleared();
    }

    public RealmResults<RecipeEntity> getRecipes() {
        return recipes;
    }

}
