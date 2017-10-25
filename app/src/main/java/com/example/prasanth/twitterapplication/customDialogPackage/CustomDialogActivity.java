package com.example.prasanth.twitterapplication.customDialogPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.prasanth.twitterapplication.R;

public class CustomDialogActivity extends AppCompatActivity implements View.OnClickListener, IntentInterface {

    private Button showCustomDialogBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        showCustomDialogBtn = (Button) findViewById(R.id.showDialogButton);
        showCustomDialogBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showDialogButton:
                DialogActivity dialogActivity = new DialogActivity(this, this);
                dialogActivity.show();
                break;
            default:
                break;
        }
    }


    @Override
    public void callIntent() {
        Intent intent = new Intent(CustomDialogActivity.this, MainImageNavigationActivity.class);
        startActivity(intent);
        finish();
    }
}
