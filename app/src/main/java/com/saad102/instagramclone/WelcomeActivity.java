package com.saad102.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    private TextView txtMsg;
    private Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //initialization view
        txtMsg = findViewById(R.id.txtMsg);
        btnLogout = findViewById(R.id.btnLogout);

        txtMsg.setText("Welcome" + ParseUser.getCurrentUser().get("username"));
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                finish();
            }
        });
    }
}