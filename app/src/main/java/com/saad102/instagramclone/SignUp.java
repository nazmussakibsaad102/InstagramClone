package com.saad102.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {
    private EditText mEditTextPlayerName, mEditTextPunchSpeed, mEditTextKickSpeed, mEditTextFlyingSpeed;
    private Button btnSave, btnGetData, btnAnotherActivity;
    private TextView txtShowData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mEditTextPlayerName = findViewById(R.id.edtPlayerName);
        mEditTextPunchSpeed= findViewById(R.id.edtPunchSpeed);
        mEditTextKickSpeed = findViewById(R.id.edtKickSpeed);
        mEditTextFlyingSpeed = findViewById(R.id.edtFlyingKick);
        btnSave = findViewById(R.id.btnSave);
        btnGetData = findViewById(R.id.btnGetData);
        btnAnotherActivity = findViewById(R.id.btnAnotherActivity);
        txtShowData = findViewById(R.id.txtShowData);

            btnSave.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    try {
                        final ParseObject boxer = new ParseObject("Boxer");
                        boxer.put("name", mEditTextPlayerName.getText().toString());
                        boxer.put("punch_Speed", Integer.parseInt(mEditTextPunchSpeed.getText().toString()));
                        boxer.put("kick_speed", Integer.parseInt(mEditTextKickSpeed.getText().toString()));
                        boxer.put("flying_kick", Integer.parseInt(mEditTextFlyingSpeed.getText().toString()));


                        boxer.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e==null){
                                    FancyToast.makeText(getApplicationContext(),"boxer class is saved",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                                }else {
                                    FancyToast.makeText(getApplicationContext(),e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                                }

                            }
                        });
                    }catch (Exception e){
                        FancyToast.makeText(SignUp.this,e.toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                    }


                }
            });

            btnGetData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("Boxer");
                    query.whereEqualTo("objectId", "CgpvNLpE5p");
                    query.getFirstInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                if (object != null){
                                    String playerName = object.getString("name");
                                    int PUNCH_SPEED = object.getInt("punch_Speed");
                                    int KICK_SPEED = object.getInt("kick_speed");
                                    int FLYING_KICK = object.getInt("flying_kick");

                                    txtShowData.setText("Player name: " + playerName
                                            + "\n Punch Speed: " + PUNCH_SPEED
                                            + "\n Kick Speed: "+ KICK_SPEED
                                            + "\n Flying Kick:"+ FLYING_KICK );
                                }

                            } else {
                                // Something is wrong
                                FancyToast.makeText(SignUp.this,e.toString(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                            }
                        }
                    });
                }
            });
            btnAnotherActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(SignUp.this, SignupLoginActivity.class);
                    startActivity(i);
                }
            });





    }
}