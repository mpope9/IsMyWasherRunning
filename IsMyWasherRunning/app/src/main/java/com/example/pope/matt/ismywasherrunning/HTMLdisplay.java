package com.example.pope.matt.ismywasherrunning;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import android.os.Handler;
import android.widget.LinearLayout;

import java.io.IOException;


public class HTMLdisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmldisplay);
        SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://10.0.0.141");
        // John Cena SoundClip: www.myinstants.com/media/sounds/and-his-name-is-john-cena-1.mp3
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }

    private void refreshContent() {
        final SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        final WebView myWebView = (WebView) findViewById(R.id.webview);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myWebView.reload();
                mySwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebView clicker = (WebView) findViewById(R.id.webview);
        clicker.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String url = "http://www.myinstants.com/media/sounds/and-his-name-is-john-cena-1.mp3";
                try {
                    MediaPlayer mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                    mediaPlayer.start();
                    return true;
                }
                catch(IOException e) {
                    return false;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_htmldisplay, menu);
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
