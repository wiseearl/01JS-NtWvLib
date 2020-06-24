package com.util.WvLib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by User on 2016/6/28.
 * Source Project：WvCheckOnline
 * Ver:1.3
 */
public class WvLib {

    WebView wvSource;
    String strUrlSource;
    ProgressBar pbSource;
    TextView tvMsg;
    int intObjectType=0;//1.WebView,2.Add ProgressBar,3.Add Offline MessageText
    boolean isOnline=false;
    Context context;
    int intDelayed=500;

    public WvLib(WebView wvSourceInput,String strUrlSourceInput){
        wvSource=wvSourceInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=1;
    }

    public WvLib(WebView wvSourceInput,String strUrlSourceInput,
                 ProgressBar pbSrourceInput){
        wvSource=wvSourceInput;
        pbSource=pbSrourceInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=2;
    }

    public WvLib(Context context,
                 WebView wvSourceInput,String strUrlSourceInput,
                 ProgressBar pbSrourceInput,
                 TextView tvMsgInput){
        this.context =context;
        wvSource=wvSourceInput;
        pbSource=pbSrourceInput;
        tvMsg=tvMsgInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=3;
    }
    public void webViewLoagPage(){
        switch (intObjectType){
            case 1:
                //Case: [WebView + Url]
                webViewLoadPageType1();
                break;
            case 2:
                //Case: [WebView + Url] + ProgressBar
                //PS: Add ProgressBar
                webViewLoadPageType2();
                break;
            case 3:
                //Case: Context + [WebView + Url] + ProgressBar + offline message TextView
                //PS: Add Context,TextView
                webViewLoadPageType3();
                break;
        }
    }


    public void setUrl(String strUrlInput){
        this.strUrlSource=strUrlInput;
    }


    private void webViewLoadPageType1(){

        wvSource.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
        wvSource.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
        wvSource.invokeZoomPicker();	                        // Show Zoom Tool
        wvSource.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object


        wvSource.getSettings().setLoadWithOverviewMode(true);//Scale XY
        wvSource.getSettings().setUseWideViewPort(true);//Scale XY


        wvSource.setWebChromeClient(new WebChromeClient());
        wvSource.loadUrl(strUrlSource);
    }


    private void webViewLoadPageType2(){

        wvSource.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
        wvSource.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
        wvSource.invokeZoomPicker();	                        // Show Zoom Tool
        wvSource.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object

        wvSource.getSettings().setLoadWithOverviewMode(true);//Scale XY
        wvSource.getSettings().setUseWideViewPort(true);//Scale XY

        wvSource.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pbSource.setProgress(progress);       //set up progress function
                pbSource.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //let progressBar show or dispear by progress
            }
        });

        wvSource.loadUrl(strUrlSource);   // Link to the website,can only the domain name, without page name
    }


    private void webViewLoadPageType3(){

        updateIsOnline();

        if(isOnline){
            tvMsg.setVisibility(TextView.GONE);
            wvSource.setVisibility(WebView.VISIBLE);


            wvSource.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
            wvSource.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
            wvSource.invokeZoomPicker();	                        // Show Zoom Tool
            wvSource.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object

            wvSource.getSettings().setLoadWithOverviewMode(true);//Scale XY
            wvSource.getSettings().setUseWideViewPort(true);//Scale XY

            wvSource.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    pbSource.setProgress(progress);       //set up progress function
                    pbSource.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //let progressBar show or dispear by progress
                }
            });

            wvSource.loadUrl(strUrlSource);   // Link to the website,can only the domain name, without page name

        }else{
            tvMsg.setVisibility(TextView.VISIBLE);
            wvSource.setVisibility(WebView.GONE);
        }
    }


    //[WebViewCheckOnline]Function inActivity
    public void updateIsOnline(){
        isOnline= checkNetworkConnected();
    }

    //[WebViewCheckOnline]Function inActivity
    public boolean checkNetworkConnected() {
        boolean result = false;
        ConnectivityManager CM = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (CM == null) {
            result = false;
        } else {
            NetworkInfo info = CM.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (!info.isAvailable()) {
                    result = false;
                } else {
                    result = true;
                }
                //Log.d(TAG, "[目前連線方式]" + info.getTypeName());
                //Log.d(TAG, "[目前連線狀態]"+info.getState());
                //Log.d(TAG, "[目前網路是否可使用]"+info.isAvailable());
                //Log.d(TAG, "[網路是否已連接]"+info.isConnected());
                //Log.d(TAG, "[網路是否已連接 或 連線中]"+info.isConnectedOrConnecting());
                //Log.d(TAG, "[網路目前是否有問題 ]"+info.isFailover());
                //Log.d(TAG, "[網路目前是否在漫遊中]" + info.isRoaming());
            }
        }
        return result;
    }


}
