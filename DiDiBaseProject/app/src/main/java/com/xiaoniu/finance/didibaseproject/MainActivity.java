package com.xiaoniu.finance.didibaseproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.didi.virtualapk.PluginManager;

import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {


    private Field mPluginsField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        } else {
            File apk = new File(Environment.getExternalStorageDirectory(), "Demo.apk");
            PluginManager pluginManager = PluginManager.getInstance(this);

            //反射得到mPlugins域
            Class cls = pluginManager.getClass();
            try {
                mPluginsField = cls.getDeclaredField("mPlugins");
                mPluginsField.setAccessible(true);
                ConcurrentHashMap mPlugin = (ConcurrentHashMap) mPluginsField.get(pluginManager);
                mPlugin.remove("com.xiaoniu.finance.didipluginproject");
            } catch (Exception e) {


            }


            if (apk.exists()) {
                try {
                    pluginManager.loadPlugin(apk);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PluginManager.getInstance(MainActivity.this).getLoadedPlugin("com.xiaoniu.finance.didipluginproject") == null) {
                    Toast.makeText(MainActivity.this, "plugin  not loaded", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setClassName("com.xiaoniu.finance.didipluginproject", "com.xiaoniu.finance.didipluginproject.PluginMainActivity");
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    File apk = new File(Environment.getExternalStorageDirectory(), "Demo.apk");
                    PluginManager pluginManager = PluginManager.getInstance(this);
                    if (apk.exists()) {
                        try {
                            pluginManager.loadPlugin(apk);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "ssss", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }
}
