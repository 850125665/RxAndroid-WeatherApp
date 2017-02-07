package edu.xtu.androidbase.weaher.ui.weather;

import android.app.Application;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.base.HomeActivity;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.DbManager;
import edu.xtu.androidbase.weaher.util.DeviceUtils;

/**
 * Created by huilin on 2016/8/15.
 */
public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppInfo.initAppInfo(this,true,HomeActivity.class);
        DeviceUtils.getInstance().init(getApplicationContext());
        DbManager.getInstant().init(R.raw.china_city,DbManager.CITY_DB,this);
        DbManager.enableQueryBuilderLog();
    }
}
