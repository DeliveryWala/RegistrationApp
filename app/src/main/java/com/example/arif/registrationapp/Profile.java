package com.example.arif.registrationapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    TextView user;
    EditText mobile, address, occupation;
    TextInputLayout input_address, input_mobile, input_occupation;
    Button submit;
    MyDBHelper helper;

    String name, email, occ, phn, add;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user = (TextView) findViewById(R.id.user_name);
        mobile = (EditText) findViewById(R.id.input_mobile);
        address = (EditText) findViewById(R.id.input_address);
        occupation = (EditText) findViewById(R.id.input_occupation);
        input_mobile = (TextInputLayout) findViewById(R.id.input_layout_mobile);
        input_address = (TextInputLayout) findViewById(R.id.input_layout_address);
        input_occupation = (TextInputLayout) findViewById(R.id.input_layout_Occupatin);
        submit = (Button) findViewById(R.id.submit);
        helper = new MyDBHelper(this);
        email = getIntent().getStringExtra("email");
        name = helper.getName(email);
        user.setText(name);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSession(v);
            }
        });


    }

    public void startSession(View view) {
        pref = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        editor = pref.edit();
        occ = String.valueOf(occupation.getText());
        add = String.valueOf(address.getText());
        phn = String.valueOf(mobile.getText());
        editor.putString("mobile", phn);
        editor.putString("occupation", occ);
        editor.putString("address", add);
        editor.commit();
        Message.message(getApplicationContext(), "Data Saved");
        startActivity(new Intent(Profile.this, SecondActivity.class));
        finish();
    }

}
