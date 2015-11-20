package com.example.deadliam.secret;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchGroupActivity extends Activity {

    //private Button back;
    private TextView phoneNumber;
    private TextView password;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group);

        //back = (Button) findViewById(R.id.back_button);
        phoneNumber = (TextView) findViewById(R.id.testPhone);
        password = (TextView) findViewById(R.id.testPassword);

        String txtPhoneNumber = getIntent().getStringExtra("phoneNumber");

        String txtPassword = getIntent().getStringExtra("password");

        phoneNumber.setText(phoneNumber.getText().toString() + " " + txtPhoneNumber);

        password.setText(password.getText().toString() + " " + txtPassword);

    }


}
