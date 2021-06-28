package com.neonlight.ntwv;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

/** Usage Example

     //NtWv declare Object before onCreate
     NtWv nw;

     //NtWv Load Page ex1
     nw =new NtWv(MainActivity.this,
     (WebView) findViewById(R.id.wv),true);

     //NtWv Load Page ex2
     nw =new NtWv(MainActivity.this,
     (WebView) findViewById(R.id.wv),
     "https://play.google.com/store/apps/details?id=com.neonlight.qrnote",
     (ProgressBar) findViewById(R.id.pb),
     (TextView) findViewById(R.id.TvMsg),true);

     //NtWv Go back page onBackPressed
     nw.ntwvBackPressed();

 */
public class NtWv {

    public WebView webView;
    String strUrlSource;
    ProgressBar pbSource;
    TextView tvMsg;
    int intObjectType=0;//1.WebView,2.Add ProgressBar,3.Add Offline MessageText
    boolean isOnline=false;
    Context context;
    int intDelayed=500;
    boolean isLoadPage = true;

    //Type 1
    public NtWv(WebView wvSourceInput, String strUrlSourceInput){
        this.webView =wvSourceInput;
        this.strUrlSource=strUrlSourceInput;
        intObjectType=1;
    }

    //Type 1L
    public NtWv(WebView wvSourceInput, String strUrlSourceInput,boolean isLoadPage){
        this.webView =wvSourceInput;
        this.strUrlSource=strUrlSourceInput;
        this.isLoadPage=isLoadPage;
        intObjectType=1;
        if(isLoadPage){
            loagPage();
        }
    }

    //Type 2
    public NtWv(WebView wvSourceInput, String strUrlSourceInput,
                ProgressBar pbSrourceInput){
        this.webView =wvSourceInput;
        this.pbSource=pbSrourceInput;
        this.strUrlSource=strUrlSourceInput;
        intObjectType=2;
    }

    //Type 2L
    public NtWv(WebView wvSourceInput, String strUrlSourceInput,
                ProgressBar pbSrourceInput, Boolean isLoadPage){
        this.webView =wvSourceInput;
        this.pbSource=pbSrourceInput;
        this.strUrlSource=strUrlSourceInput;
        this.isLoadPage = isLoadPage;
        intObjectType=2;

        if(isLoadPage){
            loagPage();
        }
    }

    //Type 3
    public NtWv(Context context,
                WebView wvSourceInput, String strUrlSourceInput,
                ProgressBar pbSrourceInput,
                TextView tvMsgInput){
        this.context =context;
        webView =wvSourceInput;
        pbSource=pbSrourceInput;
        tvMsg=tvMsgInput;
        strUrlSource=strUrlSourceInput;
        intObjectType=3;
    }

    //Type 3L
    public NtWv(Context context,
                WebView wvSourceInput, String strUrlSourceInput,
                ProgressBar pbSrourceInput,
                TextView tvMsgInput,Boolean isLoadPage){
        this.context =context;
        this.webView =wvSourceInput;
        this.pbSource=pbSrourceInput;
        this.tvMsg=tvMsgInput;
        this.strUrlSource=strUrlSourceInput;
        this.isLoadPage = isLoadPage;
        intObjectType=3;

        if(isLoadPage){
            loagPage();
        }
    }


    //Type 4L
    public NtWv(Context context,
                WebView wvSourceInput, String strUrlSourceInput,
                ProgressBar pbSrourceInput,
                TextView tvMsgInput,
                Switch swCam,
                Switch swMicMute,
                Boolean isLoadPage){
        this.context =context;
        this.webView =wvSourceInput;
        this.pbSource=pbSrourceInput;
        this.tvMsg=tvMsgInput;
        this.strUrlSource=strUrlSourceInput;
        this.isLoadPage = isLoadPage;
        intObjectType=4;

        if(isLoadPage){
            loagPage();
        }
    }

    public void loagPage(){
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
            case 4:
                //Case: Context + [WebView + Url] + ProgressBar + offline message TextView
                //PS: Add Context,TextView
                webViewLoadPageType4();
                break;
        }
    }


    private void webViewLoadPageType1(){

        webView.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
        webView.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
        webView.invokeZoomPicker();	                        // Show Zoom Tool
        webView.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object


        webView.getSettings().setLoadWithOverviewMode(true);//Scale XY
        webView.getSettings().setUseWideViewPort(true);//Scale XY


        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(strUrlSource);
    }


    private void webViewLoadPageType2(){

        webView.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
        webView.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
        webView.invokeZoomPicker();	                        // Show Zoom Tool
        webView.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object

        webView.getSettings().setLoadWithOverviewMode(true);//Scale XY
        webView.getSettings().setUseWideViewPort(true);//Scale XY

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pbSource.setProgress(progress);       //set up progress function
                pbSource.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //let progressBar show or dispear by progress
            }
        });

        webView.loadUrl(strUrlSource);   // Link to the website,can only the domain name, without page name
    }


    private void webViewLoadPageType3(){

        updateIsOnline();

        if(isOnline){
            tvMsg.setVisibility(TextView.GONE);
            webView.setVisibility(WebView.VISIBLE);


            webView.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
            webView.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
            webView.invokeZoomPicker();	                        // Show Zoom Tool
            webView.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object

            webView.getSettings().setLoadWithOverviewMode(true);//Scale XY
            webView.getSettings().setUseWideViewPort(true);//Scale XY

            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    pbSource.setProgress(progress);       //set up progress function
                    pbSource.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //let progressBar show or dispear by progress
                }
            });

            webView.loadUrl(strUrlSource);   // Link to the website,can only the domain name, without page name

        }else{
            tvMsg.setVisibility(TextView.VISIBLE);
            webView.setVisibility(WebView.GONE);
        }

    }

    private void webViewLoadPageType4(){

        updateIsOnline();

        if(isOnline){
            tvMsg.setVisibility(TextView.GONE);
            webView.setVisibility(WebView.VISIBLE);


            webView.getSettings().setJavaScriptEnabled(true);	// Use JavaScript
            webView.getSettings().setBuiltInZoomControls(true);	// Use Zoom Function
            webView.invokeZoomPicker();	                        // Show Zoom Tool
            webView.setWebViewClient(new WebViewClient());		// Build and use WebViewClient Object

            webView.getSettings().setLoadWithOverviewMode(true);//Scale XY
            webView.getSettings().setUseWideViewPort(true);//Scale XY

            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    pbSource.setProgress(progress);       //set up progress function
                    pbSource.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //let progressBar show or dispear by progress
                }
            });

            webView.loadUrl(strUrlSource);   // Link to the website,can only the domain name, without page name

        }else{
            tvMsg.setVisibility(TextView.VISIBLE);
            webView.setVisibility(WebView.GONE);
        }

    }

    //[WebViewCheckOnline]Function inActivity
    public void updateIsOnline(){
        isOnline= NetworkCheck.checkNetwork(context);
    }

    public void ntwvBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
    }

}
