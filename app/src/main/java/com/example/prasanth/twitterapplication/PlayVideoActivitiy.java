package com.example.prasanth.twitterapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class PlayVideoActivitiy extends AppCompatActivity implements View.OnClickListener {

    private VideoView videoView;
    private Button playButton;
    private TextView videoPlayerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video_activitiy);
        playButton = (Button) findViewById(R.id.playButton);
        videoPlayerStatus = (TextView) findViewById(R.id.videoPlayerStatus);

        playButton.setOnClickListener(this);
        videoView = (VideoView) findViewById(R.id.videoPlayer);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoPlayerStatus.setText("Video Ended");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 2) {
                Uri uri = data.getData();
                videoView.setVideoURI(uri);
                videoView.start();
                videoPlayerStatus.setText("Video Started");
            }
        }
    }

}
