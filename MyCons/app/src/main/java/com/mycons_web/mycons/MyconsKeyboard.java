package com.mycons_web.mycons;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;

/**
 * Created by on 25/04/2016.
 */
public class MyconsKeyboard extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycons_keyboard);

        final ImageButton myconImageButtonNumber1 = (ImageButton) findViewById(R.id.MyconButtonNumber1);
        final ImageButton myconImageButtonNumber2 = (ImageButton) findViewById(R.id.MyconButtonNumber2);

        myconImageButtonNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD

                /*SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(msgTxt.getText());
                scsTxt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.nicemycon, 0, 0, 0);
                msgTxt.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.nicemycon,0, 0, 0);
*/

/*
                Intent backToSendMessageIntent = new Intent(MyconsKeyboard.this,SendMessage.class);
                startActivity(backToSendMessageIntent);
*/
                finish();
//                putMyconInTextMsg(myconImageButtonNumber1);

=======
                ImageButtonClicked(myconImageButtonNumber1);
>>>>>>> f2ba031ee4c9797aacde5766b17704563717158c
            }
        });
        myconImageButtonNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButtonClicked(myconImageButtonNumber2);
            }
        });

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void ImageButtonClicked(final ImageButton TheClickedImageButton)
    {
       // Uri result = Uri.fromFile(new File("C:\\Users\\Ido\\Desktop\\ic_facebook.png"));
        Bitmap currentMycon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        Intent returnIntent = new Intent();
//        returnIntent.setData(result);

        returnIntent.putExtra("result", currentMycon);

        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
