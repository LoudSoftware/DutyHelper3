package com.example.mohammedabu.dutyhelper.dbHelpers;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Helper class to fetch info about a FireBaseAuth user
 */

public class UserHelper {
    private FirebaseUser currentUser;
    private String UID;
    private String name;
    private String email;

    public UserHelper(FirebaseAuth authInstance) {
        currentUser = authInstance.getCurrentUser();
        if (currentUser != null) {
            name = currentUser.getDisplayName();
            email = currentUser.getEmail();
            UID = currentUser.getUid();
        }
    }


    public void setUserEmail(String newEmail) {

        if (currentUser != null && newEmail != null) {
            currentUser.updateEmail(newEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "User email address updated.");
                    }
                }
            });
        }
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUID() {
        return UID;
    }
}
