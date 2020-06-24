package com.neonlight.webview3;


import android.content.res.Configuration;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.util.WvLib.WvLib;
import com.util.WvLib.WvLib_ProjObj;


public class MainActivity extends AppCompatActivity {

    WvLib_ProjObj wlpo ;
    WvLib wl =null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		initialComponent();

        wl.webViewLoagPage();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(wlpo.wv.canGoBack()){   // 如果 WebView 有上一頁
            wlpo.wv.goBack();	  // 回上一頁
            return;
        }
        super.onBackPressed();  //呼叫父類別的同名方法, 以執行預設動作(結束程式)
    }

    private void initialComponent(){

        wlpo=new WvLib_ProjObj();
        wlpo.wv = (WebView) findViewById(R.id.wv);
        wlpo.pb = (ProgressBar) findViewById(R.id.pb);
        wlpo.tvMsg=(TextView)findViewById(R.id.TvMsg);

        wlpo.wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wl =new WvLib(MainActivity.this,
                wlpo.wv,wlpo.strUrl,wlpo.pb,wlpo.tvMsg);
    }





}

