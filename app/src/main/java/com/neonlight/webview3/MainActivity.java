package com.neonlight.webview3;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.webkit.CookieManager;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;

import com.neonlight.ntwv.NtWv;


public class MainActivity extends AppCompatActivity
        implements OnRequestPermissionsResultCallback {

    Switch swCam;
    Switch swMicMute;

    WebView wv;
    WebSettings settings;
    //NtWv declare Object before onCreate
    NtWv nw;

    Boolean isMute = false;

    CameraManager cameraManager ;

    PermissionCheck pcM;
    private String[] arrPermissions=new String[]{Manifest.permission.CAMERA,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		initialComponent();
        pcM=new PermissionCheck(MainActivity.this,arrPermissions,true);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        swCam = findViewById(R.id.swCam);
        swMicMute = findViewById(R.id.swMicMute);

        swCam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    //cameraManager.openCamera(camaraIdRear,statteCallbackFront,backgroundHandler);

                }else{
                    //cameraManager.openCamera(camaraIdFront,statteCallbackFront,backgroundHandler);
                }
                Toast.makeText(MainActivity.this,"Cam Switch:"+b,Toast.LENGTH_SHORT).show();
            }
        });

        swMicMute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    mute(MainActivity.this);
                }else{
                    unmute(MainActivity.this);
                }
                Toast.makeText(MainActivity.this,"Mute Switch:"+b,Toast.LENGTH_SHORT).show();
            }
        });

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

        int intTestTypeNo = 4;

        switch (intTestTypeNo){
            case 1:

                //NtWv Load Page ex1
                nw =new NtWv(
                        (WebView) findViewById(R.id.wv),
                        "https://play.google.com/store/apps/details?id=com.neonlight.qrnote",
                        true);
                break;
            case 2:
                //NtWv Type 2L
                nw =new NtWv((WebView) findViewById(R.id.wv),
                        "https://play.google.com/store/apps/details?id=com.neonlight.qrnote",
                        (ProgressBar) findViewById(R.id.pb),true);
                        break;
            case 3:
                //NtWv Type 3L
                nw =new NtWv(MainActivity.this,
                        (WebView) findViewById(R.id.wv),
                        "https://appr.tc/r/testmds",
                        (ProgressBar) findViewById(R.id.pb),
                        (TextView) findViewById(R.id.TvMsg),true);
                break;
            case 4:
                wv = findViewById(R.id.wv);
                loadCustom();
                /*
                //NtWv Type 4L
                nw =new NtWv(MainActivity.this,
                        (WebView) findViewById(R.id.wv),
                        "https://appr.tc/r/testmds",
                        (ProgressBar) findViewById(R.id.pb),
                        (TextView) findViewById(R.id.TvMsg),
                        (Switch) findViewById(R.id.swCam),
                        (Switch) findViewById(R.id.swMicMute),
                        true);

                 */
                break;
        }


    }



    private void loadCustom(){
        setUpWebViewDefaults(wv);

        //wv.loadUrl("https://www.youtube.com");
        wv.loadUrl("https://appr.tc/r/mdstest4");
        wv.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }

        });
    }


    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == 0) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        } else if (requestCode == 1) {

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }




    /**
     * Convenience method to set some generic defaults for a
     * given WebView
     *
     * @param webView
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setUpWebViewDefaults(WebView webView) {
        settings = webView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        // Allow use of Local Storage
        settings.setDomStorageEnabled(true);

        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setAllowContentAccess(true);
        settings.setSupportMultipleWindows(true);


        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);
        settings.setAllowFileAccess(true);
        settings.setSupportZoom(true);
        //settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setMediaPlaybackRequiresUserGesture(false);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            // Hide the zoom controls for HONEYCOMB+
            settings.setDisplayZoomControls(false);
        }

        // Enable remote debugging via chrome://inspect
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(new WvClient());
        //webView.setWebViewClient(getWebViewClient());

        // AppRTC requires third party cookies to work
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptThirdPartyCookies(wv, true);

    }
    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static void mute(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int mute_volume = 0;
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mute_volume, 0);
    }

    public static void unmute(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int unmute_volume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, unmute_volume, 0);
    }


}

