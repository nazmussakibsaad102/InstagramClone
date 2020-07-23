package com.saad102.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignupLoginActivity extends AppCompatActivity {
    private EditText edtUserNameSignUp, edtPasswordSignUp, edtUserNameLogin, edtPasswordLogin ;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);
        edtUserNameSignUp = findViewById(R.id.edtUserNameSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        edtUserNameLogin = findViewById(R.id.edtUserNameLogin);
        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser user = new ParseUser();
                user.setUsername(edtUserNameSignUp.getText().toString());
                user.setPassword(edtPasswordSignUp.getText().toString());
//                user.setEmail("email@example.com");

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            FancyToast.makeText(getApplicationContext(),"Account Created!",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            FancyToast.makeText(getApplicationContext(),e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtUserNameLogin.getText().toString(),
                                             edtPasswordLogin.getText().toString(),
                        new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(i);

                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                            FancyToast.makeText(getApplicationContext(),e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }
                    }
                });

            }
        });

    }
}