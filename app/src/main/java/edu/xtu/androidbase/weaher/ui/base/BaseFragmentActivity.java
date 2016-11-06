package edu.xtu.androidbase.weaher.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.LogUtils;

/**
 * Created by huilin on 2016/8/15.
 */
public abstract class BaseFragmentActivity extends FragmentActivity{

    public String TAG = this.getClass().getName();
    public Context mContext;
    public Dialog loadingDialog;
    /**
     * 保存crash时的数据
     */
    public static String SAVE_INSTANCE = "saveInstance";

    /**
     * 返回键监听
     */
    private IOnBackPressedListener iOnBackPressedListener;

    /**
     * 按键监听
     */
    private IOnKeyDownlistener iOnKeyDownlistener;
    /**
     * activity传数据需要传参的key值
     */
    public static String BUNDLE_DATA = "bundle_data";
    /**
     * 传来的数据
     */
    public Bundle data=null;


    Runnable checkBackRunning = new Runnable() {
        @Override
        public void run() {
            if(!AppMethods.isAppOnForceGround(BaseFragmentActivity.this)){
                AppInfo.getAppInstant().setBackground(false);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.v(TAG,"onCreate");
        if(savedInstanceState!=null){
            data = savedInstanceState.getBundle(SAVE_INSTANCE);
        }
        loadingDialog = AppMethods.createLoadingDialog(this);
        this.mContext=this;
        setContentView(getContenViewId());
        initView();
        AppInfo.getAppInstant().initScreenInfo(this);
        ActivityStack.getInstanct().add(this);
        processExtraData();

    }

    /**
     * xml文件id
     * @return
     */
    protected abstract int getContenViewId();

    /**
     * 初始化识图
     */
    public abstract void initView();

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.v(TAG,"onStart");
        AppInfo.getAppInstant().setBackground(true);
        AppInfo.getAppInstant().getUiHandler().removeCallbacks(checkBackRunning);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.v(TAG,"onSaveInstanceState");
        if(data!=null){
            outState.putBundle(SAVE_INSTANCE,data);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.v(TAG,"onNewIntent");
        setIntent(intent);
        processExtraData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.v(TAG,"onStop");
        AppInfo.getAppInstant().getUiHandler().postDelayed(checkBackRunning,3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.v(TAG,"onDestroy");
        ActivityStack.getInstanct().remove(this);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        LogUtils.v(TAG,"onAttachFragment");
    }

    @Override
    public void onBackPressed() {
        if(iOnBackPressedListener!=null && iOnBackPressedListener.backPressdListener()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(iOnKeyDownlistener!=null && iOnKeyDownlistener.keyDownListener(keyCode,event)){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }


    /**
     * 返回键监听
     */
    public interface IOnBackPressedListener{
        /**
         * 是否要拦截返回键
         * @return
         */
        boolean backPressdListener();
    }

    /**
     * 按键监听
     */
    private interface IOnKeyDownlistener{
        /**
         * 处理按键事件是否拦截
         * @param keyCode
         * @param keyEvent
         * @return
         */
        boolean keyDownListener(int keyCode,KeyEvent keyEvent);
    }

    public IOnBackPressedListener getiOnBackPressedListener() {
        return iOnBackPressedListener;
    }

    public void setiOnBackPressedListener(IOnBackPressedListener iOnBackPressedListener) {
        this.iOnBackPressedListener = iOnBackPressedListener;
    }

    public IOnKeyDownlistener getiOnKeyDownlistener() {
        return iOnKeyDownlistener;
    }

    public void setiOnKeyDownlistener(IOnKeyDownlistener iOnKeyDownlistener) {
        this.iOnKeyDownlistener = iOnKeyDownlistener;
    }

    /**
     * 解析传来的数据 传来的数据必须是bundle
     */
    protected void processExtraData() {
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
