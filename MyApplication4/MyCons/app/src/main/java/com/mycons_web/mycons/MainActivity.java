package com.mycons_web.mycons;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    TextView WelcomeTextView;
    TextView WhatToDoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        WelcomeTextView = (TextView) findViewById(R.id.WelcomeTextView);
        WhatToDoTextView = (TextView) findViewById(R.id.WhatDoTextView);

        Button createMyconsButton = (Button) findViewById(R.id.createMyconsButton);
        Button sendByMyconsButton = (Button) findViewById(R.id.sendByMyconsButton);
        Button sendByOtherButton = (Button) findViewById(R.id.sendByOtherButton);

        createMyconsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeTextView.setText("create clicked!");
                Intent createMyconsIntent = new Intent(MainActivity.this, CreateMycons.class);
                startActivity(createMyconsIntent);
            }
        });


        sendByMyconsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeTextView.setText("MyCons clicked!");
            }
        });

        sendByOtherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WelcomeTextView.setText("Other clicked!");
                Intent sendMsgIntent = new Intent(MainActivity.this, SendMessage.class);
                startActivity(sendMsgIntent);
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
}
