package edu.xtu.androidbase.weaher.ui.test;

import android.app.Application;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.DeviceUtils;

/**
 * Created by huilin on 2016/8/15.
 */
public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.initAppInfo(this,true,TestHomeActivity.class);
        DeviceUtils.getInstance().init(getApplicationContext());
    }
}
