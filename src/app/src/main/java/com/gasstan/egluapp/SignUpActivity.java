package com.gasstan.egluapp;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gasstan.backend.myApi.model.MyBean;

import backendConnection.RegisterRequest;
import data.User;
import listeners.ApiListener;

public class SignUpActivity extends AppCompatActivity implements OnClickListener, ApiListener {
    protected EditText email, password;
    protected Button register, haveAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUpViews();

    }

    private void setUpViews() {
        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        register = (Button) findViewById(R.id.asu_btn_register);
        haveAcc = (Button) findViewById(R.id.asu_btn_have_acc);

        haveAcc.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        register.setOnClickListener(this);
        haveAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.asu_btn_register:
                if (checkFields()) {
                    User user = new User().setEmail(email.getText().toString()).setPassword(password.getText().toString());
                    new RegisterRequest(this).execute(user);
                } else
                    Toast.makeText(this, getResources().getString(R.string.both_fields_must_be_filled), Toast.LENGTH_LONG).show();
                break;
            case R.id.asu_btn_have_acc:
                onBackPressed();
                break;
        }
    }

    private boolean checkFields() {
        return email.getText().length() > 0 && password.getText().length() > 0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter_right, R.anim.anim_exit_right);
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
