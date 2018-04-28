package com.xiaoniu.finance.didibaseproject;

import android.app.Application;
import android.content.Context;

import com.didi.virtualapk.PluginManager;

/**
 * 文件描述：
 * Created by  xn069392
 * 创建时间    2018/4/27
 */

public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        PluginManager.getInstance(base).init();
    }
}
