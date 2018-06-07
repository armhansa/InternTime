package com.armhansa.app.easyproject.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.armhansa.app.easyproject.model.ListUser;
import com.armhansa.app.easyproject.model.User;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> users;

    public LiveData<ArrayList<User>> getUsers() {
        if(users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
        ListUser listUser = new ListUser();
        users.setValue(listUser.getAllUsers());

    }

    public void editValue(){
       ArrayList<User> user = users.getValue();
        ListUser listUser = new ListUser();
        listUser.addUser(new User("sasda","asd","asd"));
    }
}
