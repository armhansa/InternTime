package com.armhansa.app.easyproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.armhansa.app.easyproject.R;
import com.armhansa.app.easyproject.model.User;

import io.realm.Realm;

public class CreateUser extends AppCompatActivity {

    private static final String TAG = "CreateUser";

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private Button addUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);


        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);

        addUser = findViewById(R.id.add_user);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, firstName.getText()+" "+lastName.getText());
                saveUserRealm();
            }
        });
    }

    public void saveUserRealm() {
        String fname = String.valueOf(firstName.getText());
        String lname = String.valueOf(lastName.getText());
        String mail = String.valueOf(email.getText());
        User user = new User(fname, lname, mail);

//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//
//            }
//        });
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
