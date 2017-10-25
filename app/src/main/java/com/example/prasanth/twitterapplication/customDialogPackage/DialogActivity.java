package com.example.prasanth.twitterapplication.customDialogPackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.prasanth.twitterapplication.R;

public class DialogActivity extends Dialog implements View.OnClickListener {


    private ImageView mainImage;
    private ImageButton cancelDialogImage;
    private IntentInterface intentInterface;


    private Context context;

    public DialogActivity(Context context, IntentInterface intentInterface) {
        super(context);
        this.context = context;
        this.intentInterface = intentInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);
        mainImage = (ImageView) findViewById(R.id.mainImage);
        cancelDialogImage = (ImageButton) findViewById(R.id.cancelDialog);

        mainImage.setOnClickListener(this);
        cancelDialogImage.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelDialog:
                dismiss();
                break;
            case R.id.mainImage:
                intentInterface.callIntent();
                /*Intent intent=new Intent(activity,MainImageNavigationActivity.class);
                startActivity(intent);
                finish();
                break;*/
            default:
                break;
        }
    }
}
