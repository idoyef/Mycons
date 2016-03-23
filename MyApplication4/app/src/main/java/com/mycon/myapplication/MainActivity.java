package com.mycon.myapplication;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.IconMarginSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Bitmap;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MyconsKeyboard.KeyboardListener{

    EditText msgTxt;
    TextView scsTxt;
    ImageView iv;

    @Override
    public void PutMyconInTextView(ImageButton imageButton) {
        scsTxt.setText("scs!");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        msgTxt = (EditText) findViewById(R.id.msgTextBox);
        scsTxt = (TextView) findViewById(R.id.textViewToChange);
        iv = (ImageView)findViewById(R.id.imageTxt);
        Button myconBtn = (Button) findViewById(R.id.myconButton);
        Button sndBtn = (Button) findViewById(R.id.SendButton);

        myconBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                scsTxt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.nicemycon, 0, 0, 0);

            }
        });


        sndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                scsTxt.setDrawingCacheEnabled(true); // Enable drawing cache before calling the getDrawingCache() method
                scsTxt.setTextColor(Color.BLACK);
                scsTxt.setBackgroundColor(Color.TRANSPARENT);

                Bitmap msgImage = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(msgImage);
                scsTxt.layout(10, 10, 800, 800);
                scsTxt.draw(c);

                iv.setBackgroundColor(Color.WHITE);
                iv.setMaxHeight(800);
                iv.setMaxWidth(800);
                iv.setImageBitmap(msgImage);

                try
                {
                   // Image imageFromText =
                   // IconMarginSpan icon = new IconMarginSpan(msgImage);
                    File file =new File("//storage//emulated/0//Mycons//", String.format(System.currentTimeMillis()+".jpg"));
                    FileOutputStream ostream1 = new FileOutputStream(file);
                    iv.draw(c);
                    scsTxt.draw(c);
                    boolean scs = msgImage.compress(Bitmap.CompressFormat.JPEG, 99, ostream1);
                    ostream1.close();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

                msgTxt.setText("");
                scsTxt.setText("");
                scsTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        });

        msgTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (msgTxt.getText().toString().equals("Enter your msg...")) {
                    msgTxt.setText("");
                    scsTxt.setText("");
                }

            }
        });
        msgTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (msgTxt.getText().toString().equals("Enter your msg...")) {
                    msgTxt.setText("");
                }
                scsTxt.setText(msgTxt.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Android internals have been modified to store images in the media folder with
    // the correct date meta data

    /**
     * //  * A copy of the Android internals  insertImage method, this method populates the
     * // * meta data with DATE_ADDED and DATE_TAKEN. This fixes a common problem where media
     * // * that is inserted manually gets saved at the end of the gallery (because date is not populated).
     * //* @see android.provider.MediaStore.Images.Media#insertImage(ContentResolver, Bitmap, String, String)
     * //
     */
/*
    public static final String insertImage(ContentResolver cr,
                                           Bitmap source,
                                           String title,
                                           String description) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, description);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());

        Uri url = null;
        String stringUrl = null;    /* value to be returned */
/*
        try {
            url = cr.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

            if (source != null) {
                OutputStream imageOut = cr.openOutputStream(url);
                try {
                    source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut);
                } finally {
                    imageOut.close();
                }
/*
                    long id = ContentUris.parseId(url);
                    // Wait until MINI_KIND thumbnail is generated.
                    Bitmap miniThumb = MediaStore.Images.Thumbnails.getThumbnail(cr, id, MediaStore.Images.Thumbnails.MINI_KIND, null);
                    // This is for backward compatibility.
                    storeThumbnail(cr, miniThumb, id, 50F, 50F, MediaStore.Images.Thumbnails.MICRO_KIND);
                    */
       /*     } else {
                cr.delete(url, null, null);
                url = null;
            }
        } catch (Exception e) {
            if (url != null) {
                cr.delete(url, null, null);
                url = null;
            }
        }

        if (url != null) {
            stringUrl = url.toString();
        }

        return stringUrl;
    }*/


}