package com.saad102.instagramclone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersTab extends Fragment {

    private ListView listView;
    private ArrayList arraylist;
    private ArrayAdapter mArrayAdapter;

    public UsersTab() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users_tab, container, false);
        listView = view.findViewById(R.id.listView);
        arraylist = new ArrayList();
        mArrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, arraylist);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if(e == null){
                    if (users.size() > 0){
                        for (ParseUser user: users){
                            arraylist.add(user.getUsername());
                        }
                        listView.setAdapter(mArrayAdapter);
                    }
                }
            }
        });
        return view;
    }
}