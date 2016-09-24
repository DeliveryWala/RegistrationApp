package com.example.arif.registrationapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Cart extends AppCompatActivity {
    TextView nameShow,addressShow,emailShow,mobileShow,occupationShow;
    public static final String DEFAULT="N/A";
    View view;
    Button logout;
    SharedPreferences pref;
    Timer timer;
    LogoutTimer log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        timer=new Timer();
        log=new LogoutTimer();
        timer.schedule(log,60000);
        nameShow= (TextView) findViewById(R.id.nameShow);
        emailShow= (TextView) findViewById(R.id.emailShow);
        mobileShow= (TextView) findViewById(R.id.mobileShow);
        occupationShow= (TextView) findViewById(R.id.occupationShow);
        addressShow= (TextView) findViewById(R.id.addressShow);
        logout= (Button) findViewById(R.id.logout);
        load(view);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart.this,MainActivity.class));
            }
        });

    }
    public void load(View view)
    {
        pref=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name=pref.getString("name",DEFAULT);
        String email=pref.getString("email",DEFAULT);
        String mobile=pref.getString("mobile",DEFAULT);
        String occupation=pref.getString("occupation",DEFAULT);
        String address=pref.getString("address",DEFAULT);
        if(mobile.equals(DEFAULT)||occupation.equals(DEFAULT)||address.equals(DEFAULT))
        {
            Message.message(getApplicationContext(),"No Data Found");
        }
        else
        {
            Message.message(getApplicationContext(),"Data Found");
            nameShow.setText(name);
            emailShow.setText(email);
            mobileShow.setText(mobile);
            occupationShow.setText(occupation);
            addressShow.setText(address);
        }


    }
    private class LogoutTimer extends TimerTask
    {

        @Override
        public void run() {
            startActivity(new Intent(Cart.this,MainActivity.class));

        }
    }

}
