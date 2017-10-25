package com.example.prasanth.twitterapplication.searchLayoutPackage;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prasanth.twitterapplication.R;

/**
 * Created by Prasanth on 10/12/2017.
 */

public class SearchLayoutActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText searchText;
    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        searchText = (EditText) findViewById(R.id.enterTextBox);
        VectorDrawableCompat drawableCompatLeft = VectorDrawableCompat.create(this.getResources(), R.drawable.ic_search_black, searchText.getContext().getTheme());
        VectorDrawableCompat drawableCompatRight = VectorDrawableCompat.create(this.getResources(), R.drawable.ic_cancel_black, searchText.getContext().getTheme());
        searchText.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableCompatLeft, null, drawableCompatRight, null);
        searchText.setOnClickListener(this);
        searchText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (searchText.getRight() - searchText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        searchText.getText().clear();
                        return true;
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.enterTextBox:
                Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
