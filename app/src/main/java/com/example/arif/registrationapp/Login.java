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


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    MyDBHelper helper;

    private Button login;
    private TextInputLayout emailLayout, pasLayout;
    private EditText email, password;
    String emailEd, passwordEd, storedPassword;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public Login() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailLayout = (TextInputLayout) view.findViewById(R.id.login_layout_email);
        pasLayout = (TextInputLayout) view.findViewById(R.id.login_layout_password);
        email = (EditText) view.findViewById(R.id.login_email);
        password = (EditText) view.findViewById(R.id.login_password);
        login = (Button) view.findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOperation();
            }
        });
        return view;
    }


    public void startOperation() {
        helper = new MyDBHelper(getContext());
        pref = getContext().getSharedPreferences("MyData", Context.MODE_PRIVATE);
        editor = pref.edit();
        emailEd = String.valueOf(email.getText());
        passwordEd =  String.valueOf(password.getText());
        String storedName=helper.getName(emailEd);
        email.setText("");
        password.setText("");
        storedPassword = helper.checkPasswordMatch(emailEd);
        if (storedPassword.equals(passwordEd)) {
            editor.putString("name",storedName);
            editor.putString("email",emailEd);
            editor.commit();
            Intent i=new Intent(getContext(), Profile.class);
            i.putExtra("email",emailEd);
            startActivity(i);
        } else {
            Message.message(getContext(), "email and password do not match");
        }


    }

    private boolean isValidEmail(String email) {
      String EMAIL_PATTERN = "^[_A-Za-z0-9\\+]+(\\[_A-Za-z0-9]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+0*(\\.[A-za-z]{2,})$";
       // Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        //Matcher matcher = pattern.matcher(email);
        return false;//
        // matcher.matches;
    }

    private boolean isValidPassword(String pas) {

        if (pas != null && pas.length()>6){
            return true;
        }
        else
        {
            return false;
        }
    }
}

