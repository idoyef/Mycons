package com.mycons_web.mycons;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by  on 25/04/2016.
 */
public class SendMessage  extends Activity {

    EditText msgTxt;
    TextView scsTxt;
    ImageView iv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message);

        msgTxt = (EditText) findViewById(R.id.msgTextBox);
        scsTxt = (TextView) findViewById(R.id.textViewToChange);
        iv = (ImageView)findViewById(R.id.imageTxt);
        Button myconBtn = (Button) findViewById(R.id.myconButton);
        Button sndBtn = (Button) findViewById(R.id.SendButton);

        myconBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                /*SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(msgTxt.getText());
                scsTxt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.nicemycon, 0, 0, 0);
                msgTxt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.nicemycon,0, 0, 0);
*/

                Intent openMyconsKeyboardIntent = new Intent(SendMessage.this, MyconsKeyboard.class);
                startActivity(openMyconsKeyboardIntent);

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
                    File file = new File("//storage//emulated//0//Mycons//", String.format(System.currentTimeMillis()+".jpg"));
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
                msgTxt.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
}
