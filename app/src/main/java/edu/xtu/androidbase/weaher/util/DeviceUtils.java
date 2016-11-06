package edu.xtu.androidbase.weaher.util;

import android.app.KeyguardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;

/**
 * Creator  ming
 * Date     15/12/3
 * Email    skyar@live.cn
 */
public class DeviceUtils {

    private static final String TAG = "DeviceUtils";

    private static class DeviceUtilsHolder {

        private final static DeviceUtils INSTANCE = new DeviceUtils();
    }

    private DeviceUtils() {
    }

    public static DeviceUtils getInstance() {
        return DeviceUtilsHolder.INSTANCE;
    }

    private Context mContext;

    private PowerManager.WakeLock mWakeLock;

    public void init(Context context) {
        mContext = context;
    }

    public String getNetType() {
        String type = "";
        ConnectivityManager connectMgr = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectMgr.getActiveNetworkInfo();

        if (info != null) {
            if (!TextUtils.isEmpty(info.getTypeName())) {
                type = info.getTypeName();
            }
            if (!TextUtils.isEmpty(info.getSubtypeName())) {
                type = info.getSubtypeName();
            }
        }

        return type;
    }

    /**
     * 只关注是否联网
     */
    public  boolean isNetworkConnected() {

            ConnectivityManager mConnectivityManager =
                    (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }

        return false;
    }

    public String getModel() {
        return Build.MODEL == null ? "" : Build.MODEL;
    }

    public String getSystemVersion() {
        return Build.VERSION.RELEASE == null ? "" : Build.VERSION.RELEASE;
    }

    /**
     * Describe:保证在锁屏情况下后台应用唤醒
     * <p/>
     * Date:2015-6-24
     * <p/>
     * Author:liuzhouliang
     */
    public final void acquireWakeLock() {
        if (null == mWakeLock) {
            PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, TAG);
            if (null != mWakeLock) {
                mWakeLock.acquire();
            }
        }
    }

    public final void releaseWakeLock() {
        if (null != mWakeLock) {
            mWakeLock.release();
            mWakeLock = null;
        }
    }

    @SuppressWarnings("deprecation")
    public void wakeUpAndUnlock() {
        KeyguardManager km = (KeyguardManager) mContext
                .getSystemService(Context.KEYGUARD_SERVICE);
        if (km.inKeyguardRestrictedInputMode()) {
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            // 解锁
            kl.disableKeyguard();
            // 获取电源管理器对象
            PowerManager pm = (PowerManager) mContext
                    .getSystemService(Context.POWER_SERVICE);
            // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
            PowerManager.WakeLock wl = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP
                            | PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
            // 点亮屏幕
            wl.acquire();
            // 释放
            wl.release();
        }

    }
}
