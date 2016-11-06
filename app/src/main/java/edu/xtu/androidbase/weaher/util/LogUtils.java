package edu.xtu.androidbase.weaher.util;

import android.util.Log;

import edu.xtu.androidbase.weaher.BuildConfig;

/**
 * Created by huilin on 2016/8/21.
 */
public class LogUtils {
    private static boolean isShow = BuildConfig.IS_SHOW_LOG;

    private static String TAG = "log_util";

    public static void d(String msg) {

        if (isShow) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isShow) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isShow) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isShow) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isShow) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isShow) {
            Log.i(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isShow) {
            Log.e(TAG, msg);
        }
    }

}
