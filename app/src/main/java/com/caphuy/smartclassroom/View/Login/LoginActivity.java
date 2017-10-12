package com.caphuy.smartclassroom.View.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.caphuy.smartclassroom.Presenter.Login.PresenterLogicLoginHandler;
import com.caphuy.smartclassroom.R;

public class LoginActivity extends AppCompatActivity implements ViewLoginHandler, View.OnClickListener {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private PresenterLogicLoginHandler presenterLogicLoginHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenterLogicLoginHandler = new PresenterLogicLoginHandler(this);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void oauthSuccess() {

    }

    @Override
    public void oauthFail() {

    }

    @Override
    public void onClick(View v) {
        String username = this.txtUsername.getText().toString();
        String password = this.txtPassword.getText().toString();
        presenterLogicLoginHandler.oauth(username, password);
    }
}
