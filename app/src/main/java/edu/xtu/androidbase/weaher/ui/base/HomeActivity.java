package edu.xtu.androidbase.weaher.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TabHost;

import java.util.List;

import edu.xtu.androidbase.weaher.R;
import edu.xtu.androidbase.weaher.ui.view.TabFragmentHost;
import edu.xtu.androidbase.weaher.util.AppInfo;
import edu.xtu.androidbase.weaher.util.AppMethods;
import edu.xtu.androidbase.weaher.util.LogUtils;

/**
 * Created by huilin on 2016/8/15.
 */
public abstract class HomeActivity extends BaseFragmentActivity {



    private TabFragmentHost tabHost;

    /**
     * tab页选择
     */
    public static String SELECT_INDEX;
    /**
     * 是否退出
     */
    private boolean isExit = false;

    public class HomeTab{

        private String tab;
        private View view;
        private Class classz;

        public String getTab() {
            return tab;
        }

        public void setTab(String tab) {
            this.tab = tab;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }

        public Class getClassz() {
            return classz;
        }

        public void setClassz(Class classz) {
            this.classz = classz;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setiOnBackPressedListener(new IOnBackPressedListener() {
            @Override
            public boolean backPressdListener() {
                if(isExit){
                    finish();
                }
                AppInfo.getAppInstant().getUiHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit=false;
                    }
                },3000);
                isExit = true;
                AppMethods.shwoToast("连续按两下退出程序");
                return true;
            }
        });
    }
    @Override
    public void initView() {
        List<HomeTab> homeTabs = getHomeTabList();
        tabHost = (TabFragmentHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //添加tab，固定写法
        for (HomeTab homeTab:homeTabs){
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(homeTab.getTab()).setIndicator(homeTab.getView());
            tabHost.addTab(tabSpec,homeTab.getClassz(),null);
        }
    }

    /**
     * 填充tab页面
     * @return
     */
    protected abstract List<HomeTab> getHomeTabList();



    /**
     * 选择tab页
     * @param select
     */
    private void select(int select) {

        if(tabHost!=null){
            LogUtils.v("xxx","select"+select);
            tabHost.setCurrentTab(select);
        }
    }

    @Override
    public void receptIntent(Bundle bundle) {

        int select = bundle.getInt(SELECT_INDEX, -1);
        if(select>-1){
            select(select);
        }
    }

    /**
     * bundle中可以带设置显示第几个tab页
     * @param context
     * @param bundle
     */
    public static void openHomeActivity(Context context,Bundle bundle){
        Intent intent = new Intent(context,AppInfo.getAppInstant().getHomeClass());
        if(bundle!=null){
            intent.putExtra(BUNDLE_DATA,bundle);
        }
        if(context instanceof Activity){
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }else{
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
        ActivityStack.getInstanct().finishAllActivityExceptClass(AppInfo.getAppInstant().getHomeClass());
    }


}
