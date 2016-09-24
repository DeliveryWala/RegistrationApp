package com.example.arif.registrationapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Register extends Fragment {
    private Button register;
    String storedEmail;
    MyDBHelper adapter;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private TextInputLayout inputName, inputEmail, inputPassword;
    private EditText name, password, email;
    String nameEd, pasEd, emailEd;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        inputName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        inputEmail = (TextInputLayout) view.findViewById(R.id.input_layout_email);
        inputPassword = (TextInputLayout) view.findViewById(R.id.input_layout_password);
        name = (EditText) view.findViewById(R.id.input_name);
        email = (EditText) view.findViewById(R.id.input_email);
        password = (EditText) view.findViewById(R.id.input_password);
        register = (Button) view.findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });

        return view;

    }

    public void submitForm() {
        pref=getContext().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        editor=pref.edit();
        nameEd = name.getText().toString();
        pasEd = password.getText().toString();
        emailEd = email.getText().toString();
        adapter = new MyDBHelper(getContext());
        name.setText("");
        email.setText("");
        password.setText("");
        storedEmail = adapter.checkEmailExists(emailEd);
        if (pasEd.length() < 6) {
            Message.message(getContext(), "Weak password strength");
        } else {


            if (emailEd.equals(storedEmail)) {
                Message.message(getContext(), "Already Registered");
            } else {
                if (emailEd.equals("") || pasEd.equals("") || nameEd.equals("")) {
                    Message.message(getContext(), "Fields Vacant");
                    return;
                } else {
                    long id = adapter.insertEntry(nameEd, emailEd, pasEd);
                    if (id < 0) {
                        Message.message(getContext(), "UnSuccessful");

                    } else {
                        Message.message(getContext(), "Successfully inserted record");
                        editor.putString("name",nameEd);
                        editor.putString("email",emailEd);
                        editor.commit();
                        Intent intent=new Intent(getContext(),Profile.class);
                        intent.putExtra("email",emailEd);
                        startActivity(intent);
                    }


                }
            }
        }
    }
}
