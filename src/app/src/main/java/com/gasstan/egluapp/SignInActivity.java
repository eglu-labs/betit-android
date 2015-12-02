package com.gasstan.egluapp;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gasstan.backend.myApi.model.MyBean;

import backendConnection.LoginRequest;
import data.User;
import listeners.ApiListener;

public class SignInActivity extends AppCompatActivity implements OnClickListener, ApiListener {

    protected EditText email, password;
    protected Button login, noAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setUpViews();

    }

    private void setUpViews() {
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.asi_btn_login);
        noAcc = (Button) findViewById(R.id.asi_btn_no_acc);

        noAcc.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        login.setOnClickListener(this);
        noAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.asi_btn_login:
                if (checkFields()) {
                    User user = new User().setEmail(email.getText().toString()).setPassword(password.getText().toString());
                    new LoginRequest(this).execute(user);
                } else
                    Toast.makeText(this, getResources().getString(R.string.both_fields_must_be_filled), Toast.LENGTH_LONG).show();
                break;
            case R.id.asi_btn_no_acc:
                Intent signUp = new Intent(this, SignUpActivity.class);
                startActivity(signUp);
                overridePendingTransition(R.anim.anim_enter_left, R.anim.anim_exit_left);
                break;
        }
    }

    private boolean checkFields() {
        return email.getText().length() > 0 && password.getText().length() > 0;
    }

    @Override
    public void onApiResponse(MyBean response) {
        if (response != null && response.getSuccess()) {
            Intent welcome = new Intent(this, WelcomeActivity.class);
            welcome.putExtra(WelcomeActivity.MESSAGE, response.getMessage());
            startActivity(welcome);
            overridePendingTransition(R.anim.anim_enter_left, R.anim.anim_exit_left);
        } else
            Toast.makeText(this, getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
    }
}
