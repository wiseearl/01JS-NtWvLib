package com.neonlight.webview3;


import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neonlight.util.ObjectWebView;


public class MainActivity extends AppCompatActivity {

	//[WebView-CheckOnline]Var--Start
    WebView wv=null;
    ProgressBar pb=null;
    TextView tvMsg=null;
    public static boolean isOnline = false;
    ObjectWebView owvNormal=null;    
    //String strUrl="http://taqm.epa.gov.tw/taqm/webcam.ashx?site=408&type=1";
    String strUrl="https://nlapp.blogspot.tw/";
    //[WebView-CheckOnline]Var--End
    
    private final static String TAG = "MainActivity";
    //String strUrl="http://nlapp.blogspot.tw/";
    
    //String strUrl="https://www.google.com.tw";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        
		//[WebView-CheckOnline]findViews--Start
        wv = (WebView) findViewById(R.id.wv);


        pb = (ProgressBar) findViewById(R.id.pb); 
        tvMsg=(TextView)findViewById(R.id.TvMsg);       
		//[WebView-CheckOnline]findViews--End
		
		//[WebView-CheckOnline]initValues--Start
        wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);



        owvNormal=new ObjectWebView(this,wv,pb,tvMsg,strUrl);
        owvNormal.webViewLoagPage();

        /*
        tvMsg.setVisibility(TextView.GONE);
        wv.setVisibility(WebView.VISIBLE);
        if(isOnline){
            tvMsg.setVisibility(TextView.GONE);
            wv.setVisibility(WebView.VISIBLE);
        }else{
            tvMsg.setVisibility(TextView.VISIBLE);
            wv.setVisibility(WebView.GONE);
        }
        */
		//[WebView-CheckOnline]initValues--End



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if(wv.canGoBack()){   // 如果 WebView 有上一頁
            wv.goBack();	  // 回上一頁
            return;
        }
        super.onBackPressed();  //呼叫父類別的同名方法, 以執行預設動作(結束程式)
    }


}

