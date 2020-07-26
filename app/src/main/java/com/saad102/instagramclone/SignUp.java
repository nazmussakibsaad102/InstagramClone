package com.saad102.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText edtMail, edtUserName, edtPassword;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");


        edtMail = findViewById(R.id.edtSignUpEmail);
        edtUserName = findViewById(R.id.edtSignUpUsername);
        edtPassword = findViewById(R.id.edtSignUpPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                final ParseUser user = new ParseUser();
                user.setUsername(edtUserName.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                user.setEmail(edtMail.getText().toString());

// other fields can be set just like with ParseObject

                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            FancyToast.makeText(SignUp.this,"Congratulations "+ user.getUsername()+", your id is created successfully.",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            FancyToast.makeText(SignUp.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true);
                        }
                    }
                });
                break;
            case R.id.btnLogIn:
                Intent i = new Intent(SignUp.this, LoginActivity.class);
                startActivity(i);
                break;
        }
    }
}