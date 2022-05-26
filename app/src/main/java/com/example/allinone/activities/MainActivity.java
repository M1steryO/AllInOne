package com.example.allinone.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.allinone.R;
import com.example.allinone.fragments.HomeFragment;
import com.example.allinone.fragments.ProfileFragment;
import com.example.allinone.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, new HomeFragment()).commit();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean is_login = prefs.getBoolean("Islogin", false);
        if (!is_login)
            init();


        bottomNavigationView = findViewById(R.id.bottomNav_view);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    item.setChecked(true);
                    fragment = new HomeFragment();
                    break;
                case R.id.profile:
                    item.setChecked(true);
                    fragment = new ProfileFragment();
                    break;
                case R.id.search:
                    item.setChecked(true);
                    fragment = new SearchFragment();
                    break;


            }
            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.myContainer, fragment).commit();
            return false;
        });

    }

    private void init() {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean is_login = prefs.getBoolean("Islogin", false);

        if (!is_login) {   // condition true means user is already logins
            Intent login_activity = new Intent(this, LoginActivity.class);
            startActivity(login_activity);
            finish();
        }


    }


}



