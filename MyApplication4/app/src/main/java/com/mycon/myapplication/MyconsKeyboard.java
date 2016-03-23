package com.mycon.myapplication;

/**
 * Created by Mor on 20/03/2016.
 */

import android.content.Context;
import android.graphics.Matrix;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;

public class MyconsKeyboard extends Fragment {


    private static ImageButton image_button;
    KeyboardListener keyboardListener;

    public interface KeyboardListener{
        public void PutMyconInTextView(ImageButton imageButton);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            keyboardListener = (KeyboardListener) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString());
        }
    }

    /*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            keyboardListener = (KeyboardListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString());
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mycons_keyboard,container,false);
        image_button = (ImageButton) view.findViewById(R.id.imageButton1);

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonClicked(v);
            }
        });
        return view;
    }

    public void ButtonClicked(View view)
    {
        keyboardListener.PutMyconInTextView(image_button);
    }
}
