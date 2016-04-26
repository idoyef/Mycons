package com.mycons_web.mycons;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by on 25/04/2016.
 */
public class CreateMycons extends Activity {

    public static final int IMAGE_GALLERY_REQUEST = 20;
    public static final int ACTIVITY_SELECT_IMAGE = 1234;

    ImageView pictureImageView;
    TextView testTextView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_mycons);

        pictureImageView = (ImageView) findViewById(R.id.pictureImageView);
        testTextView = (TextView) findViewById(R.id.testTextView);

        Button choosePicButton = (Button) findViewById(R.id.pickButton);
        choosePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent photoPickerIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
              //  final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(photoPickerIntent, ACTIVITY_SELECT_IMAGE);

/*
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                File picDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);
                String picDirectoryPath = picDirectory.getPath();
                Uri data = Uri.parse(picDirectoryPath);

                photoPickerIntent.setDataAndType(data, "image/*");

                startActivityForResult(photoPickerIntent, ACTIVITY_SELECT_IMAGE);
                */
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        testTextView.setText("resultCode ="+resultCode+" requestCode = " + requestCode+ "RESULT_OK = "+ RESULT_OK);
        if (resultCode == RESULT_OK) {
           // if (requestCode == IMAGE_GALLERY_REQUEST) {
            if (requestCode == ACTIVITY_SELECT_IMAGE) {
                Uri imageUri = data.getData();

                InputStream inputStream;
                try {
                    testTextView.setText("trying1....");
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap imageToMycon = BitmapFactory.decodeStream(inputStream);
                    pictureImageView.setImageBitmap(imageToMycon);
                    testTextView.setText("trying2....");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    testTextView.setText("catch");

                    // Toast.makeText(this,"Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateMycons Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.mycons_web.mycons/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CreateMycons Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.mycons_web.mycons/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

}
