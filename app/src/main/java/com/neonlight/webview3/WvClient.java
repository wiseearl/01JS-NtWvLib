package com.neonlight.webview3;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by User on 2017/4/26.
 */

public class WvClient extends WebViewClient
{

/*
    @Override
    @TargetApi(Build.VERSION_CODES.M)
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        final Uri uri = request.getUrl();
        handleError(view, error.getErrorCode(), error.getDescription().toString(), uri);
    }

    @SuppressWarnings("deprecation")
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        final Uri uri = Uri.parse(failingUrl);
        handleError(view, errorCode, description, uri);
    }

    private void handleError(WebView view, int errorCode, String description, final Uri uri) {
        final String host = uri.getHost();// e.g. "google.com"
        final String scheme = uri.getScheme();// e.g. "https"
        // TODO: logic
    }
    */

    final String js = "javascript: var allInputs = document.getElementsByTagName('input'); for (var i = 0, len = allInputs.length; i < len; ++i) { allInputs[i].readOnly = true;}";

    @Override
    public void onPageFinished(WebView view, String url) {
        if (Build.VERSION.SDK_INT >= 19) {
            view.evaluateJavascript(js, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) { }
            });
        } else {
            view.loadUrl(js);
        }
    }


    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
        handler.proceed();
        // Ignore SSL certificate errors
    }
}
