package com.neonlight.webview3;


import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.neonlight.ntwv.NtWv;


public class MainActivity extends AppCompatActivity {

    //NtWv declare Object before onCreate
    NtWv nw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		initialComponent();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {


        //NtWv Go back page onBackPressed
        nw.ntwvBackPressed();

        super.onBackPressed();
    }

    private void initialComponent(){

        //NtWv Load Page ex1
        nw =new NtWv(
                (WebView) findViewById(R.id.wv),
                "https://play.google.com/store/apps/details?id=com.neonlight.qrnote",
                true);

        /*
        //NtWv Load Page ex2
        nw =new NtWv(MainActivity.this,
                (WebView) findViewById(R.id.wv),
                "https://play.google.com/store/apps/details?id=com.neonlight.qrnote",
                (ProgressBar) findViewById(R.id.pb),
                (TextView) findViewById(R.id.TvMsg),true);

         */

    }





}

