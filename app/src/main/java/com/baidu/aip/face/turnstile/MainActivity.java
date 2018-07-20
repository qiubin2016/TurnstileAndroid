/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidu.aip.face.turnstile;

//import com.baidu.aip.face.R;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.baidu.aip.face.LogUtil;
import com.itlong.face.R;

import java.io.File;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static boolean appidChangeFlag = false;
    private Button registerButton;
    private Button detectedButton;
    private Button appidButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = (Button) findViewById(R.id.register_button);
        detectedButton = (Button) findViewById(R.id.detect_button);
        appidButton = (Button) findViewById(R.id.appid_button);
        addListener();

        //qb_20180512
        LogUtil.init();

        LogUtil.d("qb", "+++++++++++++++++++++++++++++++++++++++++++++");
        Log.d("qb","test1");
        Log.d("qb","test2");

//        Camera c = null;
//        try {
//            c = Camera.open(); // attempt to get a Camera instance
//        }
//        catch (Exception e){
//            // Camera is not available (in use or does not exist)
//            LogUtil.d("qb", "camera open failed!");
//        }
//        c.getParameters().getSupportedPreviewSizes();


        /**
         * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
        //路径：
        Log.i("qb", "Environment.getDataDirectory():" + Environment.getDataDirectory().getAbsolutePath());
        Log.i("qb", "Environment.getExternalStorageDirectory():" + Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i("qb", "Environment.getRootDirectory():" + Environment.getRootDirectory().getAbsolutePath());
        Log.i("qb", "Environment.getDownloadCacheDirectory():" + Environment.getDownloadCacheDirectory().getAbsolutePath());
        /**
         * 这个方法接收一个参数，表明目录所放的文件的类型，传入的参数是Environment类中的DIRECTORY_XXX静态变量，比如DIRECTORY_DCIM等.
         * 注意：传入的类型参数不能是null，返回的目录路径有可能不存在，所以必须在使用之前确认一下，比如使用File.mkdirs创建该路径。
         */
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS);
        if( !path.exists() ){
            path.mkdirs();
        }
        /* /storage/emulated/0/Alarms */
        Log.i("qb", "Environment.getExternalStoragePublicDirectory():" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS));

        /**
         * contextWrapper
         */
        Log.i("qb", "getCacheDir():" + this.getCacheDir().getAbsolutePath());
        Log.i("qb", "getFilesDir():" + this.getFilesDir().getAbsolutePath());
        Log.i("qb", "getExternalCacheDir():" + this.getExternalCacheDir().getAbsolutePath());
        Log.i("qb", "getExternalFilesDir():" + this.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath());
        Log.i("qb", "getPackageResourcePath():" + this.getPackageResourcePath());
        Log.i("qb", "getDir():" + this.getDir("myfile", Context.MODE_PRIVATE));
    }

    private void addListener() {
        registerButton.setOnClickListener(this);
        detectedButton.setOnClickListener(this);
        appidButton.setOnClickListener(this);
        findViewById(R.id.rtsp_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("onClick", "-----");
        if (registerButton == v) {
            Log.i("onClick", "registerButton");
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        } else if (detectedButton == v) {
            Log.i("onClick", "detectedButton");
            Intent intent = new Intent(MainActivity.this, DetectActivity.class);
            startActivity(intent);
        } else if (appidButton == v) {
            Log.i("onClick", "appidButton");
//            if (appidChangeFlag) {
//                Config.apiKey = "ABtPbk4SD2rhti5XVy8h37CG";  //替换为你的apiKey(ak);
//                Config.secretKey = "0HkgT5iffoX8DAminQZPnR81SowIjS6z";  //替换为你的secretKey(sk);
//            } else {
//                Config.apiKey = "kuPFAOe2wWVNOAtSDjwKx2Q7";//"ABtPbk4SD2rhti5XVy8h37CG";  //替换为你的apiKey(ak);
//                Config.secretKey = "CpSIWfXfF9te0NpOwk3syfoT7lsmWRQ4";//"0HkgT5iffoX8DAminQZPnR81SowIjS6z";  //替换为你的secretKey(sk);
//            }
            appidChangeFlag = !appidChangeFlag;
            Toast.makeText(MainActivity.this,
                    "apiKey:" + Config.apiKey + ",secretKey:" + Config.secretKey + "!",
                    // 设置该Toast提示信息的持续时间,
                    Toast.LENGTH_SHORT);
            Toast.makeText(MainActivity.this, "======================================",
                    // 设置该Toast提示信息的持续时间,
                    Toast.LENGTH_SHORT);
            Log.i("onClick", "apiKey:" + Config.apiKey + ",secretKey:" + Config.secretKey + "!");
        } else {
            Log.i("onClick", "otherButton");
            Intent intent = new Intent(MainActivity.this, RtspTestActivity.class);
            startActivity(intent);
        }
        Log.i("onClick", "-----");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d("qb", "onStart");
        Log.d("qb", "====================onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("qb", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d("qb", "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("qb", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("qb", "onPause");
    }
}
