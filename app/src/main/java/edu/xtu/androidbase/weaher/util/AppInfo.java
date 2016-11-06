package edu.xtu.androidbase.weaher.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Toast;

/**
 * Created by huilin on 2016/8/13.
 * 初始化应用中常用的常量
 */
public class AppInfo {

    private static AppInfo app;
    /**
     * 全局线程
     */
    private Application myContext;
    /**
     * 当前app的启动线程
     */
    private Thread uiThread;
    /**
     * 全局的ui Handler
     */
    private Handler uiHandler;
    /**
     * 是否处于debug模式
     */
    private boolean isDebug = false;
    /**
     * 全局吐司
     */
    private Toast globleToast;
    /**
     * 是否处在后台
     */
    private boolean isBackground = false;
    /**
     * 主activity
     */
    private Class<? extends Activity> homeClass;


    //设备相关
    /**
     * 设备imel
     */
    private String IMEI;
    /**
     * 设备mac地址
     */
    private String mac;
    /**
     * 移动网络操作码
     */
    private String oprator;
    /**
     * 版本号
     */
    private String versionName;
    /**
     * 版本号
     */
    private int versionCode;
    /**
     * 屏幕信息
     */
    private String screen;
    /**
     * 屏幕密度
     */
    private float desity;
    /**
     * 屏幕高
     */
    private int screenHeight;
    /**
     * 屏幕宽
     */
    private int screenWitdh;

    /**
     *
     * 在application初始化
     * @param application
     * @param isDebug
     * @param homeClass
     */
    public synchronized static void initAppInfo(Application application,boolean isDebug,Class<? extends Activity> homeClass){
        if(app==null){
            app = new AppInfo(application,isDebug,homeClass);
            app.initDeviceInfo();
        }
    }

    /**
     * 使用前必须在application调用init方法初始
     * @return
     */
    public static AppInfo getAppInstant(){
        return app;
    }

    /**
     * 私有构造初始化相关从application传来的参数
     * @param app
     * @param isDebug
     * @param homeClass
     */
    private AppInfo(Application app,boolean isDebug,Class<? extends Activity> homeClass){
        this.myContext = app;
        this.isDebug = isDebug;
        this.homeClass = homeClass;
        this.uiThread = Thread.currentThread();//获取当前线程
        this.uiHandler = new Handler();
        this.globleToast = Toast.makeText(app, "", Toast.LENGTH_SHORT);
    }

    /**
     * 初始化设备相关参数
     */
    private void initDeviceInfo() {
        TelephonyManager tm = (TelephonyManager) myContext.getSystemService(Context.TELEPHONY_SERVICE);
        IMEI = tm.getDeviceId();
        mac = AppMethods.getLocalhostAddress();
        oprator = tm.getSimOperator();
        PackageManager packageManager = myContext.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(myContext.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 只有在activity才能初始化，放在baseactivity的oncreate方法初始化
     * @param activity
     */
    public void initScreenInfo(Activity activity){
        //避免重复初始化
        if(desity!=0){
            return;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        defaultDisplay.getMetrics(metrics);
        desity = metrics.density;
        screen = metrics.widthPixels+"       "+metrics.heightPixels;
        if(metrics.heightPixels>=metrics.widthPixels){
            screenHeight = metrics.heightPixels;
            screenWitdh = metrics.widthPixels;
        }else{
            screenHeight = metrics.widthPixels;
            screenWitdh = metrics.heightPixels;
        }


    }

    public Application getMyContext() {
        return myContext;
    }

    public Thread getUiThread() {
        return uiThread;
    }

    public Handler getUiHandler() {
        return uiHandler;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public Toast getGlobleToast() {
        return globleToast;
    }

    public boolean isBackground() {
        return isBackground;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getMac() {
        return mac;
    }

    public String getOprator() {
        return oprator;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getScreen() {
        return screen;
    }

    public float getDesity() {
        return desity;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getScreenWitdh() {
        return screenWitdh;
    }

    public void setBackground(boolean background) {
        isBackground = background;
    }

    public Class<? extends Activity> getHomeClass() {
        return homeClass;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "screenWitdh=" + screenWitdh +
                ", screenHeight=" + screenHeight +
                ", desity=" + desity +
                ", screen='" + screen + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", oprator='" + oprator + '\'' +
                ", mac='" + mac + '\'' +
                ", IMEI='" + IMEI + '\'' +
                ", isBackground=" + isBackground +
                ", isDebug=" + isDebug +
                '}';
    }
}
