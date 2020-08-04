package com.saad102.instagramclone;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileTab extends Fragment {
    private EditText edtName, edtBio, edtProfession, edtHobbies, edtFavouriteSports;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_tab, container, false);
        edtName = view.findViewById(R.id.edtProfileName);
        edtProfession = view.findViewById(R.id.edtProfileProfession);
        edtBio = view.findViewById(R.id.edtProfileBio);
        edtHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtFavouriteSports = view.findViewById(R.id.edtProfileFavoriteSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        final ParseUser currentUser = new ParseUser().getCurrentUser();

        if (currentUser.get("profileName") == null) {
            edtName.setText("");

        } else {
            edtName.setText(currentUser.get("profileName").toString());

        }
        if (currentUser.get("profileProfession") == null) {
            edtProfession.setText("");

        } else {
            edtProfession.setText(currentUser.get("profileProfession").toString());

        }
        if (currentUser.get("profileBio") == null) {
            edtBio.setText("");

        } else {
            edtBio.setText(currentUser.get("profileBio").toString());

        }
        if (currentUser.get("profileHobbies") == null) {
            edtHobbies.setText("");

        } else {
            edtHobbies.setText(currentUser.get("profileHobbies").toString());

        }
        if (currentUser.get("profileFavouriteSports") == null) {
            edtFavouriteSports.setText("");

        } else {
            edtFavouriteSports.setText(currentUser.get("profileFavouriteSports").toString());

        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog pd = new ProgressDialog(getContext());
                pd.setMessage("Signing Up");
                pd.show();
                pd.setCancelable(false);
                currentUser.put("profileName", edtName.getText().toString());
                currentUser.put("profileProfession", edtProfession.getText().toString());
                currentUser.put("profileBio", edtBio.getText().toString());
                currentUser.put("profileHobbies", edtHobbies.getText().toString());
                currentUser.put("profileFavouriteSports", edtFavouriteSports.getText().toString());
                currentUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(getContext(),"Info updated successfully",FancyToast.LENGTH_LONG, FancyToast.SUCCESS,true).show();
                        }else {
                            FancyToast.makeText(getContext(),e.getMessage(),FancyToast.LENGTH_LONG, FancyToast.ERROR,true).show();

                        }
                        pd.dismiss();
                    }
                });
            }
        });



        return view;

    }
}