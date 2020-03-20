package com.neonlight.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by User on 2016/6/28.
 * Source Project：WebView3-CheckOnline
 * Ver:1.2
 */
public class ObjectWebView {

    WebView wvSource;
    String strUrlSource;
    ProgressBar pbSource;
    TextView tvMsg;
    int intObjectType=0;//1.WebView,2.WebView,ProgressBar
    boolean isOnline=false;
    Activity actSource;
    int intDelayed=500;


    public ObjectWebView(WebView wvSourceInput,
                         String strUrlSourceInput){
        wvSource=wvSourceInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=1;
    }

    public ObjectWebView(WebView wvSourceInput,ProgressBar pbSrourceInput,
                         String strUrlSourceInput){
        wvSource=wvSourceInput;
        pbSource=pbSrourceInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=2;
    }

    public ObjectWebView(Activity actSrourceInput,
                         WebView wvSourceInput,ProgressBar pbSrourceInput,TextView tvMsgInput,
                         String strUrlSourceInput){
        actSource=actSrourceInput;
        wvSource=wvSourceInput;
        pbSource=pbSrourceInput;
        tvMsg=tvMsgInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=3;
    }
    public void webViewLoagPage(){
        switch (intObjectType){
            case 1:
                webViewLoadPageType1();
                break;
            case 2:
                webViewLoadPageType2();
                break;
            case 3:
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

    private void webViewLoadPageType2_bk(){
/*
        wvSource.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
        wvSource.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
        wvSource.invokeZoomPicker();	                        // Show Zoom Tool
        wvSource.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object


        wvSource.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pbSource.setProgress(progress);       //set up progress function
                pbSource.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //let progressBar show or dispear by progress
            }
        });

*/
        /*
        WebSettings webSettings = wvSource.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationDatabasePath(actSrource.getFilesDir().getPath());
        webSettings.setGeolocationEnabled(true);

        wvSource.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });
*/

        String TAG="LOG_WEBVIEW";
        WebSettings ws = wvSource.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        ws.setGeolocationEnabled(true);

/*
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.ECLAIR) {
            try {
                Log.d(TAG, "Enabling HTML5-Features");
                Method m1 = WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
                m1.invoke(ws, Boolean.TRUE);

                Method m2 = WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE});
                m2.invoke(ws, Boolean.TRUE);

                Method m3 = WebSettings.class.getMethod("setDatabasePath", new Class[]{String.class});
                m3.invoke(ws, "/data/data/" + actSrource.getPackageName() + "/databases/");

                Method m4 = WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE});
                m4.invoke(ws, 1024 * 1024 * 8);

                Method m5 = WebSettings.class.getMethod("setAppCachePath", new Class[]{String.class});
                m5.invoke(ws, "/data/data/" + actSrource.getPackageName() + "/cache/");

                Method m6 = WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE});
                m6.invoke(ws, Boolean.TRUE);

                Log.d(TAG, "Enabled HTML5-Features");

                wvSource.loadUrl(strUrlSource);   // Link to the website,can only the domain name, without page name



            } catch (NoSuchMethodException e) {
                Log.e(TAG, "Reflection fail", e);
            } catch (InvocationTargetException e) {
                Log.e(TAG, "Reflection fail", e);
            } catch (IllegalAccessException e) {
                Log.e(TAG, "Reflection fail", e);
            }catch (android.content.ActivityNotFoundException e){
                Log.e(TAG, "Reflection fail", e);
            }
        }

*/
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
        ConnectivityManager CM = (ConnectivityManager)actSource.getSystemService(Context.CONNECTIVITY_SERVICE);
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
//Version Note:
//1.0 First Version
//1.1 Add set Url function,Add Scale XY function
//1.2 Add check online function