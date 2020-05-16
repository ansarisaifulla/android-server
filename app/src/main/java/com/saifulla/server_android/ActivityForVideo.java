package com.saifulla.server_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jarvanmo.exoplayerview.media.SimpleMediaSource;
import com.jarvanmo.exoplayerview.ui.ExoVideoView;

import static com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_LANDSCAPE;
import static com.jarvanmo.exoplayerview.orientation.OnOrientationChangedListener.SENSOR_PORTRAIT;

public class ActivityForVideo extends AppCompatActivity {

    private ExoVideoView videoView;
    private View contentView;

    private TextView TitlVid, VidDesc;

    private ScrollView scrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_video);
        getSupportActionBar().hide();

        contentView = findViewById(R.id.activity_main);

        videoView = findViewById(R.id.videoView);

        TitlVid = findViewById(R.id.VideoTitle);

        VidDesc = findViewById(R.id.description);

        scrl = findViewById(R.id.scrolvw);

        Intent intent = getIntent();

        String URLVideo = intent.getStringExtra("url");

        String TitleVideo = intent.getStringExtra("title");

        String DescVideo = intent.getStringExtra("desc");

        TitlVid.setText(TitleVideo);
        VidDesc.setText(DescVideo);


        videoView.setBackListener((view, isPortrait) -> {
            if (isPortrait) {
                finish();
            }
            return false;
        });

        videoView.setOrientationListener(orientation -> {
            if (orientation == SENSOR_PORTRAIT) {
                changeToPortrait();
                scrl.setVisibility(View.VISIBLE);
            } else if (orientation == SENSOR_LANDSCAPE) {
                changeToLandscape();
                scrl.setVisibility(View.GONE);
            }
        });


        SimpleMediaSource mediaSource = new SimpleMediaSource(URLVideo);
        mediaSource.setDisplayName("VideoPlaying");

        videoView.play(mediaSource, false);
    }

    private void changeToPortrait() {

        WindowManager.LayoutParams attr = getWindow().getAttributes();
        Window window = getWindow();
        window.setAttributes(attr);
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    private void changeToLandscape() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        Window window = getWindow();
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > 23) {
            videoView.resume();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if ((Build.VERSION.SDK_INT <= 23)) {
            videoView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT <= 23) {
            videoView.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT > 23) {
            videoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.releasePlayer();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return videoView.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
