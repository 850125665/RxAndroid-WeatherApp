package edu.xtu.androidbase.weaher.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.LogUtils;

/**
 * Created by huilin on 2016/8/14.
 */
public class BaseActivity extends RxAppCompatActivity{

    public String TAG = this.getClass().getName();
    public Context mContext;

    public Dialog loadingDialog;
    /**
     * activity传数据需要传参的key值
     */
    public static String BUNDLE_DATA = "bundle_data";
    /**
     * 传来的数据
     */
    public Bundle data=null;

    /**
     * 检查程序是否在后台运行
     */
    private Runnable checkBackRunning = new Runnable() {
        @Override
        public void run() {
            if(!AppMethods.isAppOnForceGround(BaseActivity.this)){
                AppInfo.getAppInstant().setBackground(false);
            }
        }
    };

    /**
     * 保存crash时的数据
     */
    public String SAVE_INSTANCE = "saveInstance";
    /**
     * 初始化只能在使用activity上下文方法
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            data = savedInstanceState.getBundle(SAVE_INSTANCE);
        }
        loadingDialog=AppMethods.createLoadingDialog(this);
        LogUtils.v(TAG,"onCreate");
        this.mContext=this;
        AppInfo.getAppInstant().initScreenInfo(this);
        ActivityStack.getInstanct().add(this);
        processExtraData();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.v(TAG,"onNewIntent");
        //把最新的intent放进去
        setIntent(intent);
        processExtraData();
    }

    /**
     * 解析传来的数据 传来的数据必须是bundle
     */
    private void processExtraData() {
        Intent intent = getIntent();
        if(intent!=null && intent.getBundleExtra(BUNDLE_DATA)!=null){
            data = intent.getBundleExtra(BUNDLE_DATA);
            receptIntent(data);
        }
    }
    /**
     * 有从别的activity的参数
     */
    public void receptIntent(Bundle bundle){

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.v(TAG,"onStart");
        AppInfo.getAppInstant().getUiHandler().removeCallbacks(checkBackRunning);
        AppInfo.getAppInstant().setBackground(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.v(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.v(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.v(TAG,"onStop");
        //3秒去查看是否在后台
        AppInfo.getAppInstant().getUiHandler().postDelayed(checkBackRunning,3000);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtils.v(TAG,"onSaveInstanceState");
        if(data!=null){
            outState.putBundle(SAVE_INSTANCE,data);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.v(TAG,"onDestroy");
        ActivityStack.getInstanct().remove(this);
    }

    /**
     * 加载加载圆圈
     */
    public void showLoadingDialog(){
        AppMethods.showDialog(mContext,loadingDialog);
    }

    /**
     * 取消加载圆圈
     */
    public void dismissLoadingDialog(){
        AppMethods.dismissDialog(mContext,loadingDialog);
    }
}
