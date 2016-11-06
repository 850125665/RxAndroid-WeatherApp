package edu.xtu.androidbase.weaher.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.util.AppInfo;

/**
 * Created by huilin on 2016/8/16.
 */
public class TerminalActivity extends BaseFragmentActivity {

    public static String FRGMENT_CLASS;

    /**
     * 封装intent
     */
    public static class WrapIntent{
        private Intent intent;
        private Context mContext;
        public WrapIntent(Context context, Bundle bundle, Class<? extends Fragment> fragmentClass, Class<? extends Activity> activity){
            if(context==null){
                context= AppInfo.getAppInstant().getMyContext();
            }
            mContext= context;
            if(activity==null){
                activity=TerminalActivity.class;
            }
            intent = new Intent(context,activity);
            if(bundle==null){
                bundle = new Bundle();
            }
            bundle.putSerializable(FRGMENT_CLASS,fragmentClass);
            intent.putExtra(BUNDLE_DATA,bundle);
        }

        /**
         * 跳转
         */
        public void show(){
            if(!(mContext instanceof Activity)){
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            mContext.startActivity(intent);
        }

        /**
         * activity跳转带foresult
         * @param requestCode
         */
        public void showForResult(int requestCode){
            if(!(mContext instanceof Activity)){
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            ((Activity)mContext).startActivityForResult(intent,requestCode);
        }

        /**
         * fragment跳转带foresult
         * @param requestCode
         * @param fragment
         */
        public void showForResultFromFragment(int requestCode,Fragment fragment){
            fragment.startActivityForResult(intent,requestCode);
        }


    }

    /**
     * 跳转,可跳转到某个activity
     * @param context
     * @param fClass
     * @param bundle
     * @param activity
     */
    public static void show(Context context,Class<? extends Fragment> fClass,Bundle bundle,Class<? extends Activity> activity){
        new WrapIntent(context,bundle,fClass,activity).show();
    }


    /**
     * 跳转到指定的TerminalActivity
     * @param context
     * @param fClass
     * @param bundle
     */
    public static void show(Context context,Class<? extends Fragment> fClass,Bundle bundle){
        new WrapIntent(context,bundle,fClass,TerminalActivity.class).show();
    }


    /**
     * activity跳转带回复
     * @param context
     * @param fClass
     * @param bundle
     * @param requestCode
     */
    public static void showForResult(Context context,Class<? extends Fragment> fClass,Bundle bundle,int requestCode){
        new WrapIntent(context,bundle,fClass,TerminalActivity.class).showForResult(requestCode);
    }


    /**
     * fragment跳转带回复
     * @param context
     * @param fClass
     * @param bundle
     * @param fragment
     * @param requestCode
     */
    public static void showForResultFromFragment(Context context,Class<? extends Fragment> fClass,Bundle bundle,Fragment fragment,int requestCode){
        new WrapIntent(context,bundle,fClass,TerminalActivity.class).showForResultFromFragment(requestCode,fragment);
    }

    /**
     * xml文件id
     *
     * @return
     */
    @Override
    protected int getContenViewId() {
        return R.layout.activity_terminal;
    }

    /**
     * 初始化识图
     */
    @Override
    public void initView() {

    }

    @Override
    public void receptIntent(Bundle bundle) {
        super.receptIntent(bundle);
        initFragment(bundle);
    }

    /**
     * 初始化fragment
     * @param bundle
     */
    private void initFragment(Bundle bundle) {
        Fragment fragment = null;
        Log.v(TAG,"initFragment");
        try {
            Class fClass = (Class) bundle.getSerializable(FRGMENT_CLASS);
            fragment = (Fragment) fClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            finish();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            finish();
        }
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content,fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
