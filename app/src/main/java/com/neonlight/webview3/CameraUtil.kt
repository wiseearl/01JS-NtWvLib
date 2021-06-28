package com.neonlight.webview3

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraCharacteristics
import android.os.Build
import android.provider.MediaStore
import androidx.core.app.ActivityCompat.startActivityForResult

class CameraUtil {

    /* Usage Example

         CameraUtil.Companion.openBackCam((Activity)MainActivity.this);

         CameraUtil.Companion.openFrontCam(MainActivity.this);
     */

    companion object{

        @JvmStatic
        fun openFrontCam(act:Activity){
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && Build.VERSION.SDK_INT < Build.VERSION_CODES.O -> {
                    cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT)  // Tested on API 24 Android version 7.0(Samsung S6)
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", CameraCharacteristics.LENS_FACING_FRONT) // Tested on API 27 Android version 8.0(Nexus 6P)
                    cameraIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
                }
                else -> cameraIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)  // Tested API 21 Android version 5.0.1(Samsung S4)
            }
            startActivityForResult(act,cameraIntent,1,null)
        }


        @JvmStatic
        fun openBackCam(act : Activity){
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(act,  cameraIntent, 0,null)
        }




    }

}

annotation class JvmStatic
