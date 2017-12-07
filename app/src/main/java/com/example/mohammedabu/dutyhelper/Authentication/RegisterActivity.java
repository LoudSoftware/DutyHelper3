package com.example.mohammedabu.dutyhelper.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * RegisterActivity handles new user creation logic
 */
public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private AppCompatButton btnSignup;
    private TextView loginLink;
    private FirebaseAuth mAuth;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userName;
    private EditText userMobile;
    private EditText userReenteredPassword;
    private EditText userAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d(TAG, "onCreate started");

        userEmail = (EditText) findViewById(R.id.input_email);
        userPassword = (EditText) findViewById(R.id.input_password);
        userName = (EditText) findViewById(R.id.input_name);
        userMobile = (EditText) findViewById(R.id.input_mobile);
        userReenteredPassword = (EditText) findViewById(R.id.input_reEnterPassword);
        userAddress = (EditText) findViewById(R.id.input_address);

        mAuth = FirebaseAuth.getInstance();

        btnSignup = (AppCompatButton) findViewById(R.id.btn_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });

        loginLink = (TextView) findViewById(R.id.link_login);
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, Login.class);
                RegisterActivity.this.startActivity(registerIntent);
                finish();
            }
        });


    }

    /**
     * This was an attempt to show a progressDialog during signup
     */
    @Deprecated
    public void signup() {
        Log.d(TAG, "Signup");

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void registerUser() {
        String Email = userEmail.getText().toString().trim();
        String Password = userPassword.getText().toString().trim();
        final String name = userName.getText().toString().trim();

        // Checking if the required text Fields are empty
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Email field is empty", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Password field is empty", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Name field is empty", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        try {
                            //check if successful
                            if (task.isSuccessful()) {
                                //User is successfully registered to the data base

                                //Create the account successful and take user to the Login screen
                                finish();

                                // Adds additional user information
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name)
                                        .build();

                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile updated.");
                                                }
                                            }
                                        });


                                Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}


