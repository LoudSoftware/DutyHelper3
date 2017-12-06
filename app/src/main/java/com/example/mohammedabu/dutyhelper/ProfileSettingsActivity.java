package com.example.mohammedabu.dutyhelper;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mohammedabu.dutyhelper.Authentication.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class ProfileSettingsActivity extends AppCompatActivity {

    private static final String TAG = "ProfileSettings Activity";

    private static final Uri IMAGE0 = Uri.parse("gs://loginui-betterversion.appspot.com/Default Profile Pictures/profile1.png");
    private static final Uri IMAGE1 = Uri.parse("gs://loginui-betterversion.appspot.com/Default Profile Pictures/profile2.png");
    private static final Uri IMAGE2 = Uri.parse("gs://loginui-betterversion.appspot.com/Default Profile Pictures/profile3.png");
    private static final Uri IMAGE3 = Uri.parse("gs://loginui-betterversion.appspot.com/Default Profile Pictures/profile4.png");
    private static final Uri IMAGE4 = Uri.parse("gs://loginui-betterversion.appspot.com/Default Profile Pictures/profile5.png");
    private static final Uri IMAGE5 = Uri.parse("gs://loginui-betterversion.appspot.com/Default Profile Pictures/profile6.png");

    ImageView profileImage1;
    ImageView profileImage2;
    ImageView profileImage3;
    ImageView profileImage4;
    ImageView profileImage5;
    ImageView profileImage6;
    Button logout;
    Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        profileImage1 = (ImageView) findViewById(R.id.teamid00);
        profileImage2 = (ImageView) findViewById(R.id.teamid01);
        profileImage3 = (ImageView) findViewById(R.id.teamid02);
        profileImage4 = (ImageView) findViewById(R.id.teamid03);
        profileImage5 = (ImageView) findViewById(R.id.teamid04);
        profileImage6 = (ImageView) findViewById(R.id.teamid05);
        logout = (Button)findViewById(R.id.buttonLogout);

        // Makes the arrow image act as a back button.
        ImageView backButton = (ImageView)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Makes the logout button, log the user out of the application and back to login page.
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loggingUserOut = new Intent(ProfileSettingsActivity.this, Login.class);
                startActivity(loggingUserOut);
                finish();
            }
        });

        //adds the functionality of pressing on the images in the layout
        View.OnClickListener clickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setProfilePicture(view);
            }
        };

        profileImage1.setOnClickListener(clickListener);
        profileImage2.setOnClickListener(clickListener);
        profileImage3.setOnClickListener(clickListener);
        profileImage4.setOnClickListener(clickListener);
        profileImage5.setOnClickListener(clickListener);
        profileImage6.setOnClickListener(clickListener);
    }

    public void setProfilePicture(View view) {
        //Creating a Return intent to pass to the Main Activity
        Intent returnIntent = new Intent();
        //Figuring out which image was clicked
        ImageView selectedImage = (ImageView) view;
        
        setImageUri(selectedImage);
        //Adding stuff to the return intent
        returnIntent.putExtra("imageID", selectedImage.getId());
        setResult(RESULT_OK, returnIntent);
        //Finishing Activity and return to main screen!
        finish();
    }

    /**
     * Sets the Firebase user image Uri
     * @param image
     */
    private void setImageUri(ImageView image) {
        Uri uri;
        switch (image.getId()) {
            case R.id.teamid00:
                uri = IMAGE0;
                break;
            case R.id.teamid01:
                uri = IMAGE1;
                break;
            case R.id.teamid02:
                uri = IMAGE2;
                break;
            case R.id.teamid03:
                uri = IMAGE3;
                break;
            case R.id.teamid04:
                uri = IMAGE4;
                break;
            case R.id.teamid05:
                uri = IMAGE5;
                break;
            default:
                uri = IMAGE0;
                break;

        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            Log.d(TAG, "User profile updated with UIR: " + user.getPhotoUrl());
                        }
                    }
                });
    }
// Being edited
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data){
            Uri selectedImage = data.getData();
            String filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            ImageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }

    }
    */
}