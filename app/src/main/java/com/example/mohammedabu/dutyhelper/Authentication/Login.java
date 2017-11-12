package com.example.mohammedabu.dutyhelper.Authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohammedabu.dutyhelper.MainActivity;
import com.example.mohammedabu.dutyhelper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity{

    private static final String TAG = "LoginScreen";
    private EditText userEmail;
    private EditText userPassword;
    private  Button tvLogin;
    private TextView registerLink;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userEmail = (EditText)findViewById(R.id.emailField);
        userPassword = (EditText)findViewById(R.id.passwordField);
        tvLogin = (Button)findViewById(R.id.tvLogin);
        registerLink = (TextView)findViewById(R.id.tv_SignUp);


        // set listeners to be able to disable the login button if the login fields are empty
        userEmail.addTextChangedListener(mTextWatcher);
        userPassword.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(Login.this, RegisterActivity.class);
                Login.this.startActivity(registerIntent);
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login();
                Intent loginIntent = new Intent(Login.this, MainActivity.class);
                Login.this.startActivity(loginIntent);
            }
        });
    }

    //Tool to allow checking if the EditText field has been filled or not. Enabling and disabling
    // the login button in the application accordingly.

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
       tvLogin = (Button) findViewById(R.id.tvLogin);

        String s1 = userEmail.getText().toString();
        String s2 = userPassword.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            tvLogin.setEnabled(false);
        } else {
            tvLogin.setEnabled(true);
        }
    }


    public void login(){
        Log.d(TAG, "Login Page");
        /**
        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait while we log you in...");
        progressDialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
         **/

        String Email = userEmail.getText().toString().trim() ;
        String Password= userPassword.getText().toString().trim() ;

        mAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //check if successful
                        if (task.isSuccessful()){
                            firebaseUser = mAuth.getCurrentUser();
                            finish();
                            Toast.makeText(Login.this, "Logging in successful", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Logging in failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
