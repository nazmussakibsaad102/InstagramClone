package com.saad102.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtMail, edtPass;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");
        edtMail = findViewById(R.id.edtLoginEmail);
        edtPass = findViewById(R.id.edtLoginPassword);
        btnLogin = findViewById(R.id.btnLoginActivity);
        btnSignUp = findViewById(R.id.btnSignUpLoginActivity);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
//        if (ParseUser.getCurrentUser() != null){
//            ParseUser.getCurrentUser().logOut();
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoginActivity:
                ParseUser.logInInBackground(edtMail.getText().toString(),
                        edtPass.getText().toString(),
                        new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            // Hooray! The user is logged in.
                            FancyToast.makeText(LoginActivity.this,"Hello World !",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                        } else if (e != null){
                            // Signup failed. Look at the ParseException to see what happened.
                            FancyToast.makeText(LoginActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });
                break;
            case R.id.btnSignUpLoginActivity:
                break;
        }
    }
}