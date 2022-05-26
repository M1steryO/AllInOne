package com.example.allinone.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.allinone.R;
import com.example.allinone.activities.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    private TextView username, email;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private LinearLayout favourite_adding_btn;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        email.setText(currentUser.getEmail());
        username.setText(currentUser.getDisplayName());
        favourite_adding_btn = view.findViewById(R.id.favourite_adding_button);
        favourite_adding_btn.setOnClickListener(favourite_adding_btn_click_listener);


        Button sign_out_btn = view.findViewById(R.id.sign_out_button);
        sign_out_btn.setOnClickListener(sign_out_btn_click_listener);
        return view;


    }


    View.OnClickListener favourite_adding_btn_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };

    View.OnClickListener sign_out_btn_click_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
            prefs.edit().putBoolean("Islogin", false).commit();
            mAuth.signOut();
            Intent login_activity = new Intent(requireContext(), LoginActivity.class);
            startActivity(login_activity);
        }
    };


}