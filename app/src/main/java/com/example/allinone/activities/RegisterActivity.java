package com.example.allinone.activities;


import android.content.Intent;
import android.content.SharedPreferences;

import android.net.Uri;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.preference.PreferenceManager;


import com.example.allinone.R;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;


public class RegisterActivity extends AppCompatActivity {


    ImageView ImgUserPhoto;
    static int PReqCode = 1;
    static int REQUESCODE = 1;
    Uri pickedImgUri;

    private EditText userEmail, userPassword, userConfirmedPassword, userName;
    private Button regBtn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        userEmail = findViewById(R.id.et_email);
        userPassword = findViewById(R.id.et_password);
        userConfirmedPassword = findViewById(R.id.et_confirm_password);
        userName = findViewById(R.id.et_username);
        regBtn = findViewById(R.id.button_sign_up);
        Toolbar backToolbar = findViewById(R.id.back_toolbar);
        mAuth = FirebaseAuth.getInstance();


        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userConfirmedPassword.getText().toString();
                final String name = userName.getText().toString();

                if (email.isEmpty() || name.isEmpty() || password.isEmpty()) {

                    showWarningMessage("Пожалуйста, заполните все поля !");


                }
                else if (!password.equals(password2)){
                    showWarningMessage("Пароли должны совпадать !");
                }

                else {


                    createUserAccount(email, name, password);
                }


            }
        });
        backToolbar.setOnClickListener(new Toolbar.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login_activity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login_activity);
                finish();
            }
        });


    }

    private void createUserAccount(String email, final String name, String password) {


        // this method create user account with specific email and password

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {


                            showSuccessMessage("Аккаунт создан");
                            // after we created user account we need to update his profile picture and name
                            updateUserInfo(name, mAuth.getCurrentUser());


                        } else {

                            // account creation failed
                            showErrorMessage("Ошибка при создании аккаунта \n" + task.getException().getLocalizedMessage());


                        }
                    }
                });


    }


    // update user photo and name
    private void updateUserInfo(final String name, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url

        UserProfileChangeRequest profleUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();


        currentUser.updateProfile(profleUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            // user info updated successfully
                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            prefs.edit().putBoolean("Islogin", true).commit();
                            updateUI();
                        }

                    }
                });


    }

    private void updateUI() {

        Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);
        finish();


    }

    // simple method to show toast message
    private void showErrorMessage(String message) {
        DynamicToast.makeError(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    private void showWarningMessage(String message) {
        DynamicToast.makeWarning(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

    private void showSuccessMessage(String message) {
        DynamicToast.makeSuccess(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }


}