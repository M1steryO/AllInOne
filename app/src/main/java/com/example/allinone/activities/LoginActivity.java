package com.example.allinone.activities;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.example.allinone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;


public class LoginActivity extends AppCompatActivity {


    private EditText userMail, userPassword;
    private Button btnLogin, btnToSignUp;

    private FirebaseAuth mAuth;
    private Intent mainActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.button_sign_in);
        btnToSignUp = findViewById(R.id.button_sign_up);
        mAuth = FirebaseAuth.getInstance();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showWarningMessage("Пожалуйста, заполните все поля !");


                } else {
                    signIn(mail, password);
                }


            }
        });
        btnToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerActivity);
                finish();

            }
        });


    }

    private void signIn(String mail, String password) {


        mAuth.signInWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    prefs.edit().putBoolean("Islogin", true).commit();
                    updateUI();

                } else {
                    showMessage(task.getException().getMessage());


                }


            }
        });


    }

    private void updateUI() {

        mainActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainActivity);

        finish();

    }

    private void showMessage(String text) {

        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            //user is already connected  so we need to redirect him to home page
            updateUI();

        }


    }

    private void showWarningMessage(String message) {
        DynamicToast.makeWarning(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }
}