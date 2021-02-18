package com.example.swipetolearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.swipetolearn.utils.Constants;
import com.example.swipetolearn.utils.PreferenceUtils;


public class SwipeToLearnLoginActivity extends Activity implements View.OnClickListener {

    private EditText mLoginEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mLoginEditText =(EditText) findViewById(R.id.loginEditText);
        mPasswordEditText=(EditText) findViewById(R.id.passwordEditText);

        findViewById(R.id.loginButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String login;
        if(TextUtils.isEmpty(mLoginEditText.getText())){
            Toast.makeText(this, "Empty login !",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(mPasswordEditText.getText())){
            Toast.makeText(this, "Empty password !",Toast.LENGTH_LONG).show();
            return;
        }

        login=(String) mLoginEditText.getText().toString();
        PreferenceUtils.setLogin(login);
        startActivity(getHomeIntent(login));
    }

    private Intent getHomeIntent(String userName){
        Intent intent=new Intent(this, SwipeToLearnActivity.class);
        final Bundle extras= new Bundle();
        extras.putString(Constants.Login.EXTRA_LOGIN, userName);
        intent.putExtras(extras);
        return intent;
    }
}
