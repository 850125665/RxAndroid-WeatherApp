package edu.xtu.androidbase.weaher.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import edu.xtu.androidbase.weaher.R;

/**
 * Created by huilin on 2016/8/13.
 * 相关公用方法
 */
public class AppMethods {

    /**
     * 获取手机mac地址
     * @return
     */
    public static String getLocalhostAddress(){
        String mac = "00000";
        WifiManager wf = (WifiManager) AppInfo.getAppInstant().getMyContext().getSystemService(Context.WIFI_SERVICE);
        if(wf!=null){
            WifiInfo connectionInfo = wf.getConnectionInfo();
            if(connectionInfo!=null){
                mac = connectionInfo.getMacAddress();
            }
        }
        return mac;
    }

    /**
     * 判断应用是否在前台
     * @param context
     * @return
     */
    public static boolean isAppOnForceGround(Context context) {
        String pkgName = context.getPackageName();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        // Returns a list of application processes that are running on the device
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) return false;
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // importance:
            // The relative importance level that the system places
            // on this process.
            // May be one of IMPORTANCE_FOREGROUND, IMPORTANCE_VISIBLE,
            // IMPORTANCE_SERVICE, IMPORTANCE_BACKGROUND, or IMPORTANCE_EMPTY.
            // These constants are numbered so that "more important" values are
            // always smaller than "less important" values.
            // processName:
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(pkgName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    /**
     * 展示吐司
     * @param charSequence
     * @param length
     */
    public static void shwoToast(CharSequence charSequence,boolean length){
        final Toast globleToast = AppInfo.getAppInstant().getGlobleToast();
        globleToast.setText(charSequence);
        globleToast.setDuration(length?Toast.LENGTH_LONG:Toast.LENGTH_SHORT);
        AppInfo.getAppInstant().getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                globleToast.show();
            }
        });
    }

    /**
     * 展示吐司
     * @param charSequence
     *
     */
    public static void shwoToast(CharSequence charSequence){
        final Toast globleToast = AppInfo.getAppInstant().getGlobleToast();
        globleToast.setText(charSequence);
        globleToast.setDuration(Toast.LENGTH_SHORT);
        AppInfo.getAppInstant().getUiHandler().post(new Runnable() {
            @Override
            public void run() {
                globleToast.show();
            }
        });
    }

    /**
     * 创建dialog
     * @param context
     * @return
     */
    public static Dialog createLoadingDialog(Context context){
        View view = View.inflate(context,R.layout.view_loading_dialog,null);
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
//        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        dialog.setCancelable(true);// 用“返回键”可以取消
        dialog.setContentView(view,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return dialog;
    }

    /**
     * 显示dialog
     */
    public static void showDialog(final Context context, final Dialog dialog) {
        if (context != null) {
            Activity activity = (Activity) context;
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (dialog != null && !dialog.isShowing()) {
//                        ((ProgressBar)dialog.findViewById(android.R.id.progress)).setIndeterminateDrawable(loadingAnim);
                        dialog.show();
                    }
                }
            });
        }
    }

    /**
     * 隐藏dialog
     */
    public static void dismissDialog(final Context context,
                                     final Dialog dialog) {
        if (context != null) {
            Activity activity = (Activity) context;
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
        }
    }

    /**
     * 获取view的所有parent集合
     * @param parent
     * @param list
     * @return
     */
    public static List<ViewParent> getParentView(ViewParent parent,List list){
        if(parent==null){
            return list;
        }else{
            list.add(parent);
            getParentView(parent.getParent(),list);
        }
        return list;

    }
}
