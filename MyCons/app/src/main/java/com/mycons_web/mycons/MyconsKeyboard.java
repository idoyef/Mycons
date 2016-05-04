package com.mycons_web.mycons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by on 25/04/2016.
 */
public class MyconsKeyboard extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycons_keyboard);

        ImageButton myconImageButtonNumber1 = (ImageButton) findViewById(R.id.MyconButtonNumber1);

        myconImageButtonNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

            }
        });
    }
    }
