package com.neonlight.webview3;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

/**
 * Created by User on 2017/2/22.
 * Project Source:PermissionRES
 * Ver:1.0
 */
/*Usage example:
    private String[] arrPermissions=new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION};
            PermissionCheck pcM=new PermissionCheck(MainActivity.this,arrPermissions,true);
*/


public class PermissionCheck {
    private AppCompatActivity actSource;
    private Context ctx;
    private String strPermissionSource;
    private String[] arrPermissionSource;
    private Boolean boolIsSinglePermission=true;
    private Boolean isAllGranted=false;
    private int intPermissionNumber=1;

    private View mLayout;


    public PermissionCheck(AppCompatActivity actInput, String strPermissionInput)
    {
        this.actSource=actInput;
        this.strPermissionSource=strPermissionInput;
        ctx=actSource.getApplicationContext();
        boolIsSinglePermission=true;
    }


    public PermissionCheck(AppCompatActivity actInput,String strPermissionInput,Boolean isNeedRequest)
    {
        this.actSource=actInput;
        this.strPermissionSource=strPermissionInput;
        ctx=actSource.getApplicationContext();
        boolIsSinglePermission=true;
        if(isNeedRequest) checkAndRequestPermission();
    }

    public PermissionCheck(AppCompatActivity actInput,String[] arrPermissionInput)
    {
        this.actSource=actInput;
        this.arrPermissionSource=arrPermissionInput;
        ctx=actSource.getApplicationContext();
        boolIsSinglePermission=false;
        intPermissionNumber=arrPermissionSource.length;
    }


    public PermissionCheck(AppCompatActivity actInput,String[] arrPermissionInput,Boolean isNeedRequest)
    {
        this.actSource=actInput;
        this.arrPermissionSource=arrPermissionInput;
        ctx=actSource.getApplicationContext();
        boolIsSinglePermission=false;
        intPermissionNumber=arrPermissionSource.length;
        if(isNeedRequest)checkAndRequestPermission();
    }


    public boolean isAllGrantedPermission(){
        boolean boolReturn=false;

        if(boolIsSinglePermission)
        {
            if (ActivityCompat.checkSelfPermission(ctx, strPermissionSource)
                    != PackageManager.PERMISSION_GRANTED) {
                // Camera permission has not been granted.
                Log.i("LOG_CP","Check Permission,not granted");
                boolReturn=false;
            } else {
                // Camera permissions is already available, show the camera preview.
                Log.i("LOG_CP","Permission has already been granted. Displaying camera preview.");
                boolReturn=true;

            }

        }else
        {
            for(int i=0;i<intPermissionNumber;i++)
            {
                if(ActivityCompat.checkSelfPermission(ctx, arrPermissionSource[i])
                        != PackageManager.PERMISSION_GRANTED)
                {
                    boolReturn=false;
                    break;
                }
                boolReturn=true;
            }
        }
        isAllGranted=boolReturn;
        return  boolReturn;
    }

    public void checkAndRequestPermission(){

        //boolean boolReturn=false;

        isAllGrantedPermission();
        //if(!isAllGranted)
        requestPermission();
        //boolReturn=isAllGrantedPermission();


        //return  boolReturn;
    }

    private void requestPermission(){

        if(boolIsSinglePermission)
        {
            // BEGIN_INCLUDE(camera_permission_request)
            if (ActivityCompat.shouldShowRequestPermissionRationale(actSource,strPermissionSource)) {
                // Provide an additional rationale to the user if the permission was not granted
                // and the user would benefit from additional context for the use of the permission.
                // For example if the user has previously denied the permission.
                ActivityCompat.requestPermissions(actSource,
                        new String[]{strPermissionSource},0);
            } else {
                ActivityCompat.requestPermissions(actSource,
                        new String[]{strPermissionSource},0);
            }
        }else
        {
            Log.i("LOG_PC","Reuest multiple permissions");
            ActivityCompat.requestPermissions(actSource,arrPermissionSource,1);

        }
        // END_INCLUDE(camera_permission_request)
    }
}
//Version Note:
//1.0 1st Version
