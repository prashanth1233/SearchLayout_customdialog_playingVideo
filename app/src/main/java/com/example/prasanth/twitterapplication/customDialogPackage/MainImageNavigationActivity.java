package com.example.prasanth.twitterapplication.customDialogPackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.prasanth.twitterapplication.R;

public class MainImageNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image_navigation);
        Toast.makeText(this,"You are into other activity",Toast.LENGTH_LONG).show();
    }
}
